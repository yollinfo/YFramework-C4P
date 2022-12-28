Feature: Create new user in Gorest API

@CreateUser @ApiRegression
Scenario Outline: Create User

Given User create request data with "<name>" , "<email>" , "<gender>" , "<status>"
When User sumbits POST request to GOREST api
And User validates if statusCode is 201
Then User retrieves userID from response 
And User deletes data with userID

Examples:
|name     |email   					  |gender   | status |
|Mr.API   |apiTest@yoll.io    |male     |active  |