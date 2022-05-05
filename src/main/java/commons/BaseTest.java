package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    private WebDriver driver;

    protected WebDriver getBrowserDriver(String browserName, String url) {
	if (browserName.equals("chrome")) {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();

	} else if (browserName.equals("firefox")) {
	    WebDriverManager.firefoxdriver().setup();
	    driver = new FirefoxDriver();
	}

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get(url);

	return driver;
    }
}
