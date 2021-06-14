package tests;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.ExampleInsertTextPage;
import pages.ExampleSubElementPage;
import pages.ExampleWasLoadedOrInteractivePage;

import java.util.ArrayList;
import java.util.Set;

public class ExampleTests extends TestBase{


    @BeforeClass
    public void setUp(){
        initialization();
    }

    @Test(priority = 1, enabled = false, description = "")
    public void subElementTest() throws InterruptedException{

        driver.get("https://eu.heraldtribune.com/");
        ExampleSubElementPage exampleSubElementPage = new ExampleSubElementPage();
        exampleSubElementPage.findSubElement();
    }

    @Test(priority = 1, enabled = false, description = "")
    public void insertTextTest(){

        driver.get("https://www.google.pl/");
        ExampleInsertTextPage exampleInsertTextPage = new ExampleInsertTextPage();
        exampleInsertTextPage.insertTextToElement();
    }

    @Test(priority = 1, enabled = false, description = "")
    public void wasLoadedOrInteractive(){

        driver.get("https://www.qq.com/");
        ExampleWasLoadedOrInteractivePage exampleWasLoadedOrInteractivePage = new ExampleWasLoadedOrInteractivePage();
        exampleWasLoadedOrInteractivePage.infiniteLoadingPageTest();
    }

    @Test(priority = 1, enabled = false, description = "")
    public void scrollPageTest(){

        By endOfPageElement  = By.xpath("//*[text()='Pokaż więcej odpowiedzi']");
        long rimeoutInSeconds = 30;

        driver.get("https://twitter.com/EpochTimes/status/1209285822937288704");
        boolean result = super.scrollPageToElement(endOfPageElement,rimeoutInSeconds);
        Assert.assertTrue(result,"Błąd przewijania strony");
    }

    @Test(priority = 1, enabled = true, description = "")
    public void windowHandleTest(){

        driver.get("https://www.google.pl/");
        String googleWindowHandle = driver.getWindowHandle();

        openNewTab(driver);

        String otherWindowHandle = "";
        otherWindowHandle = getNewWindowHandle(driver);
        driver.switchTo().window(otherWindowHandle);
        String onetUrl = "https://www.onet.pl/";
        driver.get(onetUrl);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, onetUrl);

        openNewTab(driver);

        String onetHandle = otherWindowHandle;
        //ArrayList<String> currnetWindowHandles = getWindowHandlesAsArrayList(driver);
        otherWindowHandle = getNewWindowHandle(driver);
        driver.switchTo().window(otherWindowHandle);
        driver.get("https://www.benchmark.pl/");
        String currentTitle = driver.getTitle();
        Assert.assertEquals(currentTitle,"benchmark.pl | recenzje, testy, newsy, rankingi");

        driver.switchTo().window(googleWindowHandle);
        currentTitle = driver.getTitle();
        Assert.assertEquals(currentTitle,"Google");

        driver.switchTo().window(onetHandle);
        currentTitle = driver.getTitle();
        Assert.assertEquals(currentTitle,"Onet – Jesteś na bieżąco");
    }



    @AfterMethod(description = "Screenshot")
    public void getScreen(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                captureScreenshot();
                driver.quit();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}

