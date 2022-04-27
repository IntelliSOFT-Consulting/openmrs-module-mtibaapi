/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.mtibaapis.web.controller;

import com.google.gson.Gson;
import okhttp3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.mtibaapis.api.Utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/${rootArtifactid}/${rootArtifactid}Link.form'.
 */
@Controller
@RequestMapping("/rest/v1/mtibaapi")
public class MtibaapisController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	private String username;
	
	private String password;
	
	private String authenticationUrl;
	
	public String getUsername() {
		if (StringUtils.isNullOrEmptyOrWhiteSpace(username)) {
			username = Context.getAdministrationService().getGlobalProperty("mtibaapi.username");
		}
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		if (StringUtils.isNullOrEmptyOrWhiteSpace(password)) {
			password = Context.getAdministrationService().getGlobalProperty("mtibaapi.password");
		}
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAuthenticationUrl() {
		if (StringUtils.isNullOrEmptyOrWhiteSpace(authenticationUrl)) {
			authenticationUrl = Context.getAdministrationService().getGlobalProperty("mtibaapi.authentication-url");
		}
		return authenticationUrl;
	}
	
	public void setAuthenticationUrl(String authenticationUrl) {
		this.authenticationUrl = authenticationUrl;
	}
	
	public AccessToken getAccessToken() throws IOException {
		
		String username = this.getUsername();
		String password = this.getPassword();
		String authenticationUrl = this.getAuthenticationUrl();
		
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		
		RequestBody body = RequestBody.create(
		    String.format("{\n\"username\": \"%s\",\n\"password\": \"%s\"\n}", username, password), mediaType);
		Request request = new Request.Builder().url(authenticationUrl).post(body)
		        .addHeader("content-type", "application/json").build();
		
		Response response = client.newCall(request).execute();
		
		String responseText = response.body().string();
		log.info(responseText);
		
		Gson gson = new Gson();
		AccessToken accessToken = gson.fromJson(responseText, AccessToken.class);
		return accessToken;
	}
	
	;
	
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	MtibaResponse test() {
		MtibaResponse mtibaResponse = new MtibaResponse();
		mtibaResponse.status = "200";
		mtibaResponse.response = "qwerty";
		
		return mtibaResponse;
		
	}
	
	/**
	 * @should return treatment information
	 * @should return treatment information via Rest
	 */
	@RequestMapping(value = "/treatments/{treatmentCode}", method = RequestMethod.GET)
	public @ResponseBody
	MtibaResponse getTreatmentInfo(@PathVariable String treatmentCode) {
		OkHttpClient client = new OkHttpClient();
		
		MtibaResponse mtibaResponse = new MtibaResponse();
		TreatmentData treatmentData;
		AccessToken accessToken = null;
		try {
			accessToken = getAccessToken();
			Request request = new Request.Builder()
			        .url(String.format("https://api.ke-acc.carepay.dev/api/integration/treatments/%s", treatmentCode)).get()
			        .addHeader("authorization", String.format("Bearer %s", accessToken.getAccessToken()))
			        .addHeader("content-type", "application/json").build();
			
			Response response = client.newCall(request).execute();
			String responseText = response.body().string();
			
			Gson gson = new Gson();
			if (response.networkResponse().code() != 200) {
				MtibaErrorResponse errorResponse = gson.fromJson(responseText, MtibaErrorResponse.class);
				
				mtibaResponse.status = errorResponse.getStatus();
				mtibaResponse.response = errorResponse;
				log.debug(errorResponse);
			} else {
				treatmentData = gson.fromJson(responseText, TreatmentData.class);
				
				mtibaResponse.status = "200";
				mtibaResponse.response = treatmentData;
			}
			
		}
		catch (IOException e) {
			mtibaResponse.status = "404";
			mtibaResponse.response = e;
		}
		
		return mtibaResponse;
	}
	
	;
	
	public static class AccessToken {
		
		String accessToken;
		
		Integer accessTokenTtl;
		
		public String getAccessToken() {
			return accessToken;
		}
		
		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}
	}
}

/**
 * TODOs: Complete the TreatmentData class properties: Done Handle Dynamic Access Token generation
 * Done Test the new end-point on OpenMRS Non-priotity Create a unit test for the endpoint(s) Done
 */

