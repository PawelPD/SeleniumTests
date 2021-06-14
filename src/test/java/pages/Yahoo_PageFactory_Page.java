package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Yahoo_PageFactory_Page {
    private WebDriver driver;
    private static int numberTest = 0;

    @FindBy(id = "login-username")
    WebElement usernameField;

    @FindBy(id = "login-passwd")
    WebElement passwordField;

    @FindBy(id = "login-signin")
    WebElement loginButton;

    @FindBy(name = "verifyPassword")
    WebElement passwordButton;

    @FindBy(id = "ybarAccountMenuOpener")
    WebElement profileMenuButton;

    @FindBy(id = "profile-signout-link")
    WebElement logoutButton;

    @FindBy(css = "#account-switcher > div.bottom-cta > a")
    WebElement anotherAccountButton;


    public Yahoo_PageFactory_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10),this);
    }

    public static Yahoo_PageFactory_Page init(WebDriver driver) {
        return new Yahoo_PageFactory_Page(driver);
    }


    public Yahoo_PageFactory_Page email(String username) {
        try {
            usernameField.clear();
            usernameField.sendKeys(username);
        }catch (Exception e){
            anotherAccountButton.click();
            usernameField.clear();
            usernameField.sendKeys(username);
        }
        loginButton.click();
        return new Yahoo_PageFactory_Page(driver);
    }


    public Yahoo_PageFactory_Page email_ver2(String username) {
        numberTest++;
        if(numberTest > 1){
            anotherAccountButton.click();
        }
        usernameField.clear();
        usernameField.sendKeys(username);
        loginButton.click();
        return new Yahoo_PageFactory_Page(driver);
    }


    public Yahoo_PageFactory_Page pswd(String psw) {
        passwordField.clear();
        passwordField.sendKeys(psw);
        passwordButton.click();
        return new Yahoo_PageFactory_Page(driver);
    }


    public Yahoo_PageFactory_Page logout(){
        profileMenuButton.click();
        logoutButton.click();
        return new Yahoo_PageFactory_Page(driver);
    }

    public String validateLoginPageTitle() {
        return driver.getTitle();
    }
}
