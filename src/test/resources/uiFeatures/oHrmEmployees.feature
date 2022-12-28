Feature: As an administrator I should be able to view all employees and search.
  Background:
    Given I'm on logged in to OrangeHRM as admin

  @hrm-1 @smoke
  Scenario: View All Employees
    And I click on the Employee List
    Then I see employee list table

    #Scenario: Verifying employee count
    #Scenario: Verifying Adding employee (for MB-01 user story.)

  @hrm-2
  Scenario: Adding customer data
    And I click on the Employee List
    #TODO: add new feature for validation of customer email

  @hrm-3
  Scenario: Adding employee with picture
    Then I click on the Add Employee Tab
    Then I fill out employee information
    And I upload an Employee image
    Then I click on Save Employee button
    And I see employee information saved successfully