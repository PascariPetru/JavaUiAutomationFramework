Feature: Register Flow Feature Test Suite


  Scenario: Access the Account page after successfull registration
    Given Home Page is accessed
    And RegisterPage is accessed from HomePage menu
    When The Registration form is completed with valid random data
    And the privacyToggle is enabled
    And ContinueButton is clicked
    Then the new page url contains "success" keyword


  Scenario: User remains on Register Page when continue button is not clicked during the register flow
    Given Home Page is accessed
    And RegisterPage is accessed from HomePage menu
    When The Registration form is completed with valid random data
    And the privacyToggle is enabled
    Then the new page url contains "register" keyword