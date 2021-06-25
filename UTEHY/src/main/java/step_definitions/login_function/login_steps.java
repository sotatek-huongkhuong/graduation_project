package step_definitions.login_function;

import constant.base_constant;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.selenium.automationtest.common.CommonBase;

public class login_steps extends CommonBase {

    Object input_id = By.id("UserName");
    Object input_pass = By.id("PassWord");
    Object button_login = By.xpath("//button[@type='submit']");
    Object label_user_name = By.xpath("//img[@class='user-image']/following-sibling::span");
    Object notification_missing_username = By.xpath("//div[@class='validation-summary-errors text-danger']//li[1]");


    @Given("I navigate to admin page")
    public void i_navigate_to_admin_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        openPage(base_constant.ADMIN_URL, driver);
        waitForPageLoaded(driver);
    }

    @When("I enter an admin account {string}")
    public void i_enter_an_existing_admin_account(String string) {
        type(input_id, string);
    }

    @When("I enter the password  {string}")
    public void i_enter_the_password(String string) {
        type(input_pass, string);
    }

    @When("I click on login button")
    public void i_click_on_login_button() {
        click(button_login);
    }

    @Then("I validate the title of website")
    public void i_validate_the_title_of_website() {
        String title_text = driver.getTitle();
        verifyCompare(title_text, "Trang chủ");
    }

    @Then("check validate the name of account")
    public void check_validate_the_name_of_account() {
        String username_text = getText(label_user_name);
        verifyCompare(username_text, "Tran Tiên Manh");
    }

    @Then("I validate the notification with value {string}")
    public void i_validate_the_notification_with_value(String string) {
        String username_text = getText(notification_missing_username);
        verifyCompare(username_text, string);

    }

    @Then("I close the browser and driver")
    public void i_close_the_browser_and_driver() {
        driver.close();
        driver.quit();
    }

    @After
    public void afterScenario() {
        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
        }

    }
}
