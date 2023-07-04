Feature: Register Flow Feature Test Suite


  Scenario: Access the Account page after successfull registration
    Given Home Page is accessed
    And RegisterPage is accessed from HomePage menu
    When The Registration form is completed with valid random data
    And "privacyToggle" from "RegisterPage" is clicked
    And "continueButton" from "RegisterPage" is clicked
    Then the new page url contains "token" keyword


  Scenario: User remains on Register Page when continue button is not clicked during the register flow
    Given Home Page is accessed
    And RegisterPage is accessed from HomePage menu
    When The Registration form is completed with valid random data
    And "privacyToggle" from "RegisterPage" is clicked
    Then the new page url contains "register" keyword

  @run
  Scenario Outline: Error messages are displayed when trying to register with invalid <attribute> data
    Given Home Page is accessed
    And RegisterPage is accessed from HomePage menu
    And the registration form is completed with the following data:
      | firstName | <firstName> |
      | lastName  | <lastName>  |
      | email     | random      |
      | password  | Random      |
    When "continueButton" from "RegisterPage" is clicked
    Then the following error messages are displayed:
      | Warning: You must agree to the Privacy Policy!   |
      | <attribute> must be between 1 and 32 characters! |
    Examples:
      | attribute  | firstName                     | lastName                            |
      | First Name | asdsfsdfsadsadsdsdfsdfsdfasdf | random                              |
      | Last Name  | random                        | safsadasdgasdfdasfagafsafaefwefwfsd |