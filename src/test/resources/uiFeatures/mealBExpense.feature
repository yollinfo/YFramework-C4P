Feature: Expense

  Background:
    Given I'm on the MealB landing page
    Given I login with username as "elnar" and password as "mart12345"
    And I am on the "Expenses" page

  @MB-004 @Parallel @Regression
  Scenario: View Column options in Dropdown
    Then I see below options when clicked on column dropdown
      |option|
      |Expense name|
      |Amount|
      |Type|
      |Expense date|
      |Business purpose|
      |Project name|

  @MB-006
  Scenario: Adding Expense
    And I am on the "Expenses" page
    And I can add expense

  @MB-007 @run
  Scenario: User should be able to create expense on MB
    Then I navigate to the expense modal window
    Then I complete all fields of expense modal window
    Then I click on save button
    And I should be able to verify created expense on expense page

  @MB-007
  Scenario: User should be able to compare data from UI to DB
    Then I navigate to the expense modal window
    Then I complete all fields of expense modal window
    Then I click on save button
    And I should be able to verify created expense on expense page
    And I should be able to verify UI data with DB data
