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
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import static org.mockito.ArgumentMatchers.anyInt;
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
		visitRestControllerMock.updateVisit(1, null, bindingResultMock);
	}

	@Test
	public void UpdateVisit_NullVisitGetPet() {
		when(visitMock.getPet()).thenReturn(null);
		visitRestControllerMock.updateVisit(1, visitMock, bindingResultMock);
	}

	@Test
	public void UpdateVisit_BindingResultHasError() {
		Visit visit = new Visit();
		when(bindingResultMock.hasErrors()).thenReturn(true);
		visitRestControllerMock.updateVisit(1, visit, bindingResultMock);
	}

	@Test
	public void UpdateOwner_NotFoundOwner() {
		Visit visit = new Visit();
		when(clinicServiceMock.findOwnerById(anyInt())).thenReturn(null);
		visitRestControllerMock.updateVisit(1, visit, bindingResultMock);
	}

	@Test
	public void UpdateOwner_NoProblem() {
		Visit visit = new Visit();
		when(clinicServiceMock.findVisitById(anyInt())).thenReturn(visit);
		visitRestControllerMock.updateVisit(1, visit, bindingResultMock);
	}
}
