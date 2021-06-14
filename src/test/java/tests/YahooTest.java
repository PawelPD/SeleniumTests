package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.YahooPage;

public class YahooTest extends TestBase {
    //private WebDriver driver;
    private YahooPage yahooPage;

    @DataProvider
    public Object[][] testData(){
        return new Object[][]{
                new Object[] {"paweltestmail.com", "pawelSM1"},
                new Object[] {"paweltestmai2.com", "pawelSM2"},
                new Object[] {"paweltestmai3.com", "pawelSM3"},
                new Object[] {"paweltestmail.com", "pawelSM1"},};
    }

    /*
     * Funkcja wykonywana przed każdym testem
     * */
    @BeforeClass(description = "Uruchomienie przeglądarki, Przejście do strony logowania")
    public void setUp(){
        initialization();
        yahooPage = new YahooPage(driver);
    }
    /*public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }*/


    /*
     * Test polegający na sprawdzeniu tytułu wczytanej strony
     *
    @Test(priority = 0, description = "Strona logowania")
    public void homeTest() {
        String title = yahooPage.validateLoginPageTitle();
        Assert.assertEquals(title, "Yahoo");
    }*/


    @Test(priority = 0, description = "Test logowania oraz wylogowania", dataProvider = "testData")
    public void loginLogoutTest(String email, String password){
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
