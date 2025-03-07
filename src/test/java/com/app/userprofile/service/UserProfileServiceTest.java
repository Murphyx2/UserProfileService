package com.app.userprofile.service;

import com.app.userprofile.Repository.UserRepository;
import com.app.userprofile.model.UserProfile;
import com.app.userprofile.model.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserProfileServiceTest {

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserProfileService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize mocks
	}

	//Set up User
	UserProfile testUser = TestUtils.generateUserProfile(UserType.FREE);

	@Test
	void testSaveNewUserSuccess(){

		// Mock repository behavior
		when(repository.save(testUser)).thenReturn(testUser);

		//
		UserProfile savedUser = service.save(testUser);

		// Assert: Verify the result
		assertNotNull(savedUser);
		assertEquals(TestUtils.firstName, savedUser.getFirstName());
		assertEquals(TestUtils.lastName, savedUser.getLastName());
		assertEquals(TestUtils.email, savedUser.getEmail());

		// Verify: Ensuring that repository save was called exactly once
		verify(repository, times(1)).save(testUser);
	}


}
