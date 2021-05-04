package spade.test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ConsumerTesting {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "lib\\win\\chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPurchase() throws Exception {
    driver.get("http://csci4830-wheller.ddns.net:8080/spade/login.jsp");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testConsumer");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.findElement(By.xpath("/html/body/div[1]/nav[5]/a")).click();
    driver.findElement(By.name("balance")).click();
    driver.findElement(By.name("balance")).clear();
    driver.findElement(By.name("balance")).sendKeys("230");
    driver.findElement(By.xpath("//input[@value='Add to Balance']")).click();
    driver.findElement(By.linkText("Search")).click();
    driver.findElement(By.xpath("//input[@value='Search']")).click();
    driver.findElement(By.xpath("(//button[@name='id'])[6]")).click();
    driver.findElement(By.xpath("//input[@value='Proceed to Checkout']")).click();
    //retrieve balance
    double temp = Double.parseDouble(driver.findElement(By.xpath("/html/body/div[1]/nav[5]/a")).getAttribute("innerHTML").substring(10));
    //retrieves order total
    String costString = driver.findElement(By.xpath("/html/body/div[2]")).getAttribute("innerHTML");
    double cost = Double.parseDouble(costString.substring(costString.indexOf("Order Total:") + 14, costString.indexOf("<form accept")));
    driver.findElement(By.xpath("//input[@value='Purchase']")).click();
    

    
    String expected = String.format("Balance after purchase: $%.2f", (temp - cost));
    String result = driver.findElement(By.xpath("/html/body/div[2]")).getAttribute("innerHTML");
    

    
    Assert.assertEquals(expected, result);
  }
  
  @Test
  public void testAddToBalance() throws Exception {
    driver.get("http://csci4830-wheller.ddns.net:8080/spade/login.jsp");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testConsumer");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.findElement(By.xpath("/html/body/div[1]/nav[5]/a")).click();
    double temp = Double.parseDouble(driver.findElement(By.xpath("/html/body/div[1]/nav[5]/a")).getAttribute("innerHTML").substring(10));
    driver.findElement(By.name("balance")).click();
    driver.findElement(By.name("balance")).clear();
    driver.findElement(By.name("balance")).sendKeys("200");
    driver.findElement(By.xpath("//input[@value='Add to Balance']")).click();
    
    String expected = String.format("Current Balance: $%.2f", (temp + 200));
    String result = driver.findElement(By.xpath("/html/body/div[2]")).getAttribute("innerHTML");
    
    result = result.substring(0, result.indexOf("<div class"));
    Assert.assertEquals(expected, result);
  }
  
  @Test
  public void testCheckout() throws Exception {
    driver.get("http://csci4830-wheller.ddns.net:8080/spade/login.jsp");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testConsumer");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.findElement(By.linkText("Search")).click();
    driver.findElement(By.xpath("//input[@value='Search']")).click();
    driver.findElement(By.xpath("(//button[@name='id'])[6]")).click();
    driver.findElement(By.xpath("//input[@value='Proceed to Checkout']")).click();
    
    String expected = "Order Total: $212.93";
    String result = driver.findElement(By.xpath("/html/body/div[2]")).getAttribute("innerHTML");
    result = result.substring(result.indexOf("Order Total: "), result.indexOf("<form accept"));
    Assert.assertEquals(expected, result);
  }
  
  @Test
  public void testAddToCart() throws Exception {
    driver.get("http://csci4830-wheller.ddns.net:8080/spade/login.jsp");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testConsumer");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.findElement(By.linkText("Search")).click();
    driver.findElement(By.xpath("//input[@value='Search']")).click();
    //gamekid info
    String temp = driver.findElement(By.xpath("/html/body/div[2]/div[3]")).getAttribute("innerHTML");
    driver.findElement(By.xpath("(//button[@name='id'])[6]")).click();
    
    String expected = temp.substring(0, temp.indexOf("<br>"));
    String result = driver.findElement(By.xpath("/html/body/div[2]/div[1]")).getAttribute("innerHTML");
    result = result.substring(0, result.indexOf("<"));
    Assert.assertEquals(expected, result);
  }
  
  @Test
  public void testLogin() throws Exception {
    driver.get("http://csci4830-wheller.ddns.net:8080/spade/login.jsp");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testConsumer");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    
    String expected = "Welcome, testConsumer";
    String result = driver.findElement(By.xpath("/html/body/div[1]/nav[6]/a")).getAttribute("innerHTML");
    Assert.assertEquals(expected, result);
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
