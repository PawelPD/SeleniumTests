package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YahooPage {
    private WebDriver driver;

    private By usernameField = By.id("login-username");
    private By loginButton = By.id("login-signin");
    private By passwordField = By.id("login-passwd");
    private By passwordButton = By.name("verifyPassword");
    private By profileMenuButton = By.id("ybarAccountMenuOpener");
    private By logoutButton = By.id("profile-signout-link");
    private By anotherAccount = By.cssSelector("#account-switcher > div.bottom-cta > a");
    private static int numberTest = 0;



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
            driver.findElement(anotherAccount).click();
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
            driver.findElement(anotherAccount).click();
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
        driver.findElement(profileMenuButton).click();
        driver.findElement(logoutButton).click();
        return new YahooPage(driver);
    }
}
