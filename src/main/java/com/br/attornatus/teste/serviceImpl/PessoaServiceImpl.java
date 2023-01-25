package com.br.attornatus.teste.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.br.attornatus.teste.dto.request.PessoaDTO;
import com.br.attornatus.teste.dto.response.ApiResponse;
import com.br.attornatus.teste.dto.response.EnderecoResponseDTO;
import com.br.attornatus.teste.dto.response.PessoaResponseDTO;
import com.br.attornatus.teste.entity.Endereco;
import com.br.attornatus.teste.entity.Pessoa;
import com.br.attornatus.teste.mapstruc.EnderecoMapper;
import com.br.attornatus.teste.mapstruc.PessoaMapper;
import com.br.attornatus.teste.repository.EnderecoRepository;
import com.br.attornatus.teste.repository.PessoaRepository;
import com.br.attornatus.teste.service.PessoaService;
import com.br.attornatus.teste.specification.SearchRequest;
import com.br.attornatus.teste.specification.SearchSpecification;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class PessoaServiceImpl implements PessoaService {
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PessoaMapper pessoaMapper;
	
	@Autowired
	EnderecoMapper enderecoMapper;
	
	@Override
	public ResponseEntity<ApiResponse> postPessoa(PessoaDTO pessoa) {
		ApiResponse resposta = new ApiResponse();
		
		Pessoa pessoaEntity = pessoaMapper.toEntity(pessoa);

		pessoaEntity = pessoaRepository.save(pessoaEntity);
		PessoaResponseDTO pessoaResposta = pessoaMapper.toResponseDto(pessoaEntity);
		resposta.setContent(pessoaResposta);
	
		return ResponseEntity.status(201).body(resposta);
	}

	@Override
	public Page<PessoaResponseDTO> buscaPessoa(SearchRequest filtros) {
        SearchSpecification<Pessoa> specification = new SearchSpecification<>(filtros);
        Pageable pageable = SearchSpecification.getPageable(filtros.getPage(), filtros.getSize());
        Page<PessoaResponseDTO> pessoas = pessoaRepository.findAll(specification, pageable).map(pessoaMapper::toResponseDto);
        
        return pessoas;
	}
	
	public Endereco atribuiEndereco(Integer idEndereco) {
		
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(Long.valueOf(idEndereco));
		if(!enderecoOpt.isEmpty()) {
			return enderecoOpt.get();
		} else {
			return null;
		}
		

	}

	@Override
	public ResponseEntity<ApiResponse> listarEnderecos(Integer idPessoa) {
		ApiResponse resposta = new ApiResponse();
		Optional<Pessoa> pessoaOpt = pessoaRepository.findById(idPessoa);
		
		if(pessoaOpt.isEmpty()) {
			resposta.setMensagem("Usuário não encontrado");
			return ResponseEntity.status(400).body(resposta);
		}
		
		List<EnderecoResponseDTO> enderecos = enderecoRepository.findAllByPessoa(pessoaOpt.get()).stream().map(enderecoMapper::toResponseDto).collect(Collectors.toList());
		
		resposta.setContent(enderecos);
		
		return ResponseEntity.status(200).body(resposta);
	}

	@Override
	public ResponseEntity<ApiResponse> putPessoa(PessoaDTO pessoa, Integer idPessoa) {
		
		ApiResponse resposta = new ApiResponse();
		Endereco endereco = new Endereco();
		
		Optional<Pessoa> pessoaOpt = pessoaRepository.findById(idPessoa);
		
		if(pessoaOpt.isEmpty()) {
			resposta.setMensagem("Usuário não encontrado");
			return ResponseEntity.status(400).body(resposta);
		}
		
		Pessoa pessoaEntity = pessoaOpt.get();
		
		pessoaEntity.setNome(pessoa.getNome());
		pessoaEntity.setDtNascimento(pessoa.getDtNascimento());
		
		if(pessoa.getEndereco() != null) {
			endereco = this.atribuiEndereco(pessoa.getEndereco());
			if(!endereco.getPessoa().getId().equals(pessoaEntity.getId())) {
				resposta.setMensagem("O endereço é de outra pessoa");
				return ResponseEntity.status(403).body(resposta);
			} else {
				pessoaEntity.setEndereco(endereco);
			}
		}

		pessoaEntity = pessoaRepository.save(pessoaEntity);
		PessoaResponseDTO pessoaResposta = pessoaMapper.toResponseDto(pessoaEntity);
		resposta.setContent(pessoaResposta);
	
		return ResponseEntity.status(200).body(resposta);
	}
}
