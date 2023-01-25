package com.br.attornatus.teste.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="tb_pessoa")
public class Pessoa {
	
	@Id
    @GeneratedValue
	private Integer id;
	private String nome;
	private LocalDate dtNascimento;
    @ManyToOne
    @JoinColumn(columnDefinition="integer", name = "id_endereco")
	private Endereco endereco;
}
