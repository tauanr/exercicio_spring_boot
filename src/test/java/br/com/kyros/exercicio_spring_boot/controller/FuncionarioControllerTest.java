package br.com.kyros.exercicio_spring_boot.controller;

import java.net.URI;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FuncionarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private JSONObject jsonObject;

	@BeforeEach
	void setUp() throws JSONException {
		jsonObject = new JSONObject();
		jsonObject.put("nome", "nomeQuatro");
		jsonObject.put("numeroDeRegistro", "4");
		jsonObject.put("cpf", "222333444-11");
		jsonObject.put("dataDeAdmissao", "02/12/2000");
		jsonObject.put("salario", "2000");
		jsonObject.put("idLider", "1");
		jsonObject.put("status", "DESLIGADO");
		jsonObject.put("dataDeDesligamento", "10/10/2001");
		jsonObject.put("genero", "MASCULINO");
		jsonObject.put("idDepartamento", "1");
	}

	@Test
	public void getFuncionarioIdNotInDb() throws Exception {
		Integer id = 100;
		URI uri = new URI("/funcionario/" + id);

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void postWithNumeroDeRegistroAlreadyUsed() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("numeroDeRegistro", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithNumeroDeRegistroWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("numeroDeRegistro", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithCpfAlreadyUsed() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("cpf", "123456789-11");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithCpfWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("cpf", "AA3456789-11");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithStatusWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("status", "DESLIGAD");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithNomeWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("nome", "nome4");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithDataDeAdmissaoWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("dataDeAdmissao", "100/10/1");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithSalarioWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("salario", "aa");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdLiderNotInDb() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("idLider", "100");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdLiderWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("idLider", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithDataDeDesligamentoWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("dataDeDesligamento", "100/10/1");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithDataDeDesligamentoWithoutStatusDesligado() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("status", "ATIVO");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithStatusDesligadoWithoutDataDeDesligamento() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("dataDeDesligamento", "");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithGeneroWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("genero", "MASCULIN");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdDepartamentoNotInDb() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("idDepartamento", "100");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdDepartamentoWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("idDepartamento", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithNumeroDeRegistroAlreadyUsed() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("numeroDeRegistro", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithNumeroDeRegistroWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("numeroDeRegistro", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithCpfAlreadyUsed() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("cpf", "123456789-11");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithCpfWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("cpf", "AA3456789-11");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithStatusWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("status", "DESLIGAD");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithNomeWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("nome", "nome4");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithDataDeAdmissaoWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("dataDeAdmissao", "100/10/1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithSalarioWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("salario", "aa");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithIdLiderNotInDb() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("idLider", "100");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithIdLiderWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("idLider", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithDataDeDesligamentoWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("dataDeDesligamento", "100/10/1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithDataDeDesligamentoWithoutStatusDesligado() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("status", "ATIVO");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithStatusDesligadoWithoutDataDeDesligamento() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("dataDeDesligamento", "");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithGeneroWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("genero", "MASCULIN");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithIdDepartamentoNotInDb() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("idDepartamento", "100");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithIdDepartamentoWrongFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("id", "3");
		jsonObject.put("idDepartamento", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void deleteFuncionarioIdNotInDb() throws Exception {
		Integer id = 100;
		URI uri = new URI("/funcionario/" + id);

		mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void getFuncionarios() throws Exception {
		URI uri = new URI("/funcionarios/");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void postWithCorrectFormat() throws Exception {
		URI uri = new URI("/funcionario/");
		jsonObject.put("numeroDeRegistro", "10");
		jsonObject.put("cpf", "123123123-11");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

}
