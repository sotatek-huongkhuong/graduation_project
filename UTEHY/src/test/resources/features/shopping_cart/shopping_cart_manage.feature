@edit
Feature: Test suite function of shopping cart

  @basic
  Scenario: check item in cart when i add an item
    Given I login to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    When validate text next to cart icon with value "1 đầu sách"

  @alternative
  Scenario: check item in cart when we close browser and do not login
    Given I navigate to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    When validate text next to cart icon with value "1 đầu sách"
    And I close browser
    And I navigate to the user page
    When validate text next to cart icon with value ""

  @alternative
  Scenario: check item in cart when we close browser
    Given I login to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    When validate text next to cart icon with value "1 đầu sách"
    And I close browser
    And I login to the user page
    When validate text next to cart icon with value "1 đầu sách"

  @alternative
  Scenario: check count of the item in the cart when we select that item twice
    Given I login to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    When validate text next to cart icon with value "2 đầu sách"
    Then validate count of first book with "2"
    And validate number of items in cart with "1"
    And validate name of first item is "Đắc nhân tâm"

  @alternative
  Scenario: check the item in the cart when we select two different item
    Given I login to the user page
    And Click on the cart button
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Enter search input with value "Loài người trong kỷ nguyên trí tuệ nhân tạo" and search
    And I click on the book
    And I click on add to card
    Then validate name of first item is "Đắc nhân tâm"
    And validate the name of second item is "Loài người trong kỷ nguyên trí tuệ nhân tạo"
    And validate count of first book with "1"
    And validate number of items in cart with "2"

  @alternative
  Scenario: check the delete all button
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
    Then I click on Delete All
    And validate notification with value "Chưa có sản phẩm nào trong giỏ hàng"