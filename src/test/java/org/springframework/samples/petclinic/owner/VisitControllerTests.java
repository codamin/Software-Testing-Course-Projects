package org.springframework.samples.petclinic.owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.context.annotation.FilterType.CUSTOM;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

//@SpringBootTest

//@Profile("!test")
//@EnableCaching
//@Configuration
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringJUnitWebConfig

//@EnableAutoConfiguration
//@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
//@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(locations =  {"classpath*:/spring/test-context.xml"})
//@WebMvcTest
//@RunWith(SpringRunner.class)
//@SpringBootTest

//@RunWith(MockitoJUnitRunner.class)
//@SpringBootApplication
//@RunWith(SpringRunner.class)
//
//@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest
//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebAxppConfiguration
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//@RunWith(SpringJUnit4ClassRunner.class)

//@SpringBootTest
//@ContextConfiguration(classes = {ContextConfiguration.class})
//@ExtendWith(MockitoExtension.class)

//@SpringBootTest
//@AutoConfigureMockMvc

@WebMvcTest(VisitController.class)
public class VisitControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean VisitRepository visitRepositoryMock;
	@MockBean PetRepository petRepositoryMock;
	@MockBean OwnerRepository ownerRepositoryMock;

//	@Autowired
//	private VisitController visitControllerMock;

//	@BeforeEach
//	public void setup() {
//		// We would need this line if we would not use the MockitoExtension
//		// MockitoAnnotations.initMocks(this);
//		// Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
////		JacksonTester.initFields(this, new ObjectMapper());
//
//		// MockMvc standalone approach
//		this.mockMvc = MockMvcBuilders.standaloneSetup(ownerController, petController, visitController)
////			.setControllerAdvice(new SuperHeroExceptionHandler())
////			.addFilters(new SuperHeroFilter())
//			.build();
//	}

//	List mockList = Mockito.mock(ArrayList.class);

//	@Before
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	public void initNewVisitFormTest() {
		Pet pet = new Pet();
		pet.setName("Heey");
		pet.setId(14);

		when(petRepositoryMock.findById(14)).thenReturn(pet);

		try {
			this.mockMvc.perform(get("/owners/11/pets/14/visits/new")).
				andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("<td>Heey</td>")));
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
				))).andDo(print()).andExpect(status().is3xxRedirection());
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
//@AutoConfigureMockMvc




//	@Before("before")
//	@BeforeAll
//	@BeforeEach
//	public void setup() throws Exception {
//		String formData = "firstName=Hossein&lastName=Entezari&address=Zarch&city=Middle+East&telephone=09022858050";
//		String formData2 = "id=&name=heey&birthDate=2017-08-05&type=cat";
//
//		this.mockMvc.perform(post("http://localhost:" + port + "/owners/new").contentType(MediaType.APPLICATION_FORM_URLENCODED)
//			.content(buildUrlEncodedFormEntity(
//				"firstName", "Hossein",
//				"lastName", "Entezari",
//				"address", "Zarch",
//				"city", "Middle Easti",
//				"telephone", "09022858050"
//			)));
//		this.mockMvc.perform(post("http://localhost:" + port + "/owners/11/pets/new").contentType(MediaType.APPLICATION_FORM_URLENCODED)
//			.content(buildUrlEncodedFormEntity(
//				"id", "",
//				"name", "heey",
//				"birthDate", "2017-08-05",
//				"type", "cat"
//			)));
//	}

