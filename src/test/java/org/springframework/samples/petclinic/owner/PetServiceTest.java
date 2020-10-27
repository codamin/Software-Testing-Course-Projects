package org.springframework.samples.petclinic.owner;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.samples.petclinic.utility.SimpleDI;

import static org.junit.jupiter.api.Assertions.*;

//"Mockisty" Style is used this test class
public class PetServiceTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock private PetTimedCache petTimedCacheMock;
	@Mock private OwnerRepository ownerRepositoryMock;
	@Mock private Logger loggerMock;
	@Mock private Owner ownerMock;
	@Mock private Pet petMock;


	@InjectMocks private PetService petService;

	@BeforeEach
	void init_mocks() {
		MockitoAnnotations.initMocks(this);
	}

	//Behavioural Verification
	//Mock
	@Test
	public void FindOwner_CallsLoggerInfo() {
		//exercise
		petService.findOwner(1);
		//verify
		verify(loggerMock, times(1)).info(anyString(), anyInt());
	}

	//State Verification
	//Test Stub
	@Test void FindOwner_ReturnsCorrectOwner_IfOwnerIdIsValid() {
		//stubbing
		when(ownerMock.getId()).thenReturn(1);
		when(ownerRepositoryMock.findById(anyInt())).thenReturn(ownerMock);

		//exersise
		Owner foundOwner = petService.findOwner(1);

		assertEquals(foundOwner.getId(), 1);
	}

	//Behavioural Verification
	//Mock
	@Test void FindOwner_CallOwnerFindById() {
		//exersice
		petService.findOwner(1);
		//verify
		verify(ownerRepositoryMock, times(1)).findById(1);
	}

	//Behavioural Verification
	//Mock
	@Test
	public void NewPet_CallsLoggerInfo() {
		//exercise
		petService.newPet(ownerMock);
		//verify
		verify(loggerMock, times(1)).info(anyString(), anyInt());
	}

	//State Verification
	//Dummy Object
	@Test
	public void NewPet_ReturnsPet_IfOwnerIsGiven() {
		//exercise
		Pet pet = petService.newPet(ownerMock);
		//verify
		assertNotNull(pet);
	}

	//Behavioural Verification
	//Mock
	@Test
	public void NewPet_CallsOwnerGetId_IfOwnerIsGiven() {
		//exercise
		Pet pet = petService.newPet(ownerMock);
		//verify
		verify(ownerMock, times(1)).getId();
	}

	//Behavioural Verification
	//Mock
	@Test
	public void NewPet_CallsOwnerAddPet_IfOwnerIsGiven() {
		//exercise
		petService.newPet(ownerMock);
		//verify
		verify(ownerMock, times(1)).addPet(any(Pet.class));
	}

	//Behavioural Verification
	//Mock
	@Test void FindPet_CallsLoggerInfo() {
		//exercise
		petService.findPet(1);
		//verify
		verify(loggerMock, times(1)).info(anyString(), anyInt());
	}

	//Behavioural Verification
	//Mock
	@Test void FindPet_CallsPetTimedCacheGetWithGivenId_IfIdIsGiven() {
		//exercise
		petService.findPet(1);
		//verify
		verify(petTimedCacheMock, times(1)).get(1);
	}

	//Behavioural Verification
	//Mock
	@Test void SavePet_CallsLoggerInfo() {
		//exercise
		petService.savePet(petMock, ownerMock);
		//verify
		verify(loggerMock, times(1)).info(anyString(), anyInt());
	}

	//Behavioural Verification
	//Mock
	@Test void SavePet_CallsOwnerAddPetWithGivenPet_IfPetAndOwnerAreGiven() {
		//exercise
		petService.savePet(petMock, ownerMock);
		//verify
		verify(ownerMock, times(1)).addPet(petMock);
	}

	//Behavioural Verification
	//Mock
	@Test void SavePet_CallsPetTimedCacheSaveWithGivenPet_IfPetAndOwnerAreGiven() {
		//exercise
		petService.savePet(petMock, ownerMock);
		//verify
		verify(petTimedCacheMock, times(1)).save(petMock);
	}
}
