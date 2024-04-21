Feature: User registration on Basketball England
  As a new user
  I want to register on Basketball England's membership site
  In order to gain access to membership benefits and information


  Scenario: Create user – everything goes as expected and an account is created
    Given I am on the registration page
    When I fill in all required fields correctly including accepting the terms
    And I click on CONFIRM AND JOIN
    Then I should be redirected to the account creation confirmation page
    Then a new account should be successfully created

  Scenario: Create user – last name is missing
    Given I am on the registration page
    When I fill in all required fields correctly and missing last name
    And I click on CONFIRM AND JOIN
    Then I should see an error message that says Last Name is required


  Scenario: Create user – passwords do not match
    Given I am on the registration page
    When I fill in all required fields correctly and miss match the password
    And I click on CONFIRM AND JOIN
    Then I should see an error message that user password do not match


  Scenario: Create user – terms and conditions are not accepted
    Given I am on the registration page
    When I fill in all fields correctly but do not check the box to accept the terms
    And I click on CONFIRM AND JOIN
    Then I should see an error message that says "You must confirm that you have read and accepted our Terms and Conditions”