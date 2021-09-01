Feature: Login

Background:
  Given I'm on the MealB landing page

  @MB-001 @Smoke
  Scenario: User can login successfully
      When I click on the Sign In button
      Then I enter MealB username as "elnar"
      And I enter MealB password as "mart12345"
      Then I click on the Login button
      Then I am successfully logged in as "Elnar Mirza"

  @MB-002 @Smoke
  Scenario: User should not login with incorrect info
     When I login with username as "abc" and password as "abc"
     Then I should see error message as "Invalid username or password blah blah"

  @MB-005
  Scenario: Error message with missing password
    When I click on the Sign In button
    #I do not enter password
    #Then I see error message