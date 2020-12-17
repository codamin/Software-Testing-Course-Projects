package org.springframework.samples.petclinic.owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.validation.Errors;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetValidatorTests {

	@Mock private Pet petMock;
	@Mock private Errors errorsMock;
	@InjectMocks private PetValidator petValidatorMock;

	@Test
	public void test1() {
		when(petMock.getName()).thenReturn("pet");
		when(petMock.isNew()).thenReturn(true);
		PetType petType = new PetType();
		when(petMock.getType()).thenReturn(petType);
		LocalDate birthDate = LocalDate.of(1985,8,6);
		when(petMock.getBirthDate()).thenReturn(birthDate);
		this.petValidatorMock.validate(petMock, errorsMock);
		verify(errorsMock, times(0)).rejectValue("name", "required", "required");
		verify(errorsMock, times(0)).rejectValue("type", "required", "required");
		verify(errorsMock, times(0)).rejectValue("birthDate", "required", "required");
	}

	@Test
	public void test2() {
		when(petMock.getName()).thenReturn(null);
		when(petMock.isNew()).thenReturn(true);
		when(petMock.getType()).thenReturn(null);
		when(petMock.getBirthDate()).thenReturn(null);
		this.petValidatorMock.validate(petMock, errorsMock);
		verify(errorsMock, times(1)).rejectValue("name", "required", "required");
		verify(errorsMock, times(1)).rejectValue("type", "required", "required");
		verify(errorsMock, times(1)).rejectValue("birthDate", "required", "required");
	}

	@Test
	public void test3() {
		when(petMock.getName()).thenReturn(null);
		when(petMock.isNew()).thenReturn(false);
		when(petMock.getBirthDate()).thenReturn(null);
		this.petValidatorMock.validate(petMock, errorsMock);
		verify(errorsMock, times(1)).rejectValue("name", "required", "required");
		verify(errorsMock, times(0)).rejectValue("type", "required", "required");
		verify(errorsMock, times(1)).rejectValue("birthDate", "required", "required");
		Pet pet = new Pet();
		Boolean ans = this.petValidatorMock.supports(pet.getClass());
		System.out.println(ans);
		assertEquals(true, ans);
	}
}