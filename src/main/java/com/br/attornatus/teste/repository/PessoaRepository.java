package com.br.attornatus.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.br.attornatus.teste.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>,
        JpaSpecificationExecutor<Pessoa> {

	Optional<Pessoa> findById(Integer idPessoa);
}