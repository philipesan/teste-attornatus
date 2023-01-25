package com.br.attornatus.teste.mapstruc;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.br.attornatus.teste.dto.request.EnderecoDTO;
import com.br.attornatus.teste.dto.response.EnderecoResponseDTO;
import com.br.attornatus.teste.entity.Endereco;

@Mapper(componentModel="spring")
public interface EnderecoMapper {
	
    EnderecoMapper INSTANCE = Mappers.getMapper( EnderecoMapper.class );
    
	@Mapping(target="id", source="id")
	@Mapping(target="cep", source="cep")
	@Mapping(target="logradouro", source="logradouro")
	@Mapping(target="numero", source="numero")
	@Mapping(target="cidade", source="cidade")
	@Mapping(target="idPessoa", source="pessoa.id")
	EnderecoResponseDTO toResponseDto(Endereco endereco);
	
	@Mapping(target="id", ignore=true)
	@Mapping(target="cep", source="cep")
	@Mapping(target="logradouro", source="logradouro")
	@Mapping(target="numero", source="numero")
	@Mapping(target="cidade", source="cidade")
	@Mapping(ignore=true, target = "pessoa")
	Endereco toEntity(EnderecoDTO enderecoDTO);

}
