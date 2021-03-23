/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.mtibaapis.web.controller;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.*;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/${rootArtifactid}/${rootArtifactid}Link.form'.
 */
@Controller
@RequestMapping("/rest/v1/mtibaapi")
public class MtibaapisController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	public String getAccessToken() throws IOException {
		//TODO Check whether token is still valid
		
		String username = Context.getAdministrationService().getGlobalProperty("mtibaapi.username");
		String password = Context.getAdministrationService().getGlobalProperty("mtibaapi.username");
		String authenticationUrl = Context.getAdministrationService().getGlobalProperty("mtibaapi.authentication-url");
		// String password = "wtgN33Qmlga8JgMag1RYFKdwsaFjcrz6wuk49taeHKW2IrnFGIDhrDBdikPj54HE";
		// String authenticationUrl = "https://api.ke-acc.carepay.dev/api/integration/auth/accessToken";
		OkHttpClient client = new OkHttpClient();
		
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(
		    String.format("{\n\"username\": \"%s\",\n\"password\": \"%s\"\n}", username, password), mediaType);
		Request request = new Request.Builder().url(authenticationUrl).post(body)
		        .addHeader("content-type", "application/json").build();
		
		Response response = client.newCall(request).execute();
		log.info(response.body().toString());
		return response.body().toString();
	};

	/**
	 * @should return treatment information
	 * @should return treatment information via Rest
	 */
	@RequestMapping(value = "/treatments/{treatmentCode}", method = RequestMethod.GET)
	public @ResponseBody
	MtibaResponse getTreatmentInfo(@PathVariable String treatmentCode) throws IOException,
	        MissingArgumentException {
		OkHttpClient client = new OkHttpClient();

		TreatmentData treatmentData = new TreatmentData();
		String accessToken = getAccessToken();
		accessToken = "eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJlLWhvc3BpdGFsIiwicm9sZXMiOlsiUFJPVklERVJfQ0FTSElFUiIsIlBST1ZJREVSX1VTRVJfTUFOQUdFUiIsIlBST1ZJREVSX1JFQ0VQVElPTklTVCIsIlBST1ZJREVSX01FRElDIl0sInVzZXJJZCI6IjU5NjMzIiwiZXhwIjoxNjE2NTk2NjcwLCJncmFudHMiOnt9fQ.V7CgZR9IRdux5A3viwXyPmvmBTNDhTQ537oxlBbt73-v5854YipwPzBWXdRwRiqoXaT73zhVDfKZpB1DrZiWr5qyzeWghI02i8qT473JdM4UDfWML4JQR4LCEFQQVQtpuwgS_eo4HLGoZ0vFsRM9YS0PyCn2XthE4dV8gg4yAavy7ROB6AiOByxn3TTCUfuPUQPqz5YRdFVrvHAZp_O2Ip18iTO8RfxgIenoW0xOPG5HtPP5cjFTQyiYiWy398nNcwzTxj8W_bPNX85waodpr2-A4tySIBBVE3pSmWf3_MeHHjgtuQyH21lH0lNxiXGMVS92AanaBtU3bU0uKRSR-A";
		Request request = new Request.Builder()
		        .url(String.format("https://api.ke-acc.carepay.dev/api/integration/treatments/%s", treatmentCode))
		        .get().addHeader("authorization", String.format("Bearer %s", accessToken))
		        .addHeader("content-type", "application/json").build();
		
		Response response = client.newCall(request).execute();
		String responseText = response.body().string();
		MtibaResponse mtibaResponse = new MtibaResponse();
		
		Gson gson = new Gson();
		if (responseText.contains("error")) {
			MtibaErrorResponse errorResponse = gson.fromJson(responseText, MtibaErrorResponse.class);
			
			mtibaResponse.status = errorResponse.getStatus();
			mtibaResponse.response = errorResponse;
			log.debug(errorResponse);
		} else {
			treatmentData = gson.fromJson(responseText, TreatmentData.class);
			
			mtibaResponse.status = "200";
			mtibaResponse.response = treatmentData;
		}
		
		System.out.println(responseText);
		
		// treatmentData 
		return mtibaResponse;
	};
}

/**
 * TODOs: Complete the TreatmentData class properties: Done Handle Dynamic Access Token generation
 * Test the new end-point on OpenMRS Non-priotity Create a unit test for the endpoint(s)
 */

