package com.br.attornatus.teste.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.attornatus.teste.dto.request.PessoaDTO;
import com.br.attornatus.teste.dto.response.ApiResponse;
import com.br.attornatus.teste.dto.response.PessoaResponseDTO;
import com.br.attornatus.teste.specification.SearchRequest;

@Service
public interface PessoaService {
	public ResponseEntity<ApiResponse> postPessoa(PessoaDTO pessoa);
	public Page<PessoaResponseDTO> buscaPessoa(SearchRequest filtros);
	public ResponseEntity<ApiResponse> listarEnderecos(Integer idPessoa);
	public ResponseEntity<ApiResponse> putPessoa(PessoaDTO pessoa, Integer idPessoa);

}
