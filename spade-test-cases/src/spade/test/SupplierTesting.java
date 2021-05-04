package spade.test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.jupiter.api.Order;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SupplierTesting {
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
  public void testSupplierItemCreation() throws Exception {
    driver.get("http://csci4830-wheller.ddns.net:8080/spade/home.jsp");
    driver.findElement(By.linkText("Log In")).click();
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testSupplier");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.findElement(By.linkText("Your Listings")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("PS5");
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("500.99");
    driver.findElement(By.name("manufacturer")).clear();
    driver.findElement(By.name("manufacturer")).sendKeys("Sony");
    driver.findElement(By.name("quantity")).clear();
    driver.findElement(By.name("quantity")).sendKeys("600");
    driver.findElement(By.name("type")).click();
    new Select(driver.findElement(By.name("type"))).selectByVisibleText("Gaming Console");
    driver.findElement(By.name("type")).click();
    driver.findElement(By.xpath("//input[@value='Submit']")).click();
    
    String expected = "PS5 - $500.99 from Sony, 600 in stock\nDelete Listing\nView Listing";
    String result = driver.findElement(By.xpath("//*[@class='itemBox']")).getText();
    Assert.assertEquals(expected, result);
  }

  @Test
  public void testSupplierItemDeletion() throws Exception {
	  driver.get("http://csci4830-wheller.ddns.net:8080/spade/home.jsp");
	  driver.findElement(By.linkText("Log In")).click();
	  driver.findElement(By.name("username")).click();
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("testSupplier");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("test");
	  driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
	  driver.findElement(By.linkText("Your Listings")).click();
	  driver.findElement(By.name("id")).click();
    
    String expected = "You don't have any listings!\nYou can post an item listing below.";
    String result = driver.findElement(By.xpath("//h3[contains(text(), \"You don't have any listings\")]")).getText();
    //System.out.println("hello" + result);
    Assert.assertEquals(expected, result);
  }

  @Test
  public void testSuccessFullLogin() throws Exception {
    driver.get("http://csci4830-wheller.ddns.net:8080/spade/home.jsp");
    driver.findElement(By.linkText("Log In")).click();
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testSupplier");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
    
    String expected = "Welcome, testSupplier";
    String result = driver.findElement(By.xpath("//a[contains(text(), \"Welcome, testSupplier\")]")).getText();
    //System.out.println("hello" + result);
    Assert.assertEquals(expected, result);
  }
  
  @Test
  public void testFailiureLogin() throws Exception {
    driver.get("http://csci4830-wheller.ddns.net:8080/spade/home.jsp");
    driver.findElement(By.linkText("Log In")).click();
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testSupplier");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test1");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    
    String expected = "Create an Account";
    String result = driver.findElement(By.xpath("//a[contains(text(), \"Create an Account\")]")).getText();
    //System.out.println("hello" + result);
    Assert.assertEquals(expected, result);
  }
  
  /**
   * This test checks that deleted item listings are removed from user carts.
   * An item is posted, a user adds the item to their cart, and the the supplier deletes the listing.
   * If correct, the deleted item should be removed from the user's cart.
   * @throws Exception
   */
  @Test
  public void test5() throws Exception {
	  driver.get("http://csci4830-wheller.ddns.net:8080/spade/home.jsp");
	  driver.findElement(By.linkText("Log In")).click();
	  driver.findElement(By.name("username")).click();
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("testSupplier");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("test");
	  driver.findElement(By.xpath("//input[@value='Login']")).click();
	  driver.findElement(By.linkText("Your Listings")).click();
	  driver.findElement(By.name("name")).click();
	  driver.findElement(By.name("name")).clear();
	  driver.findElement(By.name("name")).sendKeys("random");
	  driver.findElement(By.name("price")).clear();
	  driver.findElement(By.name("price")).sendKeys("666");
	  driver.findElement(By.name("manufacturer")).clear();
	  driver.findElement(By.name("manufacturer")).sendKeys("joeth");
	  driver.findElement(By.name("quantity")).clear();
	  driver.findElement(By.name("quantity")).sendKeys("20");
	  driver.findElement(By.name("type")).click();
	  new Select(driver.findElement(By.name("type"))).selectByVisibleText("Laptop");
	  driver.findElement(By.name("type")).click();
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  driver.findElement(By.xpath("//button")).click();
	  driver.findElement(By.linkText("Log In")).click();
	  driver.findElement(By.name("username")).click();
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("testConsumer2");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("test");
	  driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
	  driver.findElement(By.linkText("Search")).click();
	  driver.findElement(By.xpath("//input[@value='Search']")).click();
	  driver.findElement(By.xpath("(//button[@name='id'])[14]")).click();
	  driver.findElement(By.xpath("//button")).click();
	  driver.findElement(By.linkText("Log In")).click();
	  driver.findElement(By.name("username")).click();
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("testSupplier");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("test");
	  driver.findElement(By.xpath("//input[@value='Login']")).click();
	  driver.findElement(By.linkText("Your Listings")).click();
	  driver.findElement(By.xpath("(//button[@name='id'])")).click();
	  driver.findElement(By.xpath("//button")).click();
	  driver.findElement(By.linkText("Log In")).click();
	  driver.findElement(By.name("username")).click();
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("testConsumer2");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("test");
	  driver.findElement(By.xpath("//input[@value='Login']")).click();
	  driver.findElement(By.linkText("Cart")).click();
	  driver.findElement(By.name("id")).click();
	  
    String expected = "Your cart appears to be empty!\nWhy don't you browse our item listings?";
    String result = driver.findElement(By.xpath("//h3[contains(text(), \"Your cart appears to be empty\")]")).getText();
    //System.out.println("hello" + result);
    Assert.assertEquals(expected, result);
  }
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    //System.out.println(verificationErrorString);
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
