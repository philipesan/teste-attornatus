package com.br.attornatus.teste.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EnderecoResponseDTO {
	
	private Integer id;
	private String cep;
	private String logradouro;
	private String numero;
	private String cidade;
	private Integer idPessoa;
}
