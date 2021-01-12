package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;
import stepDefinations.placeAPIStepsD;

public class Hooks {

	@Before("@deletePlace")
	public void beforeDeletePlaceScenario() throws IOException
	{
		placeAPIStepsD objplaceAPIStepsD = new placeAPIStepsD();
		
		if(placeAPIStepsD.strPlaceID == null)
		{
			objplaceAPIStepsD.add_Place_Payload_with("NameFromHook", "IndiaMart", "Telagu");
			objplaceAPIStepsD.user_calls_with_http_request("AddPlaceAPI", "POST");
			objplaceAPIStepsD.verify_created_maps_to_using("place_id", "NameFromHook", "GetPlaceAPI");
		}
	}
}
