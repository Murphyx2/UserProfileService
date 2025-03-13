package com.app.userprofile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.app.userprofile.controller.UserProfileController;
import com.app.userprofile.security.JwtUtil;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserProfileController.class,
		excludeAutoConfiguration = {
				org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
				org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
		})
@TestPropertySource(properties = "jwt.secret=test-secret-key")
class UserProfileControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private UserProfileService service;

	@MockitoBean
	private JwtUtil jwtUtils;

	@Test
	void testCreateUserButMissingInputs() throws Exception {
		mockMvc.perform(post("/api/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\":\"\",\"email\":\"\"}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.email").value("Email is mandatory"));
	}
}
