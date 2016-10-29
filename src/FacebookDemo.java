
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FacebookDemo {

	private static WebDriver driver;
	private static String baseUrl;
	private final String username = "varinder_gupta25@yahoo.co.in";
	private final String password = "valentine@0000";

	@BeforeTest
	public static void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		baseUrl = "https://www.facebook.com/";
	}

	@Test
	public void testHomePage() throws Exception {
		String verifyTxtMsg = "Facebook helps you connect and share with the people in your life.";
		// driver.get(baseUrl);
		driver.navigate().to(baseUrl);
		String result = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div/div/div/div[1]/div/div")).getText();
		System.out.println(result);
		Assert.assertEquals(result, verifyTxtMsg);
	}

	@Test(dependsOnMethods="testHomePage")
	public void testLogin() throws Exception {
		String verifyTxtMsg = "Varinder";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div[2]/form/table/tbody/tr[2]/td[3]/label/input")).submit();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String result = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div/a/span")).getText();
		Assert.assertEquals(result, verifyTxtMsg);
	}

	private void extracted(String verifyTxtMsg, String result) {
		Assert.assertEquals(result, verifyTxtMsg);
	}
	
	@Test(dependsOnMethods="testLogin")
	public void testLogOut() throws Exception {
		String verifyTxtMsg = "How people build software";
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div[3]/div/div/div[3]/div")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[23]/div/div/div/div/div[1]/div/div/ul/li[17]/a/span/span")).click();

		System.out.println("Logout working...");
		String result = driver.findElement(By.xpath("html/body/div[4]/div[1]/div/div/div[1]/h1")).getText();
		Assert.assertEquals(result, verifyTxtMsg);
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}

}
