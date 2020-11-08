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

//	public void updateOwner_NoProblem() {
//
//	}

//	@Test
//	public void UpdateOwner_

	//State Verification
	//Test Stub
//	@Test void FindOwner_ReturnsCorrectOwner_IfOwnerIdIsValid() {
//		//stubbing
//		when()
//		when(ownerMock.getId()).thenReturn(1);
//		when(ownerRepositoryMock.findById(anyInt())).thenReturn(ownerMock);
//
//		//exersise
//		Owner foundOwner = petService.findOwner(1);
//
//		assertEquals(foundOwner.getId(), 1);
//	}
////
//	//Behavioural Verification
//	//Mock
//	@Test void FindOwner_CallOwnerFindById() {
//		//exersice
//		petService.findOwner(1);
//		//verify
//		verify(ownerRepositoryMock, times(1)).findById(1);
//	}
//
//	//Behavioural Verification
//	//Mock
//	@Test
//	public void NewPet_CallsLoggerInfo() {
//		//exercise
//		petService.newPet(ownerMock);
//		//verify
//		verify(loggerMock, times(1)).info(anyString(), anyInt());
//	}
//
//	//State Verification
//	//Dummy Object
//	@Test
//	public void NewPet_ReturnsPet_IfOwnerIsGiven() {
//		//exercise
//		Pet pet = petService.newPet(ownerMock);
//		//verify
//		assertNotNull(pet);
//	}
//
//	//Behavioural Verification
//	//Mock
//	@Test
//	public void NewPet_CallsOwnerGetId_IfOwnerIsGiven() {
//		//exercise
//		Pet pet = petService.newPet(ownerMock);
//		//verify
//		verify(ownerMock, times(1)).getId();
//	}
//
//	//Behavioural Verification
//	//Mock
//	@Test
//	public void NewPet_CallsOwnerAddPet_IfOwnerIsGiven() {
//		//exercise
//		petService.newPet(ownerMock);
//		//verify
//		verify(ownerMock, times(1)).addPet(any(Pet.class));
//	}
//
//	//Behavioural Verification
//	//Mock
//	@Test void FindPet_CallsLoggerInfo() {
//		//exercise
//		petService.findPet(1);
//		//verify
//		verify(loggerMock, times(1)).info(anyString(), anyInt());
//	}
//
//	//Behavioural Verification
//	//Mock
//	@Test void FindPet_CallsPetTimedCacheGetWithGivenId_IfIdIsGiven() {
//		//exercise
//		petService.findPet(1);
//		//verify
//		verify(petTimedCacheMock, times(1)).get(1);
//	}
//
//	//Behavioural Verification
//	//Mock
//	@Test void SavePet_CallsLoggerInfo() {
//		//exercise
//		petService.savePet(petMock, ownerMock);
//		//verify
//		verify(loggerMock, times(1)).info(anyString(), anyInt());
//	}
//
//	//Behavioural Verification
//	//Mock
//	@Test void SavePet_CallsOwnerAddPetWithGivenPet_IfPetAndOwnerAreGiven() {
//		//exercise
//		petService.savePet(petMock, ownerMock);
//		//verify
//		verify(ownerMock, times(1)).addPet(petMock);
//	}
//
//	//Behavioural Verification
//	//Mock
//	@Test void SavePet_CallsPetTimedCacheSaveWithGivenPet_IfPetAndOwnerAreGiven() {
//		//exercise
//		petService.savePet(petMock, ownerMock);
//		//verify
//		verify(petTimedCacheMock, times(1)).save(petMock);
//	}
}
