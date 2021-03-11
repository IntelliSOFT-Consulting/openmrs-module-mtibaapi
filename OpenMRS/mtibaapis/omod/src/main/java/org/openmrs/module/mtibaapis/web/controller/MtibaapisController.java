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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/${rootArtifactid}/${rootArtifactid}Link.form'.
 */
@Controller
@RequestMapping("/rest/" + RestConstants.VERSION_1 + "/mtibaapi")
public class MtibaapisController extends MainResourceController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/auth/accesstoken", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/auth/getPatientProfile", method = RequestMethod.GET)
	public String getPatientProfile() {
		HttpResponse<String> response = Unirest.get("https://api.ke-acc.carepay.dev/api/integration/auth/accessToken")
		        .header("Authorization", "Bearer " + "Token").asString();
		return response.getBody();
	};
}
