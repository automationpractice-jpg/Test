package stepDefinations;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class placeAPIStepsD extends Utils{
	
	  RequestSpecification request;
	  Response response;
	  TestDataBuild objTestDataBuild = new TestDataBuild();
	  public static String strPlaceID;
	  
	  //*****************************Add Place **************************
	  
	    @Given("Add Place Payload with {string} {string} {string}")
		public void add_Place_Payload_with(String strName, String strAddress, String strLanguage) throws IOException {
			request = given().spec(placeAPIReqSpecification());		
			request.body(objTestDataBuild.addPlacePayloads(strName,strAddress, strLanguage));
			
		    }

	    @When("user calls {string} with {string} http request")
	    public void user_calls_with_http_request(String strResource, String strHttpMethod) {
	    	//Constructor will be called with value of resource which you pass from feature file
	    	APIResources objResource = APIResources.valueOf(strResource);
	    	
	    	System.out.println(objResource.getResource());
	    	
			if(strHttpMethod.equalsIgnoreCase("POST")) 
			{
		    	response = request.when().post(objResource.getResource());
		    			  // .then().spec(placeAPIRespSpecification()).extract().response();
			}
			else if(strHttpMethod.equalsIgnoreCase("GET"))
			{
				response = request.when().get(objResource.getResource());
				   //.then().spec(placeAPIRespSpecification()).extract().response();	
			}
		    	System.out.println("Response is : "+response.asString());
		    }

	    @Then("the API call got success with status code {int}")
	    public void the_API_call_got_success_with_status_code(Integer int1) throws Throwable {
	    	assertEquals(response.getStatusCode(), 200);
	    	}

		@Then("{string} in response is {string}")
		public void in_response_is(String keyValue, String expectedVal) {
	   		
	        assertEquals(getJSONPath(response, keyValue),expectedVal) ;
		}
		
		//*****************************Get Place **************************
		@Then("verify {string} created maps to {string} using {string}")
		public void verify_created_maps_to_using(String keyField, String strExpName, String strResource) throws IOException {
			String name;
			strPlaceID = getJSONPath(response, keyField);
			System.out.println(strPlaceID);
			request = given().spec(placeAPIReqSpecification()).queryParam(keyField, strPlaceID);
		    
			user_calls_with_http_request(strResource,"GET");
			
			System.out.println("Response is : "+response.asString());
			name = getJSONPath(response, "name");
			assertEquals(name, strExpName);
		}
		
		//*****************************Delete Place **************************
		@Given("Delete Place Payload")
		public void delete_Place_Payload() throws IOException {
		    
			request = given().spec(placeAPIReqSpecification());
			request.body(objTestDataBuild.deletePlacePayload(strPlaceID));
			
		}

}
