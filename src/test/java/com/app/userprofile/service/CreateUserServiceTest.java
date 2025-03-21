package com.app.userprofile.service;

import com.app.userprofile.Repository.UserRepository;
import com.app.userprofile.exceptions.userprofile.UserAlreadyExistsException;
import com.app.userprofile.domain.userprofile.UserProfile;
import com.app.userprofile.domain.userprofile.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateUserServiceTest {

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
	void saveNewUserSuccess(){

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

	@Test
	void saveNewUserButUserAlreadyExistByEmail(){
		// Mock
		when(repository.existsByEmail(testUser.getEmail())).thenReturn(true);
		when(repository.save(testUser)).thenReturn(testUser);

		// Assert exception on service.save
		UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> service.save(testUser));
		assertEquals(String.format("User of email %s already exists", testUser.getEmail()), exception.getMessage());
		assertEquals("email", exception.getFieldName());
		assertEquals(testUser.getEmail(), exception.getFieldValue());
		verify(repository, never()).save(testUser);
	}
}
