package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YahooLoginPage {
    private WebDriver driver;

    private By usernameField = By.id("login-username");
    private By loginButton = By.id("login-signin");
    private By passwordField = By.id("login-passwd");
    private By passwordButton = By.name("verifyPassword");



    public YahooLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public static YahooLoginPage open(WebDriver driver) {
        driver.get("https://login.yahoo.com/");
        return new YahooLoginPage(driver);
    }

    public YahooLoginPage email(String username) {
        WebElement u = driver.findElement(usernameField);
        u.clear();
        u.sendKeys(username);
        driver.findElement(loginButton).click();
        return new YahooLoginPage(driver);
    }

    public YahooLoginPage pswd(String psw) {
        WebElement u = driver.findElement(passwordField);
        u.clear();
        u.sendKeys(psw);
        driver.findElement(passwordButton).click();
        return new YahooLoginPage(driver);
    }
}
