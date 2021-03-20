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

import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/${rootArtifactid}/${rootArtifactid}Link.form'.
 */
@Controller
@RequestMapping("/rest/v1/mtibaapi")
public class MtibaapisController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	public String getAccessToken() {
		HttpResponse<String> response = Unirest
		        .post("https://api.ke-acc.carepay.dev/api/integration/auth/accessToken")
		        .header("Content-Type", "application/json")
		        .body(
		            "{\n\t\"username\": \"e-hospital\",\n\t\"password\": \"wtgN33Qmlga8JgMag1RYFKdwsaFjcrz6wuk49taeHKW2IrnFGIDhrDBdikPj54HE\"\n}")
		        .asString();
		log.info(response.getBody());
		return response.getBody();
	};
	
	/**
	 * @should return a proper response
	 */
	@RequestMapping(value = "/treatments/{treatmentCode}", method = RequestMethod.GET)
	public @ResponseBody
	okhttp3.ResponseBody getTreatmentInfo(@PathVariable("treatmentCode") String treatmentCode) throws IOException,
	        MissingArgumentException {
		OkHttpClient client = new OkHttpClient();
		
		if (treatmentCode == null) {
			throw new MissingArgumentException("Treatment Code is missing");
		}
		
		Request request = new Request.Builder()
		        .url("https://api.ke-acc.carepay.dev/api/integration/treatments/MTI44827")
		        .get()
		        .addHeader(
		            "authorization",
		            "Bearer eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJlLWhvc3BpdGFsIiwicm9sZXMiOlsiUFJPVklERVJfQ0FTSElFUiIsIlBST1ZJREVSX1VTRVJfTUFOQUdFUiIsIlBST1ZJREVSX1JFQ0VQVElPTklTVCIsIlBST1ZJREVSX01FRElDIl0sInVzZXJJZCI6IjU5NjMzIiwiZXhwIjoxNjE1NDc4MTMyLCJncmFudHMiOnt9fQ.hDS_UhKSfHFkrs1jdfaJNU20tFwGSktd_IbJpBBWY6x06of4gETln7fVTDWTppgXkAolzbf_qJvAMFyek1dh_QJUTE3IOfBgGktruMBZk6Nhv7xKBIpdeQC_T7G0f9SXLMaytee3giTyCbNeIIdQnD73BJ5Kq4c1Dvb5DFt8bXyrJDtXHIUyHauGV8BnXUe9W2cn9QGWtVu1tmjKJbGPodN0zuVu9Dcqbpz9CsVBkeZX9HMEyly9AEN9YdoV9pbDf5S_Y-tv6nYqnHbD6PwQXP5Ob9YZOexKQwCNDln-IMVc_KyB54qapsh4wSlA6cM38XVjBxqOEGP82ErICU_uig")
		        .addHeader("content-type", "application/json").build();
		
		Response response = client.newCall(request).execute();
		return response.body();
	};
	
	/**
	 * @should return treatment information
	 */
	@RequestMapping(value = "/treatments", method = RequestMethod.GET)
	public @ResponseBody
	MtibaResponse getTreatmentInfo1() throws IOException, MissingArgumentException {
		OkHttpClient client = new OkHttpClient();
		
		TreatmentData treatmentData = new TreatmentData();
		Request request = new Request.Builder()
		        .url("https://api.ke-acc.carepay.dev/api/integration/treatments/MTI44827")
		        .get()
		        .addHeader(
		            "authorization",
		            "Bearer eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJlLWhvc3BpdGFsIiwicm9sZXMiOlsiUFJPVklERVJfQ0FTSElFUiIsIlBST1ZJREVSX1VTRVJfTUFOQUdFUiIsIlBST1ZJREVSX1JFQ0VQVElPTklTVCIsIlBST1ZJREVSX01FRElDIl0sInVzZXJJZCI6IjU5NjMzIiwiZXhwIjoxNjE1NDc4MTMyLCJncmFudHMiOnt9fQ.hDS_UhKSfHFkrs1jdfaJNU20tFwGSktd_IbJpBBWY6x06of4gETln7fVTDWTppgXkAolzbf_qJvAMFyek1dh_QJUTE3IOfBgGktruMBZk6Nhv7xKBIpdeQC_T7G0f9SXLMaytee3giTyCbNeIIdQnD73BJ5Kq4c1Dvb5DFt8bXyrJDtXHIUyHauGV8BnXUe9W2cn9QGWtVu1tmjKJbGPodN0zuVu9Dcqbpz9CsVBkeZX9HMEyly9AEN9YdoV9pbDf5S_Y-tv6nYqnHbD6PwQXP5Ob9YZOexKQwCNDln-IMVc_KyB54qapsh4wSlA6cM38XVjBxqOEGP82ErICU_uig")
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
			treatmentData =  gson.fromJson(responseText, TreatmentData.class);

			mtibaResponse.status = "200";
			mtibaResponse.response = treatmentData;
		}
		
		// treatmentData 
		return mtibaResponse;
	};
}


/**
 * TODOs: 
 * Complete the TreatmentData class properties
 * Handle Dynamic Access Token generation
 * Test the new end-point on OpenMRS
 * 
 * Non-priotity
 * Create a unit test for the endpoint(s)
 */

