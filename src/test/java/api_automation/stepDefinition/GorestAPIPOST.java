package api_automation.stepDefinition;


import api_automation.RequestBuilder.GorestRequestBuilder;
import api_automation.utils.TestBase;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


import org.junit.Assert;

public class GorestAPIPOST extends TestBase{
	String requestData;
	static Response response;
	String userID;
	Scenario scenario;	
	
	@Before
	public void keepScenario(Scenario scenario) {
		this.scenario=scenario;
	}
	
	@Given("User create request data with {string} , {string} , {string} , {string}")
	public void user_create_request_data_with(String name, String email, String gender, String status) throws JsonProcessingException {
		 //Create request in Java object 
		GorestRequestBuilder reqBuilder=new GorestRequestBuilder();
			reqBuilder.setName(name);
			reqBuilder.setEmail(email);
			reqBuilder.setGender(gender);
			reqBuilder.setStatus(status);
			//Convert object to string	
		ObjectMapper obMap = new ObjectMapper();
			requestData=obMap.writerWithDefaultPrettyPrinter().writeValueAsString(reqBuilder);
			//write response to report
			scenario.write(requestData);
			scenario.embed(requestData.getBytes(), "application/json");
	}

	
	@When("User sumbits POST request to GOREST api")
	public void user_sumbits_POST_request_to_GOREST_api() {
		      response=given()
							.header("Authorization", "Bearer "+property.getProperty("gorestAPIKey"))
							.contentType(ContentType.JSON)
							.body(requestData).
		         	  when()
		         	  	 	.post(property.getProperty("gorestApiURI"));
		 
		 String strResponse=response.prettyPrint();

			//write response to file
			scenario.write(strResponse);
	}
	

	@When("User validates if statusCode is {int}")
	public void user_validates_if_statusCode_is(int statusCode) {
	    Assert.assertEquals(statusCode, response.getStatusCode());
	}

	@Then("User retrieves userID from response")
	public void user_retrieves_userID_from_response() {
		
		 userID=JsonPath.read(response.asString(), "$.data.id").toString();
		 scenario.write("User ID::: "+userID);
	   
	}

	@Then("User deletes data with userID")
	public void user_deletes_data_with_userID() {
		Response resp=given()
				        .header("Authorization", "Bearer "+property.getProperty("gorestAPIKey"))
				    .when()
				       .delete(property.getProperty("gorestApiURI")+"/"+userID);
		String strResponse=resp.prettyPrint();
		scenario.write(strResponse);
	}



}