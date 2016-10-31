
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


	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}

}
