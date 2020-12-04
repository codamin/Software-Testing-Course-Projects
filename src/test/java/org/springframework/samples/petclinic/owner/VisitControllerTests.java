package org.springframework.samples.petclinic.owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.test.web.servlet.MockMvc;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VisitController.class)
public class VisitControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean VisitRepository visitRepositoryMock;
	@MockBean PetRepository petRepositoryMock;
	@MockBean OwnerRepository ownerRepositoryMock;

	@Test
	public void initNewVisitFormTest() {
		Pet pet = new Pet();
		pet.setName("Heey");
		pet.setId(14);
		
		when(petRepositoryMock.findById(14)).thenReturn(pet);

		try {
			this.mockMvc.perform(get("/owners/11/pets/14/visits/new")).
				andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("pets/createOrUpdateVisitForm"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void processNewVisitFormTest(){
		Owner owner = new Owner();
		owner.setId(11);
		ArrayList<Owner> arrayListOwner = new ArrayList<Owner>();
		arrayListOwner.add(owner);

		Pet pet = new Pet();
		pet.setId(14);
		ArrayList<Pet> arrayListPet = new ArrayList<Pet>();
		arrayListPet.add(pet);
		when(petRepositoryMock.findById(14)).thenReturn(pet);
		when(ownerRepositoryMock.findById(11)).thenReturn(owner);

		try {
			this.mockMvc.perform(post("/owners/11/pets/14/visits/new").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity(
					"date", "2020-08-05",
					"description", "Her+Third+Year+Birthday",
					"petId", "14"
				))).andDo(print()).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/{ownerId}"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String buildUrlEncodedFormEntity(String... params) {
		if( (params.length % 2) > 0 ) {
			throw new IllegalArgumentException("Need to give an even number of parameters");
		}
		StringBuilder result = new StringBuilder();
		for (int i=0; i<params.length; i+=2) {
			if( i > 0 ) {
				result.append('&');
			}
			try {
				result.
					append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
					append('=').
					append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
			}
			catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return result.toString();
	}
}
