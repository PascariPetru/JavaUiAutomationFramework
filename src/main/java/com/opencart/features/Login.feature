Feature: Login Feature Tests Suite


  @run
  Scenario Outline: An error message displayed when login is performed with invalid data
    Given the "https://andreisecuqa.host/index.php?route=account/login&language=en-gb" is accessed
    And the following data is entered into the login form:
      | <email>    |
      | <password> |
    When "loginButton" from "LoginPage" is clicked
    Then the following error messages are displayed:
      | Warning: No match for E-Mail Address and/or Password. |
    Examples:
      | attribute | email             | password    |
      | email     | invalid@email.com | nevermind   |
      | password  | andrei@email.com  | fsadasf1231 |


  @run
  Scenario Outline: A valid user is able to log into the system
    Given the "https://andreisecuqa.host/index.php?route=account/login&language=en-gb" is accessed
    And the following data is entered into the login form:
      | <email>    |
      | <password> |
    When "loginButton" from "LoginPage" is clicked
    Then the current url contains the following keyword: "account"
    Examples:
      | attribute | email            | password    |
      | email     | andrei@email.com | Cc27481145! |
