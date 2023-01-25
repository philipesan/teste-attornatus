package com.br.attornatus.teste.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_endereco")
public class Endereco {
	
	@Id
    @GeneratedValue
	private Integer id;
	private String cep;
	private String logradouro;
	private String numero;
	private String cidade;
    @ManyToOne
    @JoinColumn(name="pessoa_id", nullable=false)
    private Pessoa pessoa;
}
