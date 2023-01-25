package com.br.attornatus.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.attornatus.teste.dto.request.EnderecoDTO;
import com.br.attornatus.teste.dto.response.ApiResponse;
import com.br.attornatus.teste.dto.response.EnderecoResponseDTO;
import com.br.attornatus.teste.service.EnderecoService;
import com.br.attornatus.teste.specification.SearchRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="api/v1/endereco")
public class EnderecoController {
	@Autowired
	EnderecoService enderecoService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> postEndereco(@Valid @RequestBody EnderecoDTO endereco) {
		return enderecoService.postEndereco(endereco);
	}
	
	@PostMapping(path="/lista")
	public Page<EnderecoResponseDTO> listaEndereco(@RequestBody SearchRequest filtros) {
		return enderecoService.buscaEndereco(filtros);
	}
}
