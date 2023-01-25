package com.br.attornatus.teste.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.attornatus.teste.dto.request.EnderecoDTO;
import com.br.attornatus.teste.dto.response.ApiResponse;
import com.br.attornatus.teste.dto.response.EnderecoResponseDTO;
import com.br.attornatus.teste.specification.SearchRequest;

@Service
public interface EnderecoService {
	public ResponseEntity<ApiResponse> postEndereco(EnderecoDTO endereco);
	public Page<EnderecoResponseDTO> buscaEndereco(SearchRequest filtros);
}
