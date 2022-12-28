Feature: Personal Details

  Background:
    Given I'm on the MealB landing page
    Given I login with username as "elnar" and password as "mart12345"
    And I am on the "PersonalInfo" page

  @MB-003 @Parallel
  Scenario: Change Password Verification
    When I enter mismatching passwords
    Then I get password error message "Password confirmation doesn't match Password"