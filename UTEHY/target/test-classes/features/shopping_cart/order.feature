@order
Feature: Test order function

  @alternative
  Scenario: do not enter surname of customer
    Given I login to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Enter search input with value "Loài người trong kỷ nguyên trí tuệ nhân tạo" and search
    And I click on the book
    And I click on add to card
    And validate name of first item is "Đắc nhân tâm"
    And validate the name of second item is "Loài người trong kỷ nguyên trí tuệ nhân tạo"
    And validate count of first book with "1"
    And validate number of items in cart with "2"
    When I click on Order button
    And Enter the name with "a"
    And Enter the email with "a"
    And Enter the address with "a"
    And Enter the phone number with "a"
    Then I click on button continue
    And i validate color of textbox surname with "red"

  @alternative
  Scenario: do not enter name of customer
    Given I login to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Enter search input with value "Loài người trong kỷ nguyên trí tuệ nhân tạo" and search
    And I click on the book
    And I click on add to card
    And validate name of first item is "Đắc nhân tâm"
    And validate the name of second item is "Loài người trong kỷ nguyên trí tuệ nhân tạo"
    And validate count of first book with "1"
    And validate number of items in cart with "2"
    When I click on Order button
    And Enter the surname with "a"
    And Enter the email with "a"
    And Enter the address with "a"
    And Enter the phone number with "a"
    Then I click on button continue
    And i validate color of textbox name with "red"

  @alternative
  Scenario: do not enter email of customer
    Given I login to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Enter search input with value "Loài người trong kỷ nguyên trí tuệ nhân tạo" and search
    And I click on the book
    And I click on add to card
    And validate name of first item is "Đắc nhân tâm"
    And validate the name of second item is "Loài người trong kỷ nguyên trí tuệ nhân tạo"
    And validate count of first book with "1"
    And validate number of items in cart with "2"
    When I click on Order button
    And Enter the surname with "a"
    And Enter the name with "a"
    And Enter the address with "a"
    And Enter the phone number with "a"
    Then I click on button continue
    And i validate color of textbox email with "red"

  @alternative
  Scenario: do not enter address of customer
    Given I login to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Enter search input with value "Loài người trong kỷ nguyên trí tuệ nhân tạo" and search
    And I click on the book
    And I click on add to card
    And validate name of first item is "Đắc nhân tâm"
    And validate the name of second item is "Loài người trong kỷ nguyên trí tuệ nhân tạo"
    And validate count of first book with "1"
    And validate number of items in cart with "2"
    When I click on Order button
    And Enter the surname with "a"
    And Enter the name with "a"
    And Enter the email with "a"
    And Enter the phone number with "a"
    Then I click on button continue
    And i validate color of textbox address with "red"

  @alternative
  Scenario: do not enter phone number of customer
    Given I login to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Enter search input with value "Loài người trong kỷ nguyên trí tuệ nhân tạo" and search
    And I click on the book
    And I click on add to card
    And validate name of first item is "Đắc nhân tâm"
    And validate the name of second item is "Loài người trong kỷ nguyên trí tuệ nhân tạo"
    And validate count of first book with "1"
    And validate number of items in cart with "2"
    When I click on Order button
    And Enter the surname with "a"
    And Enter the name with "a"
    And Enter the email with "a"
    And Enter the address with "a"
    Then I click on button continue
    And i validate color of textbox phone number with "red"

