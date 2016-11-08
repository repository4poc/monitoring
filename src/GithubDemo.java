
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GithubDemo {

	private static WebDriver driver;
	private static String baseUrl;
	private final String username = "varinder_gupta82@yahoo.com";
	private final String password = "Valentine2284";

	@BeforeTest
	public static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "util/chromedriver");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		driver = new ChromeDriver(option);

		baseUrl = "https://github.com/";
	}

	@Test
	public void testHomePage() throws Exception {
		String verifyTxtMsg = "How people build software";
		// driver.get(baseUrl);
		driver.navigate().to(baseUrl);
		String result = driver.findElement(By.xpath("html/body/div[4]/div[1]/div/div/div[1]/h1")).getText();
		Assert.assertEquals(result, verifyTxtMsg);
	}

	@Test(dependsOnMethods="testHomePage")
	public void testLogin() throws Exception {
		String verifyTxtMsg = "Learn Git and GitHub without any code!";
		driver.findElement(By.xpath("html/body/header/div/div/div/a[2]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//*[@id='login_field']")).sendKeys(username);
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='login']/form/div[4]/input[3]")).submit();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String result = driver.findElement(By.xpath(".//*[@id='js-pjax-container']/div[1]/div/div/h2")).getText();
		Assert.assertEquals(result, verifyTxtMsg);
	}

	private void extracted(String verifyTxtMsg, String result) {
		Assert.assertEquals(result, verifyTxtMsg);
	}
	
	@Test(dependsOnMethods="testLogin")
	public void testLogOut() throws Exception {
		String verifyTxtMsg = "How people build software";
		driver.findElement(By.xpath(".//*[@id='user-links']/li[3]/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//*[@id='user-links']/li[3]/div/div/form/button")).click();

		System.out.println("Logout working...");
		String result = driver.findElement(By.xpath("html/body/div[4]/div[1]/div/div/div[1]/h1")).getText();
		Assert.assertEquals(result, verifyTxtMsg);
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}

}
