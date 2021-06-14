package tests;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.YahooPage;

import java.util.concurrent.TimeUnit;

public class YahooTest extends TestBase {
    //private WebDriver driver;

    @DataProvider
    public Object[][] testData(){
        return new Object[][]{
                new Object[] {"paweltestmail.com", "pawelSM1"},
                new Object[] {"paweltestmai2.com", "pawelSM2"},
                new Object[] {"paweltestmai3.com", "pawelSM3"},
                new Object[] {"paweltestmail.com", "pawelSM1"},};
    }

    @BeforeClass
    public void setUp(){
        initialization();
    }
    /*public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }*/

    @Test(dataProvider = "testData")
    public void loginTest(String email, String password){
        driver.get("https://login.yahoo.com/");
        YahooPage loginPage = YahooPage.init(driver);
        loginPage.email_ver2(email);
        loginPage.pswd(password);
        Assert.assertEquals(driver.getTitle(),"Yahoo | Mail, Weather, Search, Politics, News, Finance, Sports & Videos");
        loginPage.logout();
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
