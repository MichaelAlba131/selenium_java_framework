@severity=blocker
@login @loginFeature @All
Feature: Dafiti login tests
  Responsible for filling out the login screen

  Background:
    * I open the url

  @InvalidAuthenticationSceneryOutlineExample
  Scenario Outline:  "<scenery>" - Login with invalid authentication scenery outline
    When I fill the "<user>" and "<password>"
    Then I validate the home view

    Examples:
      | scenery | user     | password |
      | Dafiti  | teste123 | teste123 |
      | Dafiti2  | teste123 | teste123 |


  @InvalidAuthentication
  Scenario:  Dafiti - Login with invalid authentication
    When I fill the "teste123" and "teste123"
    Then I validate the home view