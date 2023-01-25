package com.br.attornatus.teste.mapstruc;

import com.br.attornatus.teste.dto.request.EnderecoDTO;
import com.br.attornatus.teste.dto.response.EnderecoResponseDTO;
import com.br.attornatus.teste.entity.Endereco;
import com.br.attornatus.teste.entity.Pessoa;
import org.springframework.stereotype.Component;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-24T17:11:16-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.3 (Eclipse Adoptium)"
)
*/
@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public EnderecoResponseDTO toResponseDto(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();

        enderecoResponseDTO.setId( endereco.getId() );
        enderecoResponseDTO.setCep( endereco.getCep() );
        enderecoResponseDTO.setLogradouro( endereco.getLogradouro() );
        enderecoResponseDTO.setNumero( endereco.getNumero() );
        enderecoResponseDTO.setCidade( endereco.getCidade() );
        enderecoResponseDTO.setIdPessoa( enderecoPessoaId( endereco ) );

        return enderecoResponseDTO;
    }

    @Override
    public Endereco toEntity(EnderecoDTO enderecoDTO) {
        if ( enderecoDTO == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setCep( enderecoDTO.getCep() );
        endereco.setLogradouro( enderecoDTO.getLogradouro() );
        endereco.setNumero( enderecoDTO.getNumero() );
        endereco.setCidade( enderecoDTO.getCidade() );

        return endereco;
    }

    private Integer enderecoPessoaId(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }
        Pessoa pessoa = endereco.getPessoa();
        if ( pessoa == null ) {
            return null;
        }
        Integer id = pessoa.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
