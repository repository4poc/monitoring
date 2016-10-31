package src;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleDemo {

	private static WebDriver driver;
	private static String baseUrl;

	@BeforeTest
	public static void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		baseUrl = "https://www.google.co.in/";
	}

	@Test
	public void testHomePage() throws Exception {
		String verifyTxtMsg = "Google";
		// driver.get(baseUrl);
		driver.navigate().to(baseUrl);
		String result = driver.findElement(By.xpath("//*[@id='hplogo']")).getAttribute("title");
		Assert.assertEquals(result, verifyTxtMsg);
	}

	@Test(dependsOnMethods = "testHomePage")
	public void testSearch() throws Exception {
		String verifyTxtMsg = "Tata Consultancy Services";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("q")).sendKeys("tcs");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("btnG")).click();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean result = driver.getPageSource().contains(verifyTxtMsg);
		Assert.assertEquals(result, true);
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}

}
