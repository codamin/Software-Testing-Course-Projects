package org.springframework.samples.petclinic.service.userService;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.samples.petclinic.model.Role;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.samples.petclinic.service.UserServiceImpl;

import java.util.Arrays;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	private UserRepository userRepositoryMock;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	public void getRolesGivesNull() {
		User userMock = mock(User.class);
		when(userMock.getRoles()).thenReturn(null);
		try {
			userServiceImpl.saveUser(userMock);
		} catch (Exception e) {}
	}

	@Test
	public void getRolesGivesNul() throws Exception {
		User user = new User();
		Role roleMock = mock(Role.class);
		user.addRole("NOT_ROLE_");
		userServiceImpl.saveUser(user);
	}
}
