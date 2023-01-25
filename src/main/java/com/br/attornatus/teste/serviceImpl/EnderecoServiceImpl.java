package com.br.attornatus.teste.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.br.attornatus.teste.dto.request.EnderecoDTO;
import com.br.attornatus.teste.dto.response.ApiResponse;
import com.br.attornatus.teste.dto.response.EnderecoResponseDTO;
import com.br.attornatus.teste.entity.Endereco;
import com.br.attornatus.teste.entity.Pessoa;
import com.br.attornatus.teste.mapstruc.EnderecoMapper;
import com.br.attornatus.teste.repository.EnderecoRepository;
import com.br.attornatus.teste.repository.PessoaRepository;
import com.br.attornatus.teste.service.EnderecoService;
import com.br.attornatus.teste.specification.SearchRequest;
import com.br.attornatus.teste.specification.SearchSpecification;

@Component
public class EnderecoServiceImpl implements EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	EnderecoMapper enderecoMapper;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	
	@Override
	public Page<EnderecoResponseDTO> buscaEndereco(SearchRequest filtros) {
        SearchSpecification<Endereco> specification = new SearchSpecification<>(filtros);
        Pageable pageable = SearchSpecification.getPageable(filtros.getPage(), filtros.getSize());
        Page<EnderecoResponseDTO> enderecos = enderecoRepository.findAll(specification, pageable).map(enderecoMapper::toResponseDto);
        
        return enderecos;
	}


	@Override
	public ResponseEntity<ApiResponse> postEndereco(EnderecoDTO endereco) {
		ApiResponse resposta = new ApiResponse();
		
		Optional<Pessoa> pessoaOpt = pessoaRepository.findById(Long.valueOf(endereco.getIdPessoa()));

		if(pessoaOpt.isEmpty()) {
			resposta.setMensagem("Pessoa deste endereço não existe");
			return ResponseEntity.status(400).body(resposta);
		}
		
		Pessoa pessoa = pessoaOpt.get();
		
		Endereco enderecoEntity = enderecoMapper.toEntity(endereco);
		enderecoEntity.setPessoa(pessoa);

		enderecoEntity = enderecoRepository.save(enderecoEntity);
		
		if(pessoa.getEndereco() == null) {
			pessoa.setEndereco(enderecoEntity);
			pessoaRepository.save(pessoa);
		}
		
		EnderecoResponseDTO pessoaResposta = enderecoMapper.toResponseDto(enderecoEntity);
		resposta.setContent(pessoaResposta);
	
		return ResponseEntity.status(201).body(resposta);
	}

}
