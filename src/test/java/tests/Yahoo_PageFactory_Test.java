package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Yahoo_PageFactory_Page;

public class Yahoo_PageFactory_Test extends TestBase {
    //private WebDriver driver;
    private Yahoo_PageFactory_Page yahoo_pageFactory_page;


    /*
     * Funkcja wykonywana przed każdym testem
     * */
    @BeforeClass(description = "Uruchomienie przeglądarki, Przejście do strony logowania")
    public void setUp(){
        initialization();
        driver.get("https://login.yahoo.com/");
        yahoo_pageFactory_page = new Yahoo_PageFactory_Page(driver);
    }
    /*public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }*/


    /*
     * Test polegający na sprawdzeniu tytułu wczytanej strony
     * */
    @Test(priority = 0, description = "Strona logowania")
    public void homeTest() {
        String title = yahoo_pageFactory_page.validateLoginPageTitle();
        Assert.assertEquals(title, "Yahoo");
    }


    @Test(priority = 0, description = "Test logowania oraz wylogowania")
    public void loginLogoutTest(){
        Yahoo_PageFactory_Page loginPage = Yahoo_PageFactory_Page.init(driver);
        loginPage.email_ver2("paweltestmail1");
        loginPage.pswd("pawelSM1");
        Assert.assertEquals(driver.getTitle(),"Yahoo | Mail, Weather, Search, Politics, News, Finance, Sports & Videos");
        loginPage.logout();
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
