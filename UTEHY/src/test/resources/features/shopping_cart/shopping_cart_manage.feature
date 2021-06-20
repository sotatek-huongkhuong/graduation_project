Feature: Test function of  shopping cart

  Scenario: check item in cart when we close broser
    Given I login to the user page
    And Click on the cart button
    And validate text with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Click on the cart button
    When validate text with value "có 1 sản phẩm trong giỏ hàng"
    And I close browser
    And I login to the user page
    Then validate text with value "có 1 sản phẩm trong giỏ hàng"

  Scenario: check count of the item in the cart when we select that item twice
    Given I login to the user page
    And Click on the cart button
    And validate text with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Click on the cart button
    Then validate coutn of the item is "2"

  Scenario: check the item in the cart when we select two different item
    Given I login to the user page
    And Click on the cart button
    And validate text with value "Chưa có sản phẩm nào trong giỏ hàng"
    And Enter search input with value "Đắc nhân tâm" and search
    And I click on the book
    And I click on add to card
    And Enter search input with value "thiếu nhi" and search
    And I click on the book
    And I click on add to card
    And Click on the cart button
    Then validate name of first item is "Đắc nhân tâm"
    And validate the name of And item is "thiếu nhi"

