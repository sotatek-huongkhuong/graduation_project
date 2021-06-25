@login
Feature: Test suite login page

  # đăng nhập thành công với tài khoản và mật khẩu chính xác
  @basic
  Scenario: Successfully logged in with an existing account
    Given I navigate to admin page
    When I enter an admin account "manh"
    And I enter the password  "manh123"
    And I click on login button
    Then I validate the title of website
    And check validate the name of account

    # Không nhập tài khoản
  @alternative
  Scenario: Do not enter username
    Given I navigate to admin page
    And I enter the password  "manh123"
    And I click on login button
    Then I validate the notification with value "Enter a valid username!"

        # Không nhập mật khẩu
  @alternative
  Scenario: Do not enter password
    Given I navigate to admin page
    And I enter an admin account "manh"
    And I click on login button
    Then I validate the notification with value "Enter a valid password!"

  # nhập tài khoản không tồn tại hoặc tài khoản tồn tại và mật khẩu sai
  @alternative
  Scenario Outline: Validate account and password
    Given I navigate to admin page
    When I enter an admin account "<username>"
    And I enter the password  "<password>"
    And I click on login button
    Then I validate the notification with value "<notification>"
    Examples:
      | username                   | password                 | notification                            |
      # Tài khoản không tồn tại
      | manh123a                   | manh123                  | Tài khoản không tồn tại!                |
      # Mật khẩu sai
      | manh                       | manhxxx                  | Mật khẩu không khớp!                    |
      # Tài khoản với độ rộng trên 50 kí tự
      | 12345678901234567890123670 | manh123                  | Tài khoản không được vượt quá 50 kí tự! |
      # Mật khẩu vượt quá 32 kí tự
      | manh                       | 123456789012301234567890 | Mật khảu không được vượt quá 32 kí tự!  |
