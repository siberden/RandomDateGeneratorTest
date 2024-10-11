
Feature: Random Date Generator

  Scenario Outline: Generate random dates with invalid input
    Given I open the random date generator page
    When I enter "<value>" in the field
    And I enter "<start date>" as start date
    And I enter "<end date>" as end date
    And I click the generate button

    Examples:
      | value | start date | end date |
      | -1   | 2020-01-01 00:00:00 | 2099-12-31 23:59:59 |
