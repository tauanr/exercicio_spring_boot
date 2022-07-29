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
class FeedbackControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private JSONObject jsonObject;

	@BeforeEach
	void setUp() throws JSONException {
		jsonObject = new JSONObject();
		jsonObject.put("tipo", "ELOGIO");
		jsonObject.put("dataDeOcorrencia", "01/01/2000");
		jsonObject.put("evento", "evento ocorrido");
		jsonObject.put("idDestinatario", "2");
		jsonObject.put("idAutor", "1");
		jsonObject.put("idCompetencia", "1");
	}

	@Test
	public void getFeedbacksWithIdInDb() throws Exception {
		Integer id = 2;
		URI uri = new URI("/feedbacks/" + id);

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void getFeedbacksWithIdNotInDb() throws Exception {
		Integer id = 10;
		URI uri = new URI("/feedbacks/" + id);

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithTipoWrongFormat() throws Exception {
		URI uri = new URI("/feedback/");
		jsonObject.put("tipo", "ELOGI");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithDataDeOcorrenciaWrongFormat() throws Exception {
		URI uri = new URI("/feedback/");
		jsonObject.put("dataDeOcorrencia", "100/10/1");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithEventoWrongFormat() throws Exception {
		URI uri = new URI("/feedback/");
		jsonObject.put("evento", "evento ocorrido1");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdDestinatarioNotInDb() throws Exception {
		URI uri = new URI("/feedback/");
		jsonObject.put("idDestinatario", "10");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdDestinatarioWrongFormat() throws Exception {
		URI uri = new URI("/feedback/");
		jsonObject.put("idDestinatario", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdAutorNotInDb() throws Exception {
		URI uri = new URI("/feedback/");
		jsonObject.put("idAutor", "10");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdAutorWrongFormat() throws Exception {
		URI uri = new URI("/feedback/");
		jsonObject.put("idAutor", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdCompetenciaNotInDb() throws Exception {
		URI uri = new URI("/feedback/");
		jsonObject.put("idCompetencia", "10");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithIdCompetenciaWrongFormat() throws Exception {
		URI uri = new URI("/feedback/");
		jsonObject.put("idCompetencia", "a");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void postWithCorrectFormat() throws Exception {
		URI uri = new URI("/feedback/");
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
}
