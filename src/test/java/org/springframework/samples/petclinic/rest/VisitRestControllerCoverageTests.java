package org.springframework.samples.petclinic.rest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.postgresql.core.VisibleBufferedInputStream;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class VisitRestControllerCoverageTests {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock private BindingResult bindingResultMock;
	@Mock private Visit visitMock;
	@Mock private ClinicService clinicServiceMock;
	@InjectMocks private VisitRestController visitRestControllerMock;

	@Test
	public void UpdateVisit_NullVisit() {
		when(bindingResultMock.hasErrors()).thenReturn(false);
		Pet pet = new Pet();
		visitRestControllerMock.updateVisit(1, null, bindingResultMock);
	}

	@Test
	public void UpdateVisit_NullVisitGetPet() {
		when(bindingResultMock.hasErrors()).thenReturn(false);
		lenient().when(visitMock.getPet()).thenReturn(null);
		Visit visit = new Visit();
		visitRestControllerMock.updateVisit(1, visit, bindingResultMock);
	}

	@Test
	public void UpdateVisit_BindingResultHasError() {
		Visit visit = new Visit();
		when(bindingResultMock.hasErrors()).thenReturn(true);
		Pet pet = new Pet();
		lenient().when(visitMock.getPet()).thenReturn(pet);
		visitRestControllerMock.updateVisit(1, visit, bindingResultMock);
	}

	@Test
	public void UpdateVisit_BindingResultHasErrorNullVisit() {
		when(bindingResultMock.hasErrors()).thenReturn(true);
		Pet pet = new Pet();
		visitRestControllerMock.updateVisit(1, null, bindingResultMock);
	}

	@Test
	public void UpdateVisit_BindingResultsErrorNullVisitGetPet() {
		lenient().when(bindingResultMock.hasErrors()).thenReturn(true);
		lenient().when(visitMock.getPet()).thenReturn(null);
		Visit visit = new Visit();
		visitRestControllerMock.updateVisit(1, visitMock, bindingResultMock);
	}

	@Test
	public void UpdateVisit_NotFoundVisit() {
		when(bindingResultMock.hasErrors()).thenReturn(false);
		Pet pet = new Pet();
		lenient().when(visitMock.getPet()).thenReturn(pet);
		Visit visit = new Visit();
		lenient().when(clinicServiceMock.findOwnerById(anyInt())).thenReturn(null);
		visitRestControllerMock.updateVisit(1, visitMock, bindingResultMock);
	}

	@Test
	public void UpdateVisit_NoProblem() {
		when(bindingResultMock.hasErrors()).thenReturn(false);
		Pet pet = new Pet();
		lenient().when(visitMock.getPet()).thenReturn(pet);
		Visit visit = new Visit();
		lenient().when(clinicServiceMock.findVisitById(anyInt())).thenReturn(visit);
		visitRestControllerMock.updateVisit(1, visitMock, bindingResultMock);
	}
}
