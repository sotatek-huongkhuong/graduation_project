Feature: Test function of  edit product

  @ngoaile
  Scenario: Do not enter the name
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the create button
    And Enter img link with value "iMG LINK- Hướng Test"
    And Select the author
    And Select the publishing company
    And Enter description with value "description- Hướng Test"
    Then I click on save button
    And I validate color of name textbox

  @ngoaile
  Scenario: Do not enter img link
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the create button
    And Enter book name with value "book name- Hướng Test"
    And Select the author
    And Select the publishing company
    And Enter description with value "description- Hướng Test"
    Then I click on save button
    And I validate color of img textbox
  @ngoaile
  Scenario: Do not select the author
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the create button
    And Enter book name with value "book name- Hướng Test"
    And Enter img link with value "iMG LINK- Hướng Test"
    And Select the publishing company
    And Enter description with value "description- Hướng Test"
    Then I click on save button
    And I validate color of author combobox
  @ngoaile
  Scenario: Do not select the publishing company
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the create button
    And Enter book name with value "book name- Hướng Test"
    And Enter img link with value "IMG LINK- Hướng Test"
    And Select the author
    And Enter description with value "description- Hướng Test"
    Then I click on save button
    And I validate color of publish combobox
  @ngoaile
  Scenario: Do not enter the description
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the create button
    And Enter book name with value "book name- Hướng Test"
    And Enter img link with value "iMG LINK- Hướng Test"
    And Select the author
    And Select the publishing company
    Then I click on save button
    And I validate color of description combobox with "red"
  @ngoaile
  Scenario: Do not enter the name 2
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the create button
    And Enter img link with value "iMG LINK- Hướng Test"
    And Select the author
    And Select the publishing company
    And Enter description with value "description- Hướng Test"
    And I click on save button
    And I validate color of name textbox with "red"
    And Enter book name with value "book name- Hướng Test"
    And Clean text in descripton textbox
    When I click on save button
    And I validate color of description combobox with "red"
    And I validate color of name textbox with "black"

  @thanhcong
  Scenario Outline: Successfully added a new product
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the create button
    And Enter book name with value <string>
    And Enter img link with value <string1>
    And Select the author
    And Select the publishing company
    And Enter description with value <string2>
    Then I click on save button
    And Accept the alert
    And I validate the name of product with value <string>
    Examples:
      | string  | string1 | string2             |
      | "book name- Hướng Test1" | "iMG LINK- Hướng Test" | "description- Hướng Test" |
      | "book name- Hướng Test2" | "iMG LINK- Hướng Test" | "description- Hướng Test" |

