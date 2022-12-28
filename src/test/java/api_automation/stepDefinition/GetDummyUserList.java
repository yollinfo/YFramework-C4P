package api_automation.stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jayway.jsonpath.JsonPath;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetDummyUserList {
	
	String response;
	@Given("^User gets Employee List from Dummy API$")
	public void user_gets_Employee_List_from_Dummy_API() throws Throwable {
		  RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/employees";
	        Response resp=given().when().get();
	        response=resp.prettyPrint();

	}

	@Then("^Validate if status is \"([^\"]*)\"$")
	public void validate_if_status_is(String expectedValue) throws Throwable {
		String value=JsonPath.read(response, "$.status").toString();
        System.out.println(value);
        assertEquals(expectedValue, value);
	 
	}
	
	@Then("^User retrieve and print unique employee names$")
	public void user_retrieve_and_print_unique_employee_names() throws Throwable {
	       List<String>names=JsonPath.read(response, "$.data[*].employee_name");       
	        Set<String>uniqueNames=new HashSet<String>();
	        uniqueNames.addAll(names);
//	        System.out.println(uniqueNames);
//	    
//	        
//	        for (String string : uniqueNames) {
//	            System.out.println(string);
//	        }  
	    
	}
	@Then("^Find the first names whos age is greater then thirty$")
	public void find_the_first_names_whos_age_is_greater_then_thirty() throws Throwable {
	 //    List<String>names=JsonPath.read(response, "$.data[?(@.employee_age>30)].empoyee_name"); 
		List<String>names=JsonPath.read(response, "$.data[?(@.employee_age>30)].empoyee_name"); 
	 //    $.books[?(@.price > 100)]
	     
	     System.out.println("*********************************************");
	        Set<String>uniqueNames=new HashSet<String>();
	        uniqueNames.addAll(names);
	        System.out.println(uniqueNames);
	  
	        
	        for (String string : uniqueNames) {
	            System.out.println(string+"*****************************************");
	        } 
	  
	}
	

}
