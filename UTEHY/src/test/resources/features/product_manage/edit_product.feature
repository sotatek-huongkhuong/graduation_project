@edit
Feature: Test suite function of  edit product

  @alternative
  Scenario: Do not enter the name
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the Edit button
    And Enter book name with value ""
    And Enter img link with value "iMG LINK- Hướng Test"
    And Select the author
    And Select the publishing company
    And Enter description with value "description- Hướng Test"
    Then I click on update button
    And I validate color of name textbox

  @alternative
  Scenario: Do not enter img link
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the Edit button
    And Enter book name with value "book name- Hướng Test"
    And Enter img link with value ""
    And Select the author
    And Select the publishing company
    And Enter description with value "description- Hướng Test"
    Then I click on update button
    And I validate color of img textbox

  @alternative
  Scenario: Do not enter the description
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the Edit button
    And Enter book name with value "book name- Hướng Test"
    And Enter img link with value "iMG LINK- Hướng Test"
    And Select the author
    And Select the publishing company
    And Enter description with value ""
    Then I click on update button
    And I validate color of description combobox with "red"

  @alternative
  Scenario: Do not enter the name 2
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the Edit button
    And Enter book name with value ""
    And Enter img link with value "iMG LINK- Hướng Test"
    And Select the author
    And Select the publishing company
    And Enter description with value "description- Hướng Test"
    And I click on update button
    And I validate color of name textbox with "red"
    And Enter book name with value "book name- Hướng Test"
    And Clean text in descripton textbox
    When I click on update button
    And I validate color of description combobox with "red"
    And I validate color of name textbox with "black"

  @alternative
  Scenario Outline: Successfully edit a product
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the Edit button
    And Enter book name with value <string>
    And Enter img link with value <string1>
    And Select the author
    And Select the publishing company
    And Enter description with value <string2>
    Then I click on update button
    And Accept the alert
    And I validate the name of product with value <string>
    Examples:
      | string                   | string1                | string2                   |
      | "book name- Hướng Test1" | "iMG LINK- Hướng Test" | "description- Hướng Test" |
      | "book name- Hướng Test2" | "iMG LINK- Hướng Test" | "description- Hướng Test" |

