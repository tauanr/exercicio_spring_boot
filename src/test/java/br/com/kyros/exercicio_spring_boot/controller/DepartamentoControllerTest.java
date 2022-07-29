package br.com.kyros.exercicio_spring_boot.controller;

import java.net.URI;

import org.json.JSONArray;
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
class DepartamentoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private JSONObject jsonObject;

	@BeforeEach
	void setUp() throws JSONException {
		jsonObject = new JSONObject();
		jsonObject.put("nome", "departamentoQuatro");
		jsonObject.put("codigoDaFolhaDePagamento", "4");
		jsonObject.put("status", "ATIVO");
		JSONArray jsonArray = new JSONArray();
		jsonArray.put("1");
		jsonArray.put("3");
		jsonObject.put("funcionariosIds", jsonArray);
	}

	@Test
	public void getDepartamentos() throws Exception {
		URI uri = new URI("/departamentos/");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void postWithCodigoDaFolhaDePagamentoAlreadyUsed() throws Exception {
		URI uri = new URI("/departamento/");
		jsonObject.put("codigoDaFolhaDePagamento", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithCodigoDaFolhaDePagamentoWrongFormat() throws Exception {
		URI uri = new URI("/departamento/");
		jsonObject.put("codigoDaFolhaDePagamento", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithNomeWrongFormat() throws Exception {
		URI uri = new URI("/departamento/");
		jsonObject.put("nome", "departamento4");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithStatusWrongFormat() throws Exception {
		URI uri = new URI("/departamento/");
		jsonObject.put("status", "ATIV");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithFuncionariosIdsNotInDb() throws Exception {
		URI uri = new URI("/departamento/");
		JSONArray jsonArray = new JSONArray();
		jsonArray.put("5");
		jsonObject.put("funcionariosIds", jsonArray);

		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithCodigoDaFolhaDePagamentoAlreadyUsed() throws Exception {
		URI uri = new URI("/departamento/");
		jsonObject.put("codigoDaFolhaDePagamento", "1");
		jsonObject.put("id", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithCodigoDaFolhaDePagamentoWrongFormat() throws Exception {
		URI uri = new URI("/departamento/");
		jsonObject.put("codigoDaFolhaDePagamento", "a");
		jsonObject.put("id", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithNomeWrongFormat() throws Exception {
		URI uri = new URI("/departamento/");
		jsonObject.put("nome", "departamento4");
		jsonObject.put("id", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithStatusWrongFormat() throws Exception {
		URI uri = new URI("/departamento/");
		jsonObject.put("status", "ATIV");
		jsonObject.put("id", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithFuncionariosIdsNotInDb() throws Exception {
		URI uri = new URI("/departamento/");
		JSONArray jsonArray = new JSONArray();
		jsonArray.put("5");
		jsonObject.put("funcionariosIds", jsonArray);
		jsonObject.put("id", "1");

		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithIdNotInDb() throws Exception {
		URI uri = new URI("/departamento/");
		jsonObject.put("id", "5");

		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithCorrectFormat() throws Exception {
		URI uri = new URI("/departamento/");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
}
