Feature: Get Employee List from Dummy API


@FirstTest
Scenario: 
Given User gets Employee List from Dummy API
Then  Validate if status is "success"

@SecondTest @2
Scenario: Get unique employee name
Given User gets Employee List from Dummy API
Then User retrieve and print unique employee names

@ThirdTest @3
Scenario: Get employee whose age is greater than Thirty
Given User gets Employee List from Dummy API
Then Find the first names whos age is greater then thirty