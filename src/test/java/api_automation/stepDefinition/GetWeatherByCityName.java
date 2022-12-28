package api_automation.stepDefinition;




import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.jayway.jsonpath.JsonPath;
public class GetWeatherByCityName {
    
    static String  responseCityName;
    String responseCityID;
    Scenario scenario;
    
    @Before
    public void keepScenario(Scenario scenario) {
        this.scenario=scenario;
    }
 
    
    @Given("^User gets weather for \"([^\"]*)\" city when flag is \"([^\"]*)\"$")
    public void user_gets_weather_for_city_when_flag_is(String cityName, String flag) throws Throwable {
        
        if(flag.equalsIgnoreCase("true")) {
            
            RestAssured.baseURI="https://api.openweathermap.org/data/2.5/weather";
            Response response=given().
                                param("q", cityName).
                                param("appid", "eb42dcaceec223e47753a78413ac3f8b").
                              when().get();
            responseCityName=response.prettyPrint();
//          System.out.println(responseCityName);
            scenario.write(responseCityName);
            scenario.embed(responseCityName.getBytes(), "application/json");
            
        }
        
       
    }
    @Then("^User validates response with \"([^\"]*)\"$")
    public void user_validates_response_with(String jsonPath) throws Throwable {
        
        String value=JsonPath.read(responseCityName, jsonPath ).toString();
//      System.out.println("***** ******* "+value);
        scenario.write(value);
        assertNotNull(value);
    
       
    }
    
    
    
    
    
    
    
    
    @Given("^User gets weather for \"([^\"]*)\" city$")
    public void user_gets_weather_for_city(String cityName) throws Throwable {
        RestAssured.baseURI="https://api.openweathermap.org/data/2.5/weather";
        Response response=given().
                            param("q", cityName).
                            param("appid", "eb42dcaceec223e47753a78413ac3f8b").
                          when().get();
        responseCityName=response.prettyPrint();
        System.out.println(responseCityName);
       
    }
    @Then("^User validates \"([^\"]*)\" in response with \"([^\"]*)\"$")
    public void user_validates_in_response_with(String element, String jsonPath) throws Throwable {
       
        String value=JsonPath.read(responseCityName, jsonPath ).toString();
        System.out.println("***** ******* "+value);
        assertNotNull(value);
    }
    
    
    @Given("^User gets weather for \"([^\"]*)\" id$")
    public void user_gets_weather_for_id(String cityID) throws Throwable {
        RestAssured.baseURI="https://api.openweathermap.org/data/2.5/weather";
        Response response=given().
                            param("id", cityID).
                            param("appid", "eb42dcaceec223e47753a78413ac3f8b").
                          when().get();
        
        responseCityID=response.prettyPrint();
    }
    @Then("^User compares \"([^\"]*)\" in byCityName response with \"([^\"]*)\" in ByCityID response$")
    public void user_compares_in_byCityName_response_with_in_ByCityID_response(String element, String jsonPath) throws Throwable {
        
        String valueByID=JsonPath.read(responseCityID, jsonPath).toString();
        String valueByName=JsonPath.read(responseCityName, jsonPath).toString();
        
        System.out.println("Element :  "+element);
        System.out.println("ValueByID:  "+valueByID);
        System.out.println("ValuByName:  "+valueByName);
        
        
        assertEquals(valueByID, valueByName);
        
        
        
        
       
    }
}