@delete_book
Feature: Test suite delete function

  @basic
  Scenario: Successfully delete a book
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the Edit button
    And Enter book name with value "test xoa"
    Then I click on update button
    And Accept the alert
    When Navigate to the product management page
    And Click on the delete button of first book
    And I accept alert
    And I accept alert
    And I validate the name of product different value "test xoa"

  @alternative
  Scenario: cancel delete a book
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the Edit button
    And Enter book name with value "test - Hướng"
    Then I click on update button
    And Accept the alert
    When Navigate to the product management page
    And Click on the delete button of first book
    And I cancel alert
    And I validate the name of product with value "test - Hướng"

  @alternative
  Scenario: refresh before accept delete a book
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the Edit button
    And Enter book name with value "test - Hướng"
    Then I click on update button
    And Accept the alert
    When Navigate to the product management page
    And Click on the delete button of first book
    And I refresh this website
    And I validate the name of product with value "test - Hướng"