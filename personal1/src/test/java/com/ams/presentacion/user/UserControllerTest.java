package com.ams.presentacion.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
@ActiveProfiles("test")
@WithMockUser(username = "admin", password = "$2a$04$7AgexY1BG1hhtju15XEpUe2UPb3cHFDr35c7aOp0np3u7Ddu1Xv4G", roles = "ADMIN")
public class UserControllerTest {

	private static final String BASE_URL = "/users";

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	JdbcTemplate jdbcTemplate;

	// Used for converting heroes to/from JSON
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void initTests() {
		jdbcTemplate.execute("delete from appuser; "
				+ "insert into appuser(id,username, password) VALUES (1,'admin', '$2a$04$7AgexY1BG1hhtju15XEpUe2UPb3cHFDr35c7aOp0np3u7Ddu1Xv4G');");
	}

	@Test
	public void contextLoads() {
		assertThat(jdbcTemplate).isNotNull();
		assertThat(mockMvc).isNotNull();
	}
	/*
	 * @Test public void getUserByIdTest() throws Exception { String requestResponse
	 * = "    \"message\": \"Se han recuperado los datos\",\r\n" +
	 * "    \"result\": {\r\n" + "        \"id\": 1,\r\n" +
	 * "        \"username\": \"admin\",\r\n" +
	 * "        \"password\": \"$2a$04$7AgexY1BG1hhtju15XEpUe2UPb3cHFDr35c7aOp0np3u7Ddu1Xv4G\",\r\n"
	 * + "        \"lastConnection\": null,\r\n" +
	 * "        \"connectionNumber\": 0\r\n" + "    },\r\n" +
	 * "    \"httpStatus\": \"OK\""; RequestBuilder request =
	 * MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON);
	 * MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
	 * .andExpect(content().string(requestResponse)).andReturn(); }
	 */

	private ResultActions invokeGetUserById(int id) throws Exception {
		return mockMvc
				.perform(get(BASE_URL+"/"+id).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
	}

	@SuppressWarnings("unused")
	@Test
	public void getUserByIdTest() throws Exception {
		int id = 1;
		MvcResult result = invokeGetUserById(id).andExpect(status().isOk())
				.andExpect(jsonPath("$.result.username", is("admin"))).andReturn();
		MockHttpServletResponse response = result.getResponse();
	}
	
}
