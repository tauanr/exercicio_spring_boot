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
class CompetenciaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private JSONObject jsonObject;

	@BeforeEach
	void setUp() throws JSONException {
		jsonObject = new JSONObject();
		jsonObject.put("nome", "novaCompetencia");
		jsonObject.put("conceituacao", "conceituacao da novaCompetencia");
		jsonObject.put("tipo", "OPERACIONAL");
		JSONArray jsonArray = new JSONArray();
		jsonObject.put("idFeedbacks", jsonArray);
	}

	@Test
	public void getCompetencias() throws Exception {
		URI uri = new URI("/competencias/");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void postWithNomeWrongFormat() throws Exception {
		URI uri = new URI("/competencia/");
		jsonObject.put("nome", "novaCompetencia1");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithConceituacaoWrongFormat() throws Exception {
		URI uri = new URI("/competencia/");
		jsonObject.put("conceituacao", "conceituacao1 da novaCompetencia");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithTipoWrongFormat() throws Exception {
		URI uri = new URI("/competencia/");
		jsonObject.put("tipo", "OPERACIONA");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdFeedbacksWrongFormat() throws Exception {
		URI uri = new URI("/competencia/");
		JSONArray jsonArray = new JSONArray();
		jsonArray.put("a");
		jsonObject.put("idFeedbacks", jsonArray);
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdFeedbacksNotInDb() throws Exception {
		URI uri = new URI("/competencia/");
		JSONArray jsonArray = new JSONArray();
		jsonArray.put("10");
		jsonObject.put("idFeedbacks", jsonArray);
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithNomeWrongFormat() throws Exception {
		URI uri = new URI("/competencia/");
		jsonObject.put("nome", "novaCompetencia1");
		jsonObject.put("id", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithConceituacaoWrongFormat() throws Exception {
		URI uri = new URI("/competencia/");
		jsonObject.put("conceituacao", "conceituacao1 da novaCompetencia");
		jsonObject.put("id", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithTipoWrongFormat() throws Exception {
		URI uri = new URI("/competencia/");
		jsonObject.put("tipo", "OPERACIONA");
		jsonObject.put("id", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithIdFeedbacksWrongFormat() throws Exception {
		URI uri = new URI("/competencia/");
		JSONArray jsonArray = new JSONArray();
		jsonArray.put("a");
		jsonObject.put("idFeedbacks", jsonArray);
		jsonObject.put("id", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void putWithIdFeedbacksNotInDb() throws Exception {
		URI uri = new URI("/competencia/");
		JSONArray jsonArray = new JSONArray();
		jsonArray.put("10");
		jsonObject.put("idFeedbacks", jsonArray);
		jsonObject.put("id", "1");
		mockMvc.perform(
				MockMvcRequestBuilders.put(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithCorrectFormat() throws Exception {
		URI uri = new URI("/competencia/");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
}
