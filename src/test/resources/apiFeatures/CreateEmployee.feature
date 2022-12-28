Feature: Create new employee

@PostDummy
Scenario Outline: Post call
Given User create request body with "<name>" , "<salary>" , "<age>"
When User sumbits request and gets response
Then Validate if status is "success" in response
Examples:
|name   |salary  |age  |
|Alexa   |650000  |44   |