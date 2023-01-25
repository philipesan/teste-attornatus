package com.br.attornatus.teste.mapstruc;

import com.br.attornatus.teste.dto.request.PessoaDTO;
import com.br.attornatus.teste.dto.response.EnderecoResponseDTO;
import com.br.attornatus.teste.dto.response.PessoaResponseDTO;
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
public class PessoaMapperImpl implements PessoaMapper {

    @Override
    public PessoaResponseDTO toResponseDto(Pessoa pessoa) {
        if ( pessoa == null ) {
            return null;
        }

        PessoaResponseDTO pessoaResponseDTO = new PessoaResponseDTO();

        pessoaResponseDTO.setId( pessoa.getId() );
        pessoaResponseDTO.setNome( pessoa.getNome() );
        pessoaResponseDTO.setDtNascimento( pessoa.getDtNascimento() );
        pessoaResponseDTO.setEndereco( enderecoToEnderecoResponseDTO( pessoa.getEndereco() ) );

        return pessoaResponseDTO;
    }

    @Override
    public Pessoa toEntity(PessoaDTO pessoaDto) {
        if ( pessoaDto == null ) {
            return null;
        }

        Pessoa pessoa = new Pessoa();

        pessoa.setNome( pessoaDto.getNome() );
        pessoa.setDtNascimento( pessoaDto.getDtNascimento() );

        return pessoa;
    }

    protected EnderecoResponseDTO enderecoToEnderecoResponseDTO(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();

        enderecoResponseDTO.setCep( endereco.getCep() );
        enderecoResponseDTO.setCidade( endereco.getCidade() );
        enderecoResponseDTO.setId( endereco.getId() );
        enderecoResponseDTO.setLogradouro( endereco.getLogradouro() );
        enderecoResponseDTO.setNumero( endereco.getNumero() );

        return enderecoResponseDTO;
    }
}
