package org.springframework.samples.petclinic.service.clinicService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.service.ClinicServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClinicServiceImplTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	private PetRepository petRepositoryMock;
	@Mock
	private VetRepository vetRepositoryMock;
	@Mock
	private VisitRepository visitRepository;

	@InjectMocks
	private ClinicServiceImpl clinicServiceimpl;

	@Test
	public void visitOwnerPetsDoesNotEnterLoopLastIfTrue() {
		/* stubbing */
		Pet petMock = mock(Pet.class);
		Owner ownerMock = mock(Owner.class);
		Collection<Pet> emptyPets = new ArrayList<Pet>();
		emptyPets.add(petMock);
		when(petRepositoryMock.findByOwner(any())).thenReturn(emptyPets);
		/* verify*/
		try {
			clinicServiceimpl.visitOwnerPets(ownerMock);
		} catch (Exception e) {
			System.out.println("hi");
		}
	}

	@Test
	public void visitOwnerPetsDoesNotEnterLoopThrowException() {
		/* stubbing */
		Owner ownerMock = mock(Owner.class);

		Pet petMock = mock(Pet.class);
		Collection<Pet> emptyPets = new ArrayList<Pet>();

		Vet vetMock = mock(Vet.class);
		vetRepositoryMock.save(vetMock);
		emptyPets.add(petMock);

		when(vetMock.canCurePetTye(any())).thenReturn(true);
		when(petRepositoryMock.findByOwner(any())).thenReturn(emptyPets);
		when(vetRepositoryMock.findAll()).thenReturn(Arrays.asList(vetMock));
		/* verify*/
		clinicServiceimpl.visitOwnerPets(ownerMock);
	}
}
