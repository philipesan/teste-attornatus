package com.br.attornatus.teste.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.br.attornatus.teste.entity.Endereco;
import com.br.attornatus.teste.entity.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>,
        JpaSpecificationExecutor<Endereco> {
	
	ArrayList<Endereco> findAllByPessoa(Pessoa pessoa);
}
