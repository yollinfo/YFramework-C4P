package api_automation.stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

import com.jayway.jsonpath.JsonPath;

import api_automation.utils.TestBase;
import io.cucumber.java.en.*;
import io.restassured.response.Response;


public class GetGorestUserList extends TestBase {
	
	Response response;
	
	
	@Given("User gets Employee List from Gorest API")
	public void user_gets_Employee_List_from_Gorest_API() {
		  response =given().when().get(property.getProperty("gorestApiURI"));
		  response.prettyPrint();
	}

	@Then("Validate if status code is {int}")
	public void validate_if_status_code_is(int statusCode) {
		Assert.assertEquals(statusCode, response.getStatusCode());  
	}
	
	


	@Then("^Validate if status is \"([^\"]*)\"$")
	public void validate_if_status_is(String expectedValue) throws Throwable {
		String value=JsonPath.read(response, "$.status").toString();
        System.out.println(value);
        assertEquals(expectedValue, value);
	 
	}



	@Then("User retrieve and print unique Gorest user names")
	public void user_retrieve_and_print_unique_Gorest_user_names() {
	       List<String>names=JsonPath.read(response.asString(), "$.data[*].name");       
	        Set<String>uniqueNames=new HashSet<String>();
	        uniqueNames.addAll(names);
//	        System.out.println(uniqueNames);
	    	        
	        for (String string : uniqueNames) {
	            System.out.println(string);
	        }  
	    
	}

	@Then("Find the first names whose gender is male")
	public void find_the_first_names_whose_gender_is_male() {
		List<String>names=JsonPath.read(response.asString(), "$.data[?(@.gender=='male')].name");
		
		System.out.println("*********************************************");
        Set<String>uniqueNames=new HashSet<String>();
        uniqueNames.addAll(names);
        System.out.println(uniqueNames);
  
        
        for (String string : uniqueNames) {
            System.out.println(string);
        }
	}
	
	
	

}
