package org.openmrs.module.mtibaapis.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// @RunWith(SpringJUnit4ClassRunner.class)
public class MtibaapisControllerTest {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	private MockMvc mockMvc;
	
	private Object controllers;
	
	// @Before
	public void startService() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controllers).build();
	}
	
	/**
	 * @see MtibaapisController#getTreatmentInfo(String)
	 * @verifies return a proper response
	 */
	@Test
	public void getTreatmentInfo_shouldReturnAProperResponse() throws Exception {
		mockMvc.perform(post("/rest/v1/mtibaapis/treatments/MTI44827")).andExpect(status().isOk());
		Assert.fail("Not yet implemented");
	}
	
	/**
	 * @see MtibaapisController#getTreatmentInfo1()
	 * @verifies return treatment information
	 */
	@Test
	public void getTreatmentInfo1_shouldReturnTreatmentInformation() throws Exception {
		OkHttpClient client = new OkHttpClient();
		
		MtibaapisController mtibaapisController = new MtibaapisController();
		mtibaapisController.getTreatmentInfo1();
		
	}
	
}
