package org.springframework.samples.petclinic.rest;

import org.junit.Rule;
import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OwnerRestControllerCoverageTests {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock private BindingResult bindingResultMock;
	@Mock private ClinicService clinicServiceMock;
	@InjectMocks private OwnerRestController ownerRestController;

	UriComponentsBuilder uriComponentsBuilder = null;


	@Test
	public void UpdateOwner_NullOwner() {
		ownerRestController.updateOwner(1, null, bindingResultMock, uriComponentsBuilder);
	}

	@Test
	public void UpdateOwner_BindingResultHasError() {
		Owner owner = new Owner();
		when(bindingResultMock.hasErrors()).thenReturn(true);
		ownerRestController.updateOwner(1,owner, bindingResultMock, uriComponentsBuilder);
	}

	@Test
	public void UpdateOwner_NotFoundOwner() {
		Owner owner = new Owner();
		when(clinicServiceMock.findOwnerById(anyInt())).thenReturn(null);
		ownerRestController.updateOwner(1, owner, bindingResultMock, uriComponentsBuilder);
	}

	@Test
	public void UpdateOwner_NoProblem() {
		Owner owner = new Owner();
		when(clinicServiceMock.findOwnerById(anyInt())).thenReturn(owner);
		ownerRestController.updateOwner(1, owner, bindingResultMock, uriComponentsBuilder);
	}
}
