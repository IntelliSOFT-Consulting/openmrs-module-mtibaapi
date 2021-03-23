package org.openmrs.module.mtibaapis.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import okhttp3.OkHttpClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class MtibaapisControllerTest {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	private MockMvc mockMvc;
	
	private MtibaapisController controllers;

	private String treatmentCode;
	
	@Before
	public void startService() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new MtibaapisController()).build();
		treatmentCode = "MTI44827";
	}
	
	/**
	 * @see MtibaapisController#getTreatmentInfo(String)
	 * @verifies return a proper response
	 */
	@Test
	public void getTreatmentInfo_shouldReturnTreatmentInformationViaRest() throws Exception {
		mockMvc.perform(get(String.format("/rest/v1/mtibaapi/treatments/%s", treatmentCode)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * @see MtibaapisController#getTreatmentInfo()
	 * @verifies return treatment information
	 */
	@Test
	public void getTreatmentInfo_shouldReturnTreatmentInformation() throws Exception {
		OkHttpClient client = new OkHttpClient();
		
		MtibaapisController mtibaapisController = new MtibaapisController();
		MtibaResponse mtibaResponse = mtibaapisController.getTreatmentInfo(treatmentCode);
		Assert.assertNotNull(mtibaResponse);
	}
	
}
