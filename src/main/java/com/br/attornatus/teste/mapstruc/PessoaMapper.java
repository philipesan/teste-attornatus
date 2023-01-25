package com.br.attornatus.teste.mapstruc;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.br.attornatus.teste.dto.request.PessoaDTO;
import com.br.attornatus.teste.dto.response.PessoaResponseDTO;
import com.br.attornatus.teste.entity.Pessoa;

@Mapper(componentModel="spring")
public interface PessoaMapper {
	
    PessoaMapper INSTANCE = Mappers.getMapper( PessoaMapper.class );
	
	@Mapping(target="id", source="id")
	@Mapping(target="nome", source="nome")
	@Mapping(target="dtNascimento", source="dtNascimento")
	@Mapping(target="endereco", source="endereco")
	PessoaResponseDTO toResponseDto(Pessoa pessoa);
	
	@Mapping(target="id", ignore=true)
	@Mapping(target="nome", source="nome")
	@Mapping(target="dtNascimento", source="dtNascimento")
	@Mapping(ignore=true, target = "endereco")
	Pessoa toEntity(PessoaDTO pessoaDto);
	
}
