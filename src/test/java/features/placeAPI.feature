Feature: Validating Place API

@addPlace @regression
Scenario Outline: Verify if Place is being added succesfully using AddPlace API
	Given Add Place Payload with "<name>" "<address>" "<language>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response is "OK"
	And "scope" in response is "APP"
	And verify "place_id" created maps to "<name>" using "GetPlaceAPI"
	
	Examples:
	|name         | |address                         | |language|
	|Tiranga Villa| |Maharashtra - World Trade Center| |Marathi |
#	|Ganesh House | |India - Sea Cross Trade Center  | |Hindi   |



@deletePlace @regression
Scenario: Verify if Delete Place Functionality is working
	Given Delete Place Payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response is "OK"