package org.openmrs.module.mtibaapis.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class MtibaapisControllerTest {
	
	/**
	 * Logger for this class and subclasses
	 */
	protected final Log log = LogFactory.getLog(getClass());
	
	private MockMvc mockMvc;
	
	private MtibaapisController controllers;
	
	private String treatmentCode;
	
	@Before
	public void startService() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		MtibaapisController mtibaapisController = new MtibaapisController();
		mtibaapisController.setUsername("e-hospital");
		mtibaapisController.setPassword("wtgN33Qmlga8JgMag1RYFKdwsaFjcrz6wuk49taeHKW2IrnFGIDhrDBdikPj54HE");
		mtibaapisController.setAuthenticationUrl("https://api.ke-acc.carepay.dev/api/integration/auth/accessToken");
		
		mockMvc = MockMvcBuilders.standaloneSetup(mtibaapisController).build();
		treatmentCode = "MTI44827";
	}
	
	/**
	 * @verifies return a proper response
	 * @see MtibaapisController#getTreatmentInfo(String)
	 */
	@Test
	public void getTreatmentInfo_shouldReturnTreatmentInformationViaRest() throws Exception {
		MvcResult mvcResult = mockMvc
		        .perform(
		            get(String.format("/rest/v1/mtibaapi/treatments/%s", treatmentCode)).contentType(
		                MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.status").exists())
		        .andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
	}
	
	/**
	 * @verifies return treatment information
	 * @see MtibaapisController#getTreatmentInfo()
	 */
	@Test
	public void getTreatmentInfo_shouldReturnTreatmentInformation() throws Exception {
		
		MtibaapisController mtibaapisController = new MtibaapisController();
		MtibaResponse mtibaResponse = mtibaapisController.getTreatmentInfo(treatmentCode);
		Assert.assertNotNull(mtibaResponse);
	}
	
}
