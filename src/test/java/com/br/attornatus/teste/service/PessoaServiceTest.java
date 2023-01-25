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
import com.br.attornatus.teste.serviceImpl.PessoaServiceImpl;
import com.br.attornatus.teste.specification.FieldType;
import com.br.attornatus.teste.specification.FilterRequest;
import com.br.attornatus.teste.specification.Operator;
import com.br.attornatus.teste.specification.SearchRequest;
import com.br.attornatus.teste.specification.SortDirection;
import com.br.attornatus.teste.specification.SortRequest;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PessoaServiceTest {
	@InjectMocks
	PessoaServiceImpl pessoaServiceImpl;
	
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
        
        filtros.setFilters(filterRequests);
        filtros.setSorts(sortRequests);
    }
    
    @Test
    public void pessoaPostTest() {
    	when(this.pessoaRepository.save(Mockito.any())).thenReturn(pessoaOk);
    	when(this.pessoaMapper.toEntity(Mockito.any())).thenReturn(pessoaOk);
    	ResponseEntity<ApiResponse> resposta = pessoaServiceImpl.postPessoa(pessoaDtoOk);
    	assertEquals(resposta.getStatusCode().value(), 201);
    }
    
    @Test
    public void pessoaEditaValorOk() {
    	when(this.pessoaRepository.save(Mockito.any())).thenReturn(pessoaOk);
    	when(this.enderecoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(enderecoOk));
    	when(this.pessoaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(pessoaOk));
    	when(this.pessoaMapper.toEntity(Mockito.any())).thenReturn(pessoaOk);
    	ResponseEntity<ApiResponse> resposta = pessoaServiceImpl.putPessoa(pessoaDtoOk, 1);
    	assertEquals(resposta.getStatusCode().value(), 200);
    }
    
    @Test
    public void pessoaEditaValorNotOk() {
    	when(this.pessoaRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
    	ResponseEntity<ApiResponse> resposta = pessoaServiceImpl.putPessoa(pessoaDtoOk, 1);
    	assertEquals(400, resposta.getStatusCode().value());
    }
    
    @Test
    public void pessoaBuscaEnderecosOk() {
    	enderecos.add(enderecoOk);
    	when(this.pessoaRepository.save(Mockito.any())).thenReturn(pessoaOk);
    	when(this.pessoaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(pessoaOk));
    	when(this.enderecoRepository.findAllByPessoa(Mockito.any())).thenReturn(enderecos);
    	when(this.enderecoMapper.toResponseDto(Mockito.any())).thenReturn(endResponseOk);
    	ResponseEntity<ApiResponse> resposta = pessoaServiceImpl.listarEnderecos(1);
    	assertEquals(resposta.getStatusCode().value(), 200);
    }
    
    @Test
    public void pessoaBuscaEnderecosNotOk() {
    	when(this.pessoaRepository.save(Mockito.any())).thenReturn(Optional.empty());
    	ResponseEntity<ApiResponse> resposta = pessoaServiceImpl.listarEnderecos(1);
    	assertEquals(resposta.getStatusCode().value(), 400);
    }
}
