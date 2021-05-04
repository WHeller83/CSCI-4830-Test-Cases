package edu.uno.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestUNOApp {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", //
    		  "lib\\win\\chromedriver.exe");
      driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUNOSearch() throws Exception {
    driver.get("https://www.unomaha.edu/registrar/students/before-you-enroll/class-search/index.php");
    driver.manage().window().maximize();
    driver.findElement(By.xpath("//button[@class='btn btn-large btn-cta']")).click();
    driver.findElement(By.id("term")).click();
    new Select(driver.findElement(By.id("term"))).selectByVisibleText("Spring 2021");
    driver.findElement(By.id("term")).click();
    driver.findElement(By.id("subject")).click();
    new Select(driver.findElement(By.id("subject"))).selectByVisibleText("Computer Science");
    driver.findElement(By.id("subject")).click();
    driver.findElement(By.name("weekdays[]")).click();
    driver.findElement(By.id("class-search-submit")).click();
    driver.findElement(By.id("copy_main")).click();
    driver.findElement(By.xpath("//div[@id='copy_main']/div[4]")).click();
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
