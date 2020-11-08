package org.springframework.samples.petclinic.service.clinicService;

import org.joda.time.DateTime;
import org.joda.time.Years;
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
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.service.ClinicServiceImpl;

import java.time.LocalDate;
import java.util.*;

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

	@Test
	public void visitOwnerPetsFirstIfTrue() {
		/* stubbing */
		Owner ownerMock = mock(Owner.class);

		Pet petMock = mock(Pet.class);
		Visit visitMock = mock(Visit.class);
		when(petMock.getLastVisit()).thenReturn(Optional.ofNullable(visitMock));
		Collection<Pet> emptyPets = new ArrayList<Pet>();
		emptyPets.add(petMock);

		Vet vetMock = mock(Vet.class);
		vetRepositoryMock.save(vetMock);

		lenient().when(vetMock.canCurePetTye(any())).thenReturn(true);
		when(petRepositoryMock.findByOwner(any())).thenReturn(emptyPets);
		when(vetRepositoryMock.findAll()).thenReturn(Arrays.asList(vetMock));
		/* verify*/
		clinicServiceimpl.visitOwnerPets(ownerMock);
	}

	@Test
	public void visitOwnerPetsSecondIfTrue() {
		/* stubbing */
		Owner ownerMock = mock(Owner.class);

		Pet petMock = mock(Pet.class); //age > 3
		when(petMock.getBirthDate()).thenReturn(new Date(100,1,1));
		Visit visitMock = mock(Visit.class); //days > 364
		when(visitMock.getDate()).thenReturn(new Date(115,1,1));
		when(petMock.getLastVisit()).thenReturn(Optional.ofNullable(visitMock));
		Collection<Pet> emptyPets = new ArrayList<Pet>();
		emptyPets.add(petMock);

		Vet vetMock = mock(Vet.class);
		vetRepositoryMock.save(vetMock);

		lenient().when(vetMock.canCurePetTye(any())).thenReturn(true);
		when(petRepositoryMock.findByOwner(any())).thenReturn(emptyPets);
		when(vetRepositoryMock.findAll()).thenReturn(Arrays.asList(vetMock));
		/* verify*/
		clinicServiceimpl.visitOwnerPets(ownerMock);
	}

	@Test
	public void visitOwnerPetsSecondIfTrue2() {
		/* stubbing */
		Owner ownerMock = mock(Owner.class);

		Pet petMock = mock(Pet.class);
		when(petMock.getBirthDate()).thenReturn(new Date(120,1,1)); //0
		Visit visitMock = mock(Visit.class);
		when(visitMock.getDate()).thenReturn(new Date(100,1,1)); //7
		when(petMock.getLastVisit()).thenReturn(Optional.ofNullable(visitMock));
		Collection<Pet> emptyPets = new ArrayList<Pet>();
		emptyPets.add(petMock);

		Vet vetMock = mock(Vet.class);
		vetRepositoryMock.save(vetMock);

		lenient().when(vetMock.canCurePetTye(any())).thenReturn(true);
		when(petRepositoryMock.findByOwner(any())).thenReturn(emptyPets);
		when(vetRepositoryMock.findAll()).thenReturn(Arrays.asList(vetMock));
		/* verify*/
		clinicServiceimpl.visitOwnerPets(ownerMock);
	}

	@Test
	public void visitOwnerPetsSecondIfFalse2() {
		/* stubbing */
		Owner ownerMock = mock(Owner.class);

		Pet petMock = mock(Pet.class);
		when(petMock.getBirthDate()).thenReturn(new Date(100,1,1));
		Visit visitMock = mock(Visit.class);
		when(visitMock.getDate()).thenReturn(new Date(120,1,1));
		when(petMock.getLastVisit()).thenReturn(Optional.ofNullable(visitMock));
		Collection<Pet> emptyPets = new ArrayList<Pet>();
		emptyPets.add(petMock);

		Vet vetMock = mock(Vet.class);
		vetRepositoryMock.save(vetMock);

		lenient().when(vetMock.canCurePetTye(any())).thenReturn(true);
		when(petRepositoryMock.findByOwner(any())).thenReturn(emptyPets);
		when(vetRepositoryMock.findAll()).thenReturn(Arrays.asList(vetMock));
		/* verify*/
		clinicServiceimpl.visitOwnerPets(ownerMock);
	}
}
