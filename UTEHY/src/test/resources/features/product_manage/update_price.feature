@edit
Feature: Test suite function of update price

  @basic
  Scenario: Successfully update price
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the edit price button
    And Enter the price with value "123454"
    And Enter the discount with "11"
    Then I click on update price button
    And I validate alert notification with "Thiết lập giá thành công!"
    And I accept alert
    And I validate the price with value "123454"

  @alternative
  Scenario: invalid the price
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the edit price button
    And Enter the price with value ""
    And Enter the discount with "10"
    Then I click on update price button
    And I validate alert notification with "Thiết lập giá thất bại!"
    And I accept alert

  @alternative
  Scenario Outline: invalid the discount
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the edit price button
    And Enter the price with value "<price>"
    And Enter the discount with "<discount>"
    Then I click on update price button
    And I validate color of discount with "<color>"
    Examples:
      | price | discount | color |
      | 1000  | -1       | red   |
      | 1000  | 101      | red   |

  @alternative
  Scenario: cancel update price
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the edit price button
    And Enter the price with value "333333"
    And Enter the discount with "10"
    And I click on update price button
    And I validate alert notification with "Thiết lập giá thành công!"
    And I accept alert
    And Click on the edit price button
    And Enter the price with value "22222"
    And Enter the discount with "10"
    And I click on cancel update price button
    And I validate the price with value "333333"

  @alternative
  Scenario: Do not enter the discount input
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the edit price button
    And Enter the price with value "10000"
    And Enter the discount with ""
    Then I click on update price button
    And I validate alert notification with "Thiết lập giá thất bại!"
    And I accept alert

  @alternative
  Scenario: refresh before click on update price button
    Given I login to the admin page
    When Navigate to the product management page
    And Click on the edit price button
    And Enter the price with value "333333"
    And Enter the discount with "10"
    And I click on update price button
    And I validate alert notification with "Thiết lập giá thành công!"
    And I accept alert
    And Click on the edit price button
    And Enter the price with value "22222"
    And Enter the discount with "10"
    And I refresh this website
    And I validate the price with value "333333"