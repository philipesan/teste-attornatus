package com.br.attornatus.teste.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.br.attornatus.teste.service.EnderecoService;

@ContextConfiguration(classes = { EnderecoController.class })
@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EnderecoService enderecoService;
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	public static final String PATH = "http://localhost:8080/api/v1/endereco";

	public final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void postEnderecoControllerTest() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.post(PATH)
						.content("{\r\n"
								+ "    \"cep\": \"01206000\",\r\n"
								+ "    \"logradouro\": \"Avenida Rio Branco\",\r\n"
								+ "    \"numero\": \"744\",\r\n"
								+ "    \"cidade\": \"Sao Paulo\",\r\n"
								+ "    \"idPessoa\": 1\r\n"
								+ "}")
						.contentType(APPLICATION_JSON_UTF8))
						.andDo(MockMvcResultHandlers.print())
						.andReturn();
		
		Integer statusCode = result.getResponse().getStatus();
		assertEquals(200, statusCode);
	}
	
	@Test
	public void postEnderecoControllerNoContentTest() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.post(PATH)
						.contentType(APPLICATION_JSON_UTF8))
						.andDo(MockMvcResultHandlers.print())
						.andReturn();
		
		Integer statusCode = result.getResponse().getStatus();
		assertEquals(400, statusCode);
	}
	
	@Test
	public void postBuscaEnderecoControllerTest() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.post(PATH + "/lista")
						.content("{}")
						.contentType(APPLICATION_JSON_UTF8))
						.andDo(MockMvcResultHandlers.print())
						.andReturn();
		
		Integer statusCode = result.getResponse().getStatus();
		assertEquals(200, statusCode);
	}
	
	@Test
	public void postBuscaEnderecoControllerNoContentTest() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.post(PATH + "/lista")
						.contentType(APPLICATION_JSON_UTF8))
						.andDo(MockMvcResultHandlers.print())
						.andReturn();
		
		Integer statusCode = result.getResponse().getStatus();
		assertEquals(400, statusCode);
	}

}