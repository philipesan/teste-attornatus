package com.br.attornatus.teste.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import com.br.attornatus.teste.dto.request.EnderecoDTO;
import com.br.attornatus.teste.dto.request.PessoaDTO;
import com.br.attornatus.teste.dto.response.ApiResponse;
import com.br.attornatus.teste.dto.response.EnderecoResponseDTO;
import com.br.attornatus.teste.dto.response.PessoaResponseDTO;
import com.br.attornatus.teste.entity.Endereco;
import com.br.attornatus.teste.entity.Pessoa;
import com.br.attornatus.teste.mapstruc.EnderecoMapper;
import com.br.attornatus.teste.mapstruc.PessoaMapper;
import com.br.attornatus.teste.repository.EnderecoRepository;
import com.br.attornatus.teste.repository.PessoaRepository;
import com.br.attornatus.teste.serviceImpl.EnderecoServiceImpl;
import com.br.attornatus.teste.specification.FieldType;
import com.br.attornatus.teste.specification.FilterRequest;
import com.br.attornatus.teste.specification.Operator;
import com.br.attornatus.teste.specification.SearchRequest;
import com.br.attornatus.teste.specification.SortDirection;
import com.br.attornatus.teste.specification.SortRequest;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EnderecoServiceTest {
	@InjectMocks
	EnderecoServiceImpl enderecoServiceImpl;
	
	@Mock
	PessoaRepository pessoaRepository;
	
	@Mock
	EnderecoRepository enderecoRepository;
	
	@Mock
	PessoaMapper pessoaMapper;
	
	@Mock
	EnderecoMapper enderecoMapper;
	
	SearchRequest 	  		filtros		   	 = new SearchRequest();
	FilterRequest 	  		filterRequest    = new FilterRequest();
	SortRequest   	  		sortRequest      = new SortRequest();
	PessoaDTO 	  	  		pessoaDtoOk      = new PessoaDTO();
	PessoaResponseDTO		pessoaResponseOk = new PessoaResponseDTO();
	Pessoa	   	  	  		pessoaOk 		 = new Pessoa();
	Endereco		  		enderecoOk	     = new Endereco();
	EnderecoResponseDTO		endResponseOk    = new EnderecoResponseDTO();
	EnderecoDTO				endDtoOk    	 = new EnderecoDTO();
	
	ArrayList<Endereco> enderecos			 = new ArrayList<Endereco>();
	ArrayList<FilterRequest> filterRequests  = new ArrayList<FilterRequest>();
	ArrayList<SortRequest> sortRequests      = new ArrayList<SortRequest>();
	
	
    @BeforeEach
    public void setup() throws IOException {
        pessoaDtoOk.setNome("Pessoilson da Silva");
        pessoaDtoOk.setDtNascimento(LocalDate.of(2000, 01, 01));
        
        pessoaOk.setId(1);
        pessoaOk.setNome("Pessoilson da Silva");
        pessoaOk.setDtNascimento(LocalDate.of(2000, 01, 01));
        
        sortRequest.setDirection(SortDirection.DESC);
        sortRequest.setKey("id");
        sortRequests.add(sortRequest);
        
        enderecoOk.setCep("1234567");
        enderecoOk.setCidade("Sao Paulo");
        enderecoOk.setId(1);
        enderecoOk.setLogradouro("Rua Engenheiro Engenheiroso");
        enderecoOk.setNumero("12");
        enderecoOk.setPessoa(pessoaOk);
        
        endDtoOk.setCep("1234567");
        endDtoOk.setCidade("Sao Paulo");
        endDtoOk.setLogradouro("Rua Engenheiro Engenheiroso");
        endDtoOk.setNumero("12");
        endDtoOk.setIdPessoa(1);
        
        endResponseOk.setCep("1234567");
        endResponseOk.setCidade("Sao Paulo");
        endResponseOk.setId(1);
        endResponseOk.setLogradouro("Rua Engenheiro Engenheiroso");
        endResponseOk.setNumero("12");
        endResponseOk.setIdPessoa(1);
        
        filterRequest.setFieldType(FieldType.INTEGER);
        filterRequest.setKey("id");
        filterRequest.setOperator(Operator.LIKE);
        filterRequest.setValue(1);
        filterRequests.add(filterRequest);
    }

    @Test
    public void pessoaPostOkTest() {
    	when(this.enderecoRepository.save(Mockito.any())).thenReturn(enderecoOk);
    	when(this.pessoaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pessoaOk));
    	when(this.enderecoMapper.toEntity(Mockito.any())).thenReturn(enderecoOk);
    	when(this.enderecoMapper.toResponseDto(Mockito.any())).thenReturn(endResponseOk);

    	ResponseEntity<ApiResponse> resposta = enderecoServiceImpl.postEndereco(endDtoOk);
    	assertEquals(resposta.getStatusCode().value(), 201);
    }
    
    @Test
    public void pessoaPostNokTest() {
    	when(this.pessoaRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
    	ResponseEntity<ApiResponse> resposta = enderecoServiceImpl.postEndereco(endDtoOk);
    	assertEquals(resposta.getStatusCode().value(), 400);
    }
}
