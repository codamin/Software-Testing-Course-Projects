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

import java.util.*;

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
		User user = new User();
		try {
			userServiceImpl.saveUser(user);
		} catch (Exception e) {}
	}

	@Test
	public void getRolesIsEmpty() {
		User userMock = mock(User.class);
		Set<Role> emptyRoles = new HashSet<>();
		when(userMock.getRoles()).thenReturn(emptyRoles);
		try {
			userServiceImpl.saveUser(userMock);
		} catch (Exception e) {}
	}

	@Test
	public void getRolesRoleNOTStartsWithROLE() throws Exception {
		User user = new User();
		user.addRole("NOT_ROLE_");
		userServiceImpl.saveUser(user);
	}

	@Test
	public void getRolesRoleStartsWithROLE() throws Exception {
		User user = new User();
		user.addRole("ROLE_");
		userServiceImpl.saveUser(user);
	}

	@Test
	public void roleGetUserNotNull() throws Exception {
		User userMock = mock(User.class);
		Role role = new Role();
		role.setName("ROLE_");
		role.setUser(userMock);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		when(userMock.getRoles()).thenReturn(roles);
		userServiceImpl.saveUser(userMock);
	}
}
