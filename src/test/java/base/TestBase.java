package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static Properties prop;
    public static ChromeDriver driver;
    private static long PAGE_LOAD_TIMEOUT = 20;


    public static void initialization() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--window-size=1920,1080");
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /*
     * Wczytanie ustawień z pliku properties
     * */
    public TestBase(){
        try{
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src\\config\\autoTests.properties");
            prop.load(ip);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public WebElement getElementById(String id){
        return this.getElement(By.id(id));
    }


    public WebElement getElementByName(String name){
        return this.getElement(By.name(name));
    }


    public WebElement getElementByCssSelector(String css){
        return this.getElement(By.cssSelector(css));
    }


    public WebElement getElementByXpath(String xpath){
        return this.getElement(By.xpath(xpath));
    }


    public WebElement getElement(By by){
        WebElement element;
        try{
            element = driver.findElement(by);
        }catch (Exception e){
            element = null;
        }
        return element;
    }


    public List<WebElement> getSubElementsByCssSelector(WebElement parentElementm, String selector){
        return this.getSubElements(parentElementm, By.cssSelector(selector));
    }


    public List<WebElement> getSubElements(WebElement parentElement, By by){
        List<WebElement> elements;
        try{
            elements = parentElement.findElements(by);
        }catch (Exception e){
            elements = null;
        }
        return elements;
    }


    public boolean clickOnElement(WebElement element){
        try {
            element.click();
        }catch (Exception e){
            return false;
        }
        return true;
    }


    public String getElementText(WebElement element){
        try{
            return element.getText();
        }catch (Exception e){
            return "";
        }
    }


    public boolean insertText(WebElement element, String textToInsert, long delayInMillis){
        if(element != null){
            for(char c : textToInsert.toCharArray()){
                this.waitActionInMillis(delayInMillis);
                element.sendKeys(""+c);
            }
            String textAfterInsert = element.getAttribute("value");
            System.out.println("textToInsert: " + textToInsert + "\ntextAfterInsert: " + textAfterInsert);
            return textToInsert.equals(textAfterInsert);
        }
        return false;
    }


    public void waitActionInMillis(long delayInMillis){
        long startTime = System.currentTimeMillis();
        long endTime = startTime + delayInMillis;
        while(System.currentTimeMillis() < endTime){
            ;
        }
    }


    public boolean waitForElement(boolean checkIfDisplayed, By by, long timeOutInSeconds){
        long startTime = System.currentTimeMillis();

        boolean isFound = false;

        while(!isFound && !this.isTimeout(startTime, timeOutInSeconds)){
            WebElement element = this.getElement(by);
            if(checkIfDisplayed){
                isFound = this.isElementDisplayed(element);
            }
            else {
                isFound = element != null;
            }
        }
        return isFound;
    }


    public boolean isElementDisplayed(WebElement element){
        if(element == null)
            return false;

        return element.isDisplayed();
    }


    public boolean isTimeout(long oryginalTime, long timeOutInSeconds){
        long waitTime = timeOutInSeconds *1000;
        long endTime = oryginalTime + waitTime;

        return (System.currentTimeMillis() > endTime);
    }


    public boolean waitForElementtoVanish(WebElement elementm, long timeOut){
        long startTime = System.currentTimeMillis();
        boolean isFound = this.isElementEnabled(elementm);

        while (isFound && !this.isTimeout(startTime, timeOut)){
            isFound = this.isElementEnabled(elementm);
        }
        return !isFound;
    }


    public boolean isElementEnabled(WebElement element){
        if(element == null)
            return false;

        try{
            return element.isEnabled();
        }
        catch(StaleElementReferenceException e){
            return false;
        }
    }

    public boolean waitForPageToLoadedOrInteractive(){
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        String pageLoadStatus = "";
        boolean pageWasLoaded = false;
        long startTime = System.currentTimeMillis();

        do{
            try{
                /*
                 * zabezpiecznie jeśli wysyła za dużo requestów
                 * waitActionInMilliseconds(1000);
                 * */
                pageLoadStatus = this.returnDocumentStatus();
                } catch (Exception e){
                    e.printStackTrace();
                }
                if(pageLoadStatus.equals("complete") || pageLoadStatus.equals("interactive")){
                    pageWasLoaded = true;
                }
                System.out.println("pageLoadStatus: " + pageLoadStatus);

        }while (!pageWasLoaded && !this.isTimeout(startTime,PAGE_LOAD_TIMEOUT));
        return pageWasLoaded;
    }


    public String returnDocumentStatus(){
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        return (String) js.executeScript("return document.readyState");
    }


    public boolean scrollPageToElement(By endElement, long timeOutInSeconds){
        boolean endOfPage = false;

        long startTime = System.currentTimeMillis();
        JavascriptExecutor js = (JavascriptExecutor) this.driver;

        Actions actions = new Actions(driver);
        while(!endOfPage && !isTimeout(startTime, timeOutInSeconds)){
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();

            endOfPage = this.waitForElement(true, endElement, 1);
            waitActionInMillis(500);
        }
        return endOfPage;
    }

    public String getCurrentDate() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public void captureScreenshot() throws IOException {
        try{
            //((ChromeDriver) driver).executeScript("document.body.style.transform = 'scale(0.85)'");
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new  File("screenshots/"+getCurrentDate()+".png"));
            System.out.println("Wykonano zrzut erkanu");
        }catch (Exception e){
            System.out.println("Wystapił wyjątek: " + e.getMessage());
        }
    }


    public void openNewTab(WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("window.open('about:blank','_blank');");
    }


    public ArrayList<String> getWindowHandlesAsArrayList(WebDriver driver){
        Set windowHandles =  driver.getWindowHandles();
        return new ArrayList<String>(windowHandles);
    }


    public String getNewWindowHandle(WebDriver driver){
        ArrayList<String> currentWindowHandles = getWindowHandlesAsArrayList(driver);
        int lastPosition = currentWindowHandles.size()-1;
        return currentWindowHandles.get(lastPosition);
    }

    public boolean clickUntilInteractable(WebElement element, long timeOutInSeconds){
        long startTime = System.currentTimeMillis();

        boolean success = false;
        while(!success && !this.isTimeout(startTime, timeOutInSeconds))
        try{
            element.click();
            success = true;
        }catch (ElementNotInteractableException e){
            try {
                System.out.println("w pętli");
                Thread.sleep(50);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return success;
    }

}
