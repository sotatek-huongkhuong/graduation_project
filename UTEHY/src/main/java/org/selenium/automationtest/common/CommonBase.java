package org.selenium.automationtest.common;

import java.io.File;
import java.net.Inet4Address;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.cucumber.java.After;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import static org.selenium.automationtest.common.TestLogger.*;

public class CommonBase {
	public WebDriver driver;
	
	protected String baseCoreSolr = "dbmapactiveinfo";
	protected int DEFAULT_TIMEOUT = 180000;
	protected int WAIT_INTERVAL = 1000;
	public int loopCount = 0;
	public final int ACTION_REPEAT = 5;
	public Actions action;

	public void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver)
						.executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, (DEFAULT_TIMEOUT/1000));
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout khi cho trang web hoan thanh load");
		}
	}

	/**
	 * Open page
	 * 
	 * @param pageUrl
	 * @param driver
	 */
	public void openPage(String pageUrl, WebDriver driver) {
		if (pageUrl != null ) {
			driver.get(pageUrl);
		}
		waitForPageLoaded(driver);
		pause(1000);
	}

	/**
	 * Open page at not loaded status, as clear cache
	 * 
	 * @param pageUrl
	 * @param driver
	 */
	public void openPageNotLoad(String pageUrl, WebDriver driver) {
		if (pageUrl != null) {
			driver.get(pageUrl);
			pause(2000);
		}
	}

	/**
	 * pause driver in timeInMillis
	 * 
	 * @param timeInMillis
	 */
	public void pause(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param locator
	 * @return
	 */

	public WebElement getElement(Object locator) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator
				.toString());
		WebElement elem = null;
		try {
			elem = driver.findElement(by);
		} catch (NoSuchElementException e) {
			checkCycling(e, 10);
			pause(WAIT_INTERVAL);
			getElement(locator);
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 10);
			pause(WAIT_INTERVAL);
			getElement(locator);
		}
		return elem;
	}

	/**
	 * get a display element in web page
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getDisplayedElement(Object locator) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator
				.toString());
		WebElement e = null;
		try {
			if (by != null)
				e = driver.findElement(by);
			if (e != null) {
				if (isDisplay(by))
					return e;
			}
		} catch (NoSuchElementException ex) {
			checkCycling(ex, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		} finally {
			loopCount = 0;
		}
		return null;
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getListElement(Object locator) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator
				.toString());
		List<WebElement> elementOptions;
		try {
			elementOptions = driver.findElements(by);
			return elementOptions;
		} catch (NoSuchElementException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			getListElement(locator);
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			getListElement(locator);
		} finally {
			loopCount = 0;
		}
		return null;
	}

	/**
	 * lay cac gia tri thuoc tinh cua 1 mang doi tuong element
	 * 
	 * @param locator
	 * @param attribute
	 * @return
	 */
	public String[] getAttOfListElement(Object locator, String attribute) {
		String[] att = new String[20];
		List<WebElement> list;
		list = getListElement(locator);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				att[i] = list.get(i).getAttribute(attribute);
			}
		}
		return att;
	}

	public String[] getTextOfListElement(Object locator) {
		String[] att = new String[20];
		List<WebElement> list;
		list = getListElement(locator);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				att[i] = list.get(i).getText();
			}
		}
		return att;
	}

	public String getSizeOfListElement(Object locator) {
		return String.valueOf(getListElement(locator).size());
	}

	/**
	 * checking an element is displayed in web page
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isDisplay(Object locator) {
		boolean bool = false;
		WebElement e = getElement(locator);
		try {
			if (e != null)
				bool = e.isDisplayed();
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			isDisplay(locator);
		} finally {
			loopCount = 0;
		}
		return bool;
	}

	/**
	 * check repeat times
	 * 
	 * @param e
	 * @param loopCountAllowed
	 */
	public void checkCycling(Exception e, int loopCountAllowed) {
		info("Co exception xay ra: " + e.getClass().getName());
		if (loopCount > loopCountAllowed) {
			driver.manage().deleteAllCookies();
			Assert.fail("Qua thoi gian nhung khong thay hoac thay doi tuong "
					+ e.getMessage());
		}
		info("Lap lai lan thu " + loopCount);
		loopCount++;
	}

	/**
	 * get an element that present in Web Page
	 * 
	 * @param locator
	 * @param opParams
	 * @return
	 */
	public WebElement getElementPresent(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1] : 1;
		int notDisplayE = opParams.length > 2 ? opParams[2] : 0;
		for (int tick = 0; tick < timeout / WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2) {
				elem = getElement(locator);
			} else {
				elem = getDisplayedElement(locator);
			}
			if (null != elem)
				return elem;
			pause(WAIT_INTERVAL);
		}
		if (isAssert == 1) {
			String date = getDateTime("yyyyMMddHHmmss");
			info("date");
			captureScreen(driver, "Loi_" + date + ".jpg");
			assert false : ("Qua thoi gian " + timeout
					+ "ma khong tim thay doi tuong " + locator);
			quitDriver(driver);
		}
		return null;
	}

	public void type(Object object, String value) {
		WebElement element =getElement(object);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			// WebElement element = getElementPresent(locator, 10000, 0);
			if (element != null) {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.clear();
				element.sendKeys(value);
			} else {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.sendKeys(value);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			type(element, value);
		} catch (NoSuchElementException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			type(element, value);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			type(element, value);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * 
	 * @param locator
	 * @param value
	 */
	public void inputTextJavaScript(Object locator, String value) {
		WebElement e = getElementPresent(locator, DEFAULT_TIMEOUT, 1, 2);
		try {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].innerHTML = '" + value + "'", e);
		} catch (StaleElementReferenceException ex) {
			pause(1000);
			inputTextJavaScript(locator, value);
		}
	}

	/**
	 * get value of element in web page
	 * 
	 * @param locator
	 * @return
	 */
	public String getValue(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		try {
			return getElementPresent(locator, DEFAULT_TIMEOUT, 1, notDisplay)
					.getAttribute("value");
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * click on an element
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void click(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		Actions actions = new Actions(driver);
		try {
			WebElement element = getElementPresent(locator, DEFAULT_TIMEOUT, 1,
					notDisplay);
			if (element.isEnabled()) {
				actions.click(element).perform();
			} else {
				info("Element is not enabled");
				// click(locator, notDisplay);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} catch (NoSuchElementException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} finally {
			loopCount = 0;
		}
	}


	public void clickJavascript(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		try {
			WebElement element = getElementPresent(locator, DEFAULT_TIMEOUT, 1,
					notDisplay);
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView(true);arguments[0].click();",
					element);
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			clickJavascript(locator, opParams);
		}
	}

	/**
	 * 
	 * @param locator
	 * @param opParams
	 * @return
	 */
	public WebElement waitForElementNotPresent(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1] : 1;
		int notDisplayE = opParams.length > 2 ? opParams[2] : 0;

		for (int tick = 0; tick < timeout / WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2) {
				elem = getElement(locator);
			} else {
				elem = getDisplayedElement(locator);
			}
			if (elem == null) {
				return null;
			}
			pause(WAIT_INTERVAL);
		}
		if (isAssert == 1) {
			assert false : ("Timeout after " + timeout
					+ "ms waiting for element not present: " + locator);
		}
		return elem;
	}

	/**
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void check(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0] : 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = getElementPresent(locator, DEFAULT_TIMEOUT, 1,
					notDisplayE);
			boolean a = element.getAttribute("class").contains(
					"ui-state-active");
			if (!element.isSelected() && !a) {
				actions.click(element).perform();
			} else {
				info("Element " + locator + " is already checked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			check(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void uncheck(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0] : 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = getElementPresent(locator, DEFAULT_TIMEOUT, 1,
					notDisplayE);

			if (element.isSelected()) {
				actions.click(element).perform();
			} else {
				info("Element " + locator + " is already unchecked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			pause(1000);
			uncheck(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get absolute path of file
	 * 
	 * @param relativeFilePath
	 * @return
	 */
	public String getAbsoluteFilePath(String relativeFilePath) {
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + relativeFilePath;
		return absolutePath;
	}

	/**
	 * @param locator
	 */
	public void doubleClickOnElement(Object locator) {
		Actions actions = new Actions(driver);
		try {
			WebElement element = getElementPresent(locator);
			actions.doubleClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			pause(1000);
			doubleClickOnElement(locator);
		} finally {
			loopCount = 0;
		}
	}


	/**
	 * get text of element
	 * 
	 * @param locator
	 * @return
	 */
	public String getText(Object locator) {
		WebElement element = null;
		try {
			element = getElementPresent(locator);
			return element.getText();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			return getText(locator);
		} finally {
			loopCount = 0;
		}
	}
	
	/**
	 * 
	 * @param locator
	 * @param safeToSERE
	 * @param opParams
	 */
	public void mouseOver(Object locator, boolean safeToSERE,
			Object... opParams) {
		WebElement element;
		Actions actions = new Actions(driver);
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		try {
			if (safeToSERE) {
				for (int i = 1; i < ACTION_REPEAT; i++) {
					info("Thuc hien mouserover repeat lan thu " + i);
					element = getElementPresent(locator, 2000, 0, notDisplay);
					info("Doi tuong " + element);
					if (element == null) {
						pause(WAIT_INTERVAL);
					} else {
						info("Thuc hien action");
						actions.moveToElement(element).build().perform();
						break;
					}
				}
			} else {
				element = getElementPresent(locator);
				actions.moveToElement(element).build().perform();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			mouseOver(locator, safeToSERE);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void mouseOverAndClick(Object locator, Object... opParams) {
		WebElement element;
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		Actions actions = new Actions(driver);
		try {
			element = getElementPresent(locator, DEFAULT_TIMEOUT, 1, notDisplay);
			actions.moveToElement(element).click(element).build().perform();
		} catch (StaleElementReferenceException e) {
			mouseOverAndClick(locator, opParams);
		}
	}

	/**
	 * quit driver if driver existed
	 * 
	 * @param dr
	 */
	public void quitDriver(WebDriver dr) {
		if (dr.toString().contains("null")) {
			System.out.print("All Browser windows are closed ");
		} else {
			driver.manage().deleteAllCookies();
			dr.quit();
		}
	}

	/**
	 * switch to a frame
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void switchToFrame(Object locator, Object... opParams) {
		info("Switch to frame " + locator);
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		try {
			driver.switchTo().frame(
					getElementPresent(locator, DEFAULT_TIMEOUT, 1, notDisplay));
		} catch (Exception e) {
			switchToFrame(locator, notDisplay);
		}
	}

	/**
	 * back to main frame
	 */
	public void switchToParentFrame() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			switchToParentFrame();
		}
	}

	/**
	 * accept unexpected alert
	 */
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();

		} catch (NoAlertPresentException ex) {
			info("No Alert present");
			;
		}
	}

	/**
	 * get datetime
	 * 
	 * @param format
	 */
	public String getDateTime(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String dateTime = dateFormat.format(cal.getTime());
		info("time at moment is " + dateTime);
		return dateTime;
	}
	/**
	 * 
	 * @param object
	 */
	public void clickTab(By object) {
		if (object != null) {
			WebElement e = getElementPresent(object);
			e.sendKeys(Keys.TAB);
		}
	}

	/**
	 * 
	 * @param xpath
	 * @param att
	 * @return
	 */
	public String getAttributeFromJavaScript(String xpath, String att) {
		WebElement e = getElementPresent(xpath);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String value = (String) executor.executeScript(
				" return arguments[0].getAttribute('" + att + "')", e);
		info("value" + value);
		return value;
	}
	/**
	 * 
	 * @return
	 */
	public String getIpOfMachinhe() {
		String ip = "";
		try {
			ip = Inet4Address.getLocalHost().getHostAddress();
			info("IP of local machine: " + ip);
		} catch (Exception e) {
			info("Exeption: " + e);
		}
		return ip;
	}

	/**
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void scrollToElement(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		WebElement element = getElementPresent(locator, DEFAULT_TIMEOUT, 1,
				notDisplay);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * type cho cac input dang so
	 * 
	 * @param locator
	 * @param text
	 * @param validate
	 */
	public void typeHinput(Object locator, String text, boolean validate) {
		if (text != null) {
			if (getElementPresent(locator) != null) {
				try {
					WebElement e = getElementPresent(locator);
					e.click();
					e.sendKeys(Keys.CONTROL + "a");
					e.sendKeys(text);
					for (int i = 0; i < 5; i++) {
						String am = getValue(locator, 2);
						if (am != null) {
							if (am.equalsIgnoreCase(text)) {
								break;
							} else {
								e.sendKeys(Keys.CONTROL + "a");
								e.sendKeys(text);
							}
						}
					}
				} catch (StaleElementReferenceException ex) {
					typeHinput(locator, text, validate);
				}
			}
		}
	}

	/**
	 * compare 2 string
	 * 
	 * @param s1
	 * @param s2
	 */
	public void verifyCompare(String s1, String s2) {
		if (s1 != "" && s1 != null && s2 != null && s2 != "") {
			Assert.assertFalse(!s1.equalsIgnoreCase(s2),
					"So sanh khong bang nhau: " + s1 + " va " + s2);
		} else if ((s1 == "" || s1 == null) && (s2 == "" || s2 == null)) {
			info("2 truong du lieu can so sanh deu null");
		} else {
			Assert.fail("Du lieu so sanh co 1 truong bi null");
		}
	}
	public void verifyContains(String s1, String s2) {
		if (s1 != null && s2 != null) {
			Assert.assertFalse(!s2.contains(s1), "Chuỗi " + s1
					+ " không nằm trong chuỗi " + s2);
		}
	}

	/**
	 * 
	 * @param xpath
	 * @param option
	 */
	public void selectOptionFromCombobox(String xpath, String option) {
		if (option != null) {
			String locator = xpath.replaceAll("&option", option);
			click(locator);
			waitForElementNotPresent(locator, 10000, 0);
		}
	}
	public void captureScreen(WebDriver driver, String fileName) {
		try {
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			String dir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(dir + "\\capture_screen\\"
					+ fileName));
		} catch (Exception e) {
			info("Khong capture duoc man hinh");
		}
	}

	public String trimCharactor(String input, String trim) {
		info("Xau can xu ly trim: " + input);
		if (input != "" && input != null && trim != "") {
			if (trim == ".") {
				return input.replaceAll("\\.", "");
			} else {
				return input.replaceAll(trim, "");
			}
		} else
			return "";
	}
	/**
	 * 
	 * @param file
	 * @param filePath
	 */
	public void uploadFile(Object file, String filePath) {
		WebElement e = getElement(file);
		info("Upload file "
				+ getAbsoluteFilePath("\\file_to_upload\\" + filePath));
		e.sendKeys(getAbsoluteFilePath("\\file_to_upload\\" + filePath));
	}

	public void selectOptionByValue(Object cbb,String value)
	{
		WebElement e = getElementPresent(cbb);
		Select dropdown = new Select(e);
		dropdown.selectByValue(value);
	}

	public void switchNewTab(int... index) {
		int tab = index.length > 0 ? index[0] : 1;
		ArrayList<String> tabs2 = new ArrayList<String>(
				driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(tab));
	}

	public void verifyNotContains(String s1, String s2) {
		if (s1 != null && s2 != null && s2.contains(s1)) {
			info("Fail do chuoi " + s2 + "  van chua chuoi " + s1);
			Assert.assertFalse(s2.contains(s1));
		}
	}


	public void enter(Object locator) {
		if (locator != null) {
			WebElement e = getElementPresent(locator);
			e.sendKeys(Keys.ENTER);
		}
	}
}
