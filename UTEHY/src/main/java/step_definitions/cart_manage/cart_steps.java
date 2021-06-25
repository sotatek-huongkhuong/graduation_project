package step_definitions.cart_manage;

import constant.base_constant;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.selenium.automationtest.common.CommonBase;

public class cart_steps extends CommonBase {

    Object input_id = By.id("UserName");
    Object input_pass = By.id("PassWord");
    Object button_login = By.xpath("//button[@type='submit']");
    Object input_search = By.xpath("//input[@name='keyword']");
    Object button_search = By.xpath("//button[@type='submit' and @class='pull-right btn-search']");
    Object button_book_1 = By.xpath("//div[@class='product-featured clearfix']//ul[@class='product-list']/li[1]");
    Object button_icon_cart = By.xpath("//i[@class='fa fa-cart-plus fa-2x']");
    Object button_add_to_cart = By.xpath("//div[@class='button-group']/a[@title='Add to Cart']");
    Object label_notification_chuacosanpham = By.xpath("//span[@class='alert alert-danger']");
    Object number_of_items_in_cart = By.xpath("//div[@class='heading-counter warning']/span[2]/b");
    String coutn_of_the_first_book = "//div[@class='order-detail-content']//tbody/tr[1]//input";
    String coutn_of_the_second_book = "//div[@class='order-detail-content']//tbody/tr[2]//input";

    Object button_delete_from_list = By.xpath("//a[text()='Delete item']");
    Object button_first_book_name = By.xpath("//div[@class='order-detail-content']//tbody/tr[1]//td[1]");
    Object button_second_book_name = By.xpath("//div[@class='order-detail-content']//tbody/tr[2]//td[1]");
    Object button_delete_all = By.id("btn_deleteAll");

    Object button_order = By.id("btn_payment");
    Object input_surname = By.id("firstname");
    Object input_name = By.id("lastname");
    Object input_email = By.id("email");
    Object input_address = By.id("address");
    Object input_phone = By.id("phone");
    Object button_continue = By.id("button-payment-method");


    @Given("I login to the user page")
    public void i_login_to_the_user_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        openPage(base_constant.ADMIN_URL, driver);
        type(input_id, "huongkhuong");
        type(input_pass, "huong123");
        click(button_login);
        waitForPageLoaded(driver);
    }

    @Given("I navigate to the user page")
    public void iNavigateToTheUserPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        openPage(base_constant.USER_URL, driver);
    }

    @Given("Click on the cart button")
    public void click_on_the_cart_button() {
        click(button_icon_cart);
        waitForPageLoaded(driver);

    }

    @Given("Enter search input with value {string} and search")
    public void enter_search_input_with_value_and_search(String string) {
        type(input_search, string);
        click(button_search);
        waitForPageLoaded(driver);


    }

    @Given("I click on the book")
    public void i_click_on_the_book() {
        click(button_book_1);
        waitForPageLoaded(driver);
    }

    @Given("I click on add to card")
    public void i_click_on_add_to_card() {
        WebElement element = getElementPresent(button_add_to_cart);
        if (isVisibleInViewport(element, driver)) {
            click(button_add_to_cart);
        } else {
            scrollToElement(button_add_to_cart);
            click(button_add_to_cart);
            waitForPageLoaded(driver);
        }


    }

    @When("I close browser")
    public void i_close_browser() {
        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
        }
    }

    @Then("validate count of first book with {string}")
    public void validateCoutnOfTheItemIs(String arg0) {
        String text = getAttributeFromJavaScript(coutn_of_the_first_book, "value");
        verifyCompare(text, arg0);
    }

    @Then("validate name of first item is {string}")
    public void validateNameOfFirstItemIs(String arg0) {
        String text = getText(button_first_book_name);
        verifyCompare(text, arg0);
    }


    @And("validate notification with value {string}")
    public void validateNotificationWithValue(String arg0) {
        String text = getText(label_notification_chuacosanpham);
        verifyCompare(text, arg0);
    }

    @After
    public void afterScenario() {
        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
        }

    }

    @When("validate text next to cart icon with value {string}")
    public void validateTextNextToCartIconWithValue(String arg0) {
        String text = getText(button_icon_cart);
        verifyCompare(text, arg0);
    }

    @And("validate the name of second item is {string}")
    public void validateTheNameOfSecondItemIs(String arg0) {
        String text = getText(button_second_book_name);
        verifyCompare(text, arg0);
    }

    @And("validate number of items in cart with {string}")
    public void validateNumberOfItemsInCartWith(String arg0) {
        String text = getText(number_of_items_in_cart);
        verifyCompare(text, arg0);

    }


    @When("I click on Order button")
    public void iClickOnOrderButton() {
        click(button_order);
    }

    @And("Enter the surname with {string}")
    public void enterTheSurnameWith(String arg0) {
        type(input_surname, arg0);
    }

    @And("Enter the name with {string}")
    public void enterTheNameWith(String arg0) {
        type(input_name, arg0);

    }

    @And("Enter the email with {string}")
    public void enterTheEmailWith(String arg0) {

        type(input_email, arg0);

    }

    @And("Enter the address with {string}")
    public void enterTheAddressWith(String arg0) {

        type(input_address, arg0);

    }

    @And("Enter the phone number with {string}")
    public void enterThePhoneNumberWith(String arg0) {
        type(input_phone, arg0);

    }

    @Then("I click on button continue")
    public void iClickOnButtonContinue() {
        click(button_continue);
    }

    @And("i validate color of textbox surname with {string}")
    public void iValidateColorOfTextboxSurnameWith(String arg0) {
        String attribute = getAttributeFromJavaScript("//input[@id='firstname']", "style");// border-coler: red
        verifyContains(arg0, attribute);
    }

    @And("i validate color of textbox name with {string}")
    public void iValidateColorOfTextboxNameWith(String arg0) {
        String attribute = getAttributeFromJavaScript("//input[@id='lastname']", "style");// border-coler: red
        verifyContains(arg0, attribute);
    }

    @And("i validate color of textbox address with {string}")
    public void iValidateColorOfTextboxAddressWith(String arg0) {
        String attribute = getAttributeFromJavaScript("//input[@id='address']", "style");// border-coler: red
        verifyContains(arg0, attribute);
    }

    @And("i validate color of textbox email with {string}")
    public void iValidateColorOfTextboxEmailWith(String arg0) {
        String attribute = getAttributeFromJavaScript("//input[@id='email']", "style");// border-coler: red
        verifyContains(arg0, attribute);
    }

    @And("i validate color of textbox phone number with {string}")
    public void iValidateColorOfTextboxPhoneNumberWith(String arg0) {
        String attribute = getAttributeFromJavaScript("//input[@id='phone']", "style");// border-coler: red
        verifyContains(arg0, attribute);
    }

    @Then("I click on Delete All")
    public void iClickOnDeleteAll() {
        click(button_delete_all);
    }
}
