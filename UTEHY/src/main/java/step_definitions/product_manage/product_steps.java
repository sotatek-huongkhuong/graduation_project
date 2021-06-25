package step_definitions.product_manage;

import constant.base_constant;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.selenium.automationtest.common.CommonBase;

public class product_steps extends CommonBase {
    Object input_id = By.id("UserName");
    Object input_pass = By.id("PassWord");
    Object button_login = By.xpath("//button[@type='submit']");

    Object button_create = By.xpath("//button[text()='Tạo mới']");
    Object input_name = By.id("name");
    Object input_img = By.id("image");
    Object select_publishing = By.id("pub");
    Object select_author = By.id("author");
    Object input_desc = By.id("desc");
    Object button_add = By.id("btnAdd");
    Object label_name_created = By.xpath("//tbody/tr[1]/td[1]");
    Object button_edit = By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[last()]/a[1]");
    Object button_update = By.id("btnUpdate");

    Object button_edit_price = By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[8]/a");
    Object label_price = By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[8]");
    Object input_price = By.id("priceout");
    Object input_discount = By.id("promotionforPrice");
    Object button_update_price = By.id("btnUpdatePrice");
    Object button_cancel_update_price = By.xpath("//button[@id='btnUpdatePrice']//following-sibling::button");
    Object button_delete_first_book = By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[10]/a[@class='btn btn-danger']");

    @When("I login to the admin page")
    public void i_login_to_the_admin_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        openPage(base_constant.ADMIN_URL, driver);
        type(input_id, "manh");
        type(input_pass, "manh123");
        click(button_login);
        waitForPageLoaded(driver);
    }

    @When("Navigate to the product management page")
    public void navigate_to_the_product_management_page() {
        openPage(base_constant.PRODUCT_MANAGE_URL, driver);
        waitForPageLoaded(driver);
    }

    @When("Click on the create button")
    public void click_on_the_create_button() {
        click(button_create);
    }

    @When("Enter book name with value {string}")
    public void enter_book_name_with_value(String string) {
        type(input_name, string);
    }

    @When("Enter img link with value {string}")
    public void enter_img_link_with_value(String string) {
        type(input_img, string);
    }

    @When("Select the author")
    public void select_the_author() {
        selectOptionByValue(select_author, "1");
    }

    @When("Select the publishing company")
    public void select_the_publishing_company() {
        selectOptionByValue(select_publishing, "1");

    }

    @Then("Accept the alert")
    public void accept_the_alert() {
        driver.switchTo().alert().accept();
        pause(2000);
    }

    @Then("I click on save button")
    public void i_click_on_save_button() {
        click(button_add);
        pause(2000);
    }

    @Then("I validate color of name textbox")
    public void i_validate_color_of_name_textbox() {
        String attribute = getAttributeFromJavaScript("//*[@id='name']", "style");
        verifyContains("red", attribute);
    }

    @When("Enter description with value {string}")
    public void enter_description_with_value(String string) {
        type(input_desc, string);
    }

    @Then("I validate the name of product with value {string}")
    public void i_validate_the_name_of_product_with_value(String string) {
        String product_text = getText(label_name_created);
        verifyCompare(product_text, string);
    }

    @After
    public void afterScenario() {
        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
        }
    }

    @And("I validate color of img textbox")
    public void iValidateColorOfImgTextbox() {
        String attribute = getAttributeFromJavaScript("//*[@id='image']", "style");
        verifyContains("red", attribute);
    }

    @And("I validate color of author combobox")
    public void iValidateColorOfAuthorCombobox() {
        String attribute = getAttributeFromJavaScript("//*[@id='author']", "style");
        verifyContains("red", attribute);
    }

    @And("I validate color of publish combobox")
    public void iValidateColorOfPublishCombobox() {
        String attribute = getAttributeFromJavaScript("//*[@id='pub']", "style");
        verifyContains("red", attribute);
    }

    @And("I validate color of description combobox with {string}")
    public void iValidateColorOfDescriptionComboboxWith(String arg0) {
        String attribute = getAttributeFromJavaScript("//*[@id='desc']", "style");
        verifyContains(arg0, attribute);
    }

    @And("I validate color of name textbox with {string}")
    public void iValidateColorOfNameTextboxWith(String arg0) {
        String attribute = getAttributeFromJavaScript("//*[@id='name']", "style");// border-coler: red
        verifyContains(arg0, attribute);
    }

    @And("Clean text in descripton textbox")
    public void cleanTextInDescriptonTextbox() {
        type(input_desc, "");
    }

    @And("Click on the Edit button")
    public void clickOnTheEditButton() {
        click(button_edit);
        pause(2000);
    }

    @Then("I click on update button")
    public void iClickOnUpdateButton() {
        click(button_update);
        pause(2000);
    }

    @And("Click on the edit price button")
    public void clickOnTheEditPriceButton() {
        click(button_edit_price);
    }

    @And("Enter the price with value {string}")
    public void enterThePriceWithValue(String arg0) {
        type(input_price, arg0);
    }

    @And("Enter the discount with {string}")
    public void enterTheDiscountWith(String arg0) {
        type(input_discount, arg0);
    }

    @Then("I click on update price button")
    public void iClickOnUpdatePriceButton() {
        click(button_update_price);
        pause(2000);
    }

    @And("I validate alert notification with {string}")
    public void iValidateAlertNotification(String arg0) {
        String a = getAlertText();
        verifyCompare(arg0, a);
    }

    @And("I validate color of discount with {string}")
    public void iValidateColorOfPriceWith(String arg0) {
        String attribute = getAttributeFromJavaScript("//input[@id='promotionforPrice']", "style");
        verifyContains(arg0, attribute);
    }

    @And("I accept alert")
    public void iAcceptAlert() {
        acceptAlert();
        pause(3000);
    }

    @And("I click on cancel update price button")
    public void iClickOnCancelUpdatePriceButton() {
        click(button_cancel_update_price);
    }

    @And("I validate the price with value {string}")
    public void iValidateThePriceWithValue(String arg0) {
        String text = getText(label_price);
        text = text.replace(".", "");
        text = text.replace("₫", "");
        verifyCompare(arg0, text);
    }

    @And("I refresh this website")
    public void iRefreshThisWebsite() {
        try {
            driver.navigate().refresh();

        } catch (Exception e) {

        }
    }

    @And("Click on the delete button of first book")
    public void clickOnTheDeleteButtonOfFirstBook() {
        click(button_delete_first_book);
    }

    @And("I validate the name of product different value {string}")
    public void iValidateTheNameOfProductWithoutValue(String arg0) {
        String text = getText(label_name_created);
        verifyNotEqual(text, arg0);
    }

    @And("I cancel alert")
    public void iCancelAlert() {
        cancelAlert();
    }
}
