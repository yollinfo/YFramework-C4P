package api_automation.stepDefinition;

import api_automation.RequestBuilder.Employee;
import api_automation.utils.TestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class DummyAPIPOST extends TestBase{
	String requstbody;
	String response;
	Scenario scenario;	
	@Before
	public void keepScenario(Scenario scenario) {
		this.scenario=scenario;
	}
	@Given("^User create request body with \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void user_create_request_body_with(String name, String salary, String age) throws Throwable {
		//Create request in Java object (emp)
		Employee emp=new Employee();
			emp.setAge(age);
			emp.setName(name);
			emp.setSalary(salary);
		//Convert emp object to string	
		ObjectMapper obMap = new ObjectMapper();
		requstbody=obMap.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
		//write response to report
		scenario.write(requstbody);
		scenario.embed(requstbody.getBytes(), "application/json");
	}
	@When("^User sumbits request and gets response$")
	public void user_sumbits_request_and_gets_response() throws Throwable {
		RestAssured.baseURI=property.getProperty("postDummyURI");		
//		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/create";
		Response resp=given().
				         body(requstbody).
				      when().
				         post();
		response=resp.prettyPrint();
//		response=resp.asString();
		//write response to file
		scenario.write(response);
	}
	@Then("^Validate if status is \"([^\"]*)\" in response$")
	public void validate_if_status_is_in_response(String statusValue) throws Throwable {
		String actualValue=JsonPath.read(response, "$.status");
		scenario.write("Actual Value: "+actualValue +"   Expected Value: "+statusValue);
		assertEquals(statusValue, actualValue);
		
	}
}