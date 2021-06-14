package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.YahooLoginPage;

import java.util.concurrent.TimeUnit;

public class YahooTest {
    private WebDriver driver;



    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Test
    public void testLogin(){
        YahooLoginPage loginPage = YahooLoginPage.open(driver);
        loginPage.email("paweltestmail");
        loginPage.pswd("pawelSM1");


    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
