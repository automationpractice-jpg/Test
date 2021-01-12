package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.placeAPIpojo;
import pojo.placeAPIpojo_location;

public class TestDataBuild {
	
	public placeAPIpojo addPlacePayloads(String strName, String strAddress, String strLanguage)
	{
		placeAPIpojo objGetPlace = new placeAPIpojo();
		
		objGetPlace.setAccuracy(50);
		objGetPlace.setName(strName);
		objGetPlace.setPhone_number("(+91) 983 893 3937");
		objGetPlace.setAddress(strAddress);
		objGetPlace.setWebsite("http://google.com");
		objGetPlace.setLanguage(strLanguage);
		
		//Set Type --> Need to create ArrayList
		List<String> typeList = new ArrayList<String>();
		typeList.add("shoe park");
		typeList.add("shop");
		objGetPlace.setTypes(typeList);
		
		//Set Location --> Need to create object of Location class
		placeAPIpojo_location objLocation = new placeAPIpojo_location();
		objLocation.setLat(-38.383494);
		objLocation.setLng(33.427362);
		
		objGetPlace.setLocation(objLocation);
		
		return objGetPlace;
	}
	
	public String deletePlacePayload(String strPlaceId)
	{
		return "{\r\n" + 
				"    \"place_id\":\""+strPlaceId+"\"\r\n" + 
				"}";
	}
}
