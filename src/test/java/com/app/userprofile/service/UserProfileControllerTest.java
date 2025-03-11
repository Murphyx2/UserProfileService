package com.app.userprofile.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.app.userprofile.controller.UserProfileController;
import com.app.userprofile.model.UserProfile;
import com.app.userprofile.security.JwtRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserProfileController.class)
class UserProfileControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private UserProfileService service;

	@MockitoBean
	private JwtRequestFilter jwtRequestFilter;

	@BeforeEach
	void setUp() throws Exception {
		doAnswer(invocation -> {
			HttpServletRequest request = invocation.getArgument(0);
			HttpServletResponse response = invocation.getArgument(1);
			FilterChain chain = invocation.getArgument(2);
			chain.doFilter(request, response);
			return null;
		}).when(jwtRequestFilter).doFilterInternal(any(), any(), any());
	}

	@Test
	@WithMockUser(username = "johndoe@example.com")
	void testCreateUserButMissingInputs() throws Exception {
		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\":\"\",\"email\":\"\"}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.id").value("Id is mandatory"))
				.andExpect(jsonPath("$.email").value("Email is mandatory"));
	}


	@WithMockUser(username = "johndoe@example.com")
	void testCreateUserSuccess() throws Exception {
		UserProfile user = new UserProfile();
		user.setId(UUID.fromString("5fbf8093-3c5d-4e76-ab21-a7f571573c11"));
		user.setEmail("johndoe@example.com");

		when(service.save(any(UserProfile.class))).thenReturn(user);

		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\":\"1\",\"email\":\"johndoe@example.com\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("5fbf8093-3c5d-4e76-ab21-a7f571573c11"));
	}

	@Test
	void testCreateUserUnauthenticated() throws Exception {
		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\":\"\",\"email\":\"\"}"))
				.andExpect(status().isForbidden());
	}
}
