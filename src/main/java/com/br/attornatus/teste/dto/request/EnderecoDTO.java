package com.br.attornatus.teste.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EnderecoDTO {
	
	@NotEmpty
	@NotBlank
	private String cep;

	@NotEmpty
	@NotBlank
	private String logradouro;

	@NotEmpty
	@NotBlank
	private String numero;
	
	@NotEmpty
	@NotBlank
	private String cidade;
	
	@NotNull
	private Integer idPessoa;

}
