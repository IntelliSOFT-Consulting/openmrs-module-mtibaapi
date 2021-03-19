import kong.unirest.*;
import kong.unirest.json.JSONObject;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.junit.Assert.*;

public class mtibaApiTest {
	
	@Test
	public void shouldReturnStatusOkay() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest
		        .post("https://api.ke-acc.carepay.dev/api/integration/auth/accessToken")
		        .header("Content-Type", "application/json")
		        .body(
		            "{\n\t\"username\": \"e-hospital\",\n\t\"password\": \"wtgN33Qmlga8JgMag1RYFKdwsaFjcrz6wuk49taeHKW2IrnFGIDhrDBdikPj54HE\"\n}")
		        .asJson();
		if (!(response == null)) {
			assertTrue(true);
		}
		System.out.println("This is the response:" + response.getBody());
		JSONObject myObj = response.getBody().getObject();
	}
	
	@Test
	public void shouldReturnTreatmentInfo() throws UnirestException {
		HttpResponse<String> response = Unirest
		        .get("https://api.ke-acc.carepay.dev/api/integration/treatments/{code}")
		        .header(
		            "Authorization",
		            "Bearer eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJlLWhvc3BpdGFsIiwicm9sZXMiOlsiUFJPVklERVJfVVNFUl9NQU5BR0VSIiwiUFJPVklERVJfQ0FTSElFUiIsIlBST1ZJREVSX1JFQ0VQVElPTklTVCIsIlBST1ZJREVSX01FRElDIl0sInVzZXJJZCI6IjU5NjMzIiwiZXhwIjoxNjE1NjM2Mzk5LCJncmFudHMiOnt9fQ.NGtbegRYWWGrTAPBjGUTsDvPv64LycnW9GaN7F6RrEQDiW2qGahPfgTC2q1rnDduFuamhxEtk0UV94M7LZltrgy18gv5Cn8Z7CJ7HN6uq3J5fZAyuhEER9Rqrm79TIBLweK4YAKvRo0tHOGlSF1VGY8n3xuG1cgymroJuCWHgO8lHCAy0_2QF68HDvXXG0InMGhhJ9ZDn75vu8cM5vPLUB3BpZtPwPtsBy-_qQejLcNUfyy_G-IBTMRhLJv-3D3SfTeK5KtJsDPb07MMYvPlVXHIlSSpAUFSxJfDK97zk4ubC3EY2VVwnu17xrlnL8OYYiaI5LONZTfe9lZTAWaa1Q")
		        .header("Content-Type", "application/json").header("", "").routeParam("code", "MTI44827").asString();
		if (!(response == null)) {
			assertTrue(true);
		}
		System.out.println("This is the treatment info:" + response.getBody());
	};
};
