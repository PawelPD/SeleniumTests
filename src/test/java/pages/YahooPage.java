package pages;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YahooPage extends TestBase{
    private WebDriver driver;
    private static int numberTest = 0;

    private By usernameField = By.id("login-username");
    private By passwordField = By.id("login-passwd");
    private By loginButton = By.id("login-signin");
    private By passwordButton = By.name("verifyPassword");
    private By profileMenuButton = By.id("ybarAccountMenuOpener");
    private By logoutButton = By.id("profile-signout-link");
    private By anotherAccountButton = By.cssSelector("#account-switcher > div.bottom-cta > a");


    public YahooPage(WebDriver driver) {
        this.driver = driver;
    }

    public static YahooPage init(WebDriver driver) {
        return new YahooPage(driver);
    }

    public YahooPage email(String username) {
        try {
            WebElement element = driver.findElement(usernameField);
            element.clear();
            element.sendKeys(username);
        }catch (Exception e){
            driver.findElement(anotherAccountButton).click();
            WebElement element = driver.findElement(usernameField);
            element.clear();
            element.sendKeys(username);
        }
        driver.findElement(loginButton).click();
        return new YahooPage(driver);
    }

    public YahooPage email_ver2(String username) {
        numberTest++;
        if(numberTest > 1){
            driver.findElement(anotherAccountButton).click();
        }
        WebElement element = driver.findElement(usernameField);
        element.clear();
        element.sendKeys(username);
        driver.findElement(loginButton).click();
        return new YahooPage(driver);
    }

    public YahooPage pswd(String psw) {
        WebElement element = driver.findElement(passwordField);
        element.clear();
        element.sendKeys(psw);
        driver.findElement(passwordButton).click();
        return new YahooPage(driver);
    }

    public YahooPage logout(){
        super.clickUntilInteractable(driver.findElement(profileMenuButton),10);
        super.clickUntilInteractable(driver.findElement(logoutButton),10);
        return new YahooPage(driver);
    }

    public String validateLoginPageTitle() {
        return driver.getTitle();
    }
}
