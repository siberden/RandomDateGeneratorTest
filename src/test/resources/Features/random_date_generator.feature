Feature: Random Date Generator Tests

  Background:
    Given I open the random date generator page

  @happyPath
  Scenario: User should be able to generate dates with given details
    When I enter 4 in the count of dates field
    And I select the "MM-DD-YYYY hh:mm:ss" as the date format
    And I enter "2024-10-01 00:00:00" as start date
    And I enter "2024-12-31 23:59:59" as end date
    Then verify that 4 random dates are generated
    Then verify that the generated date are generated in the given  "MM-DD-YYYY hh:mm:ss"

  @count
  Scenario Outline: User should be able to generate dates for the given count
    When I enter <count> in the count of dates field
    Then verify that <count> random dates are generated
    Examples:
      | count |
      | 2     |
      | 4     |
      | 7     |

  @DEFECT
    # Defect 1: User should not be able to enter decimal number of dates
    # Defect 2: Count of dates field has no capacity limit
  Scenario Outline: User should not be able to generate dates with invalid input
    When I enter <count> as count of dates field
    Then verify that the count of dates field only accepts integers
    Examples:
      | count             |
      | 5.01              |
      | 5.000000000000001 |

  @DEFECT
    # Defect 3:  User should not be able to enter negative number of dates
    # Defect 4:  End date should be after start date
  Scenario Outline: User should not be able to generate dates with invalid input: "<description>"
    When I enter <count> in the count of dates field
    And I enter "<start_date>" as start date
    And I enter "<end_date>" as end date
    And I click the "generate" button
    Then I should not see any dates generated

    Examples:
      | description                          | count | start_date          | end_date            |
      | start date should be before end date | 4     | 2099-12-31 23:59:59 | 2020-01-01 00:00:00 |
      | count of dates should be positive    | -1    | 2020-01-01 00:00:00 | 2099-12-31 23:59:59 |


  @dateFormats
  Scenario Outline: Random date generator should follow the given data output format
    When I enter <count> in the count of dates field
    And I select the "<date_format>" as the date format
    Then verify that the generated date are generated in the given  "<date_format>"

    Examples:
      | count | date_format         |
      | 1     | YYYY-MM-DD hh:mm:ss |
      | 1     | MM-DD-YYYY hh:mm:ss |

