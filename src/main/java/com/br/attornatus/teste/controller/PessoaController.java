package com.br.attornatus.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.attornatus.teste.dto.request.PessoaDTO;
import com.br.attornatus.teste.dto.response.ApiResponse;
import com.br.attornatus.teste.dto.response.PessoaResponseDTO;
import com.br.attornatus.teste.service.PessoaService;
import com.br.attornatus.teste.specification.SearchRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="api/v1/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> postPessoa(@Valid @RequestBody PessoaDTO pessoa) {
		return pessoaService.postPessoa(pessoa);
	}
	
	@PutMapping(value="/{idPessoa}")
	public ResponseEntity<ApiResponse> putPessoa(@Valid @RequestBody PessoaDTO pessoa,
														@PathVariable Integer idPessoa) {
		return pessoaService.putPessoa(pessoa, idPessoa);
	}

	
	@PostMapping(path="/lista")
	public Page<PessoaResponseDTO> listaPessoa(@RequestBody SearchRequest filtros) {
		return pessoaService.buscaPessoa(filtros);
	}
	
	@GetMapping(path="/lista-enderecos/{idPessoa}")
	public ResponseEntity<ApiResponse> listaEnderecoPessoa(@PathVariable Integer idPessoa) {
		return pessoaService.listarEnderecos(idPessoa);
	}
}
