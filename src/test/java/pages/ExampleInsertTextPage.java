package pages;

import base.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ExampleInsertTextPage extends TestBase{

    public void insertTextToElement(){
        WebElement element = getElementByCssSelector("[title='Szukaj']");
        WebElement cookies = getElementByCssSelector("#L2AGLb");
        super.clickOnElement(cookies);

        boolean result = insertText(element,"string testowy", 15);
        Assert.assertTrue(result, "Nie udało sie wprowadzić tekstu");

    }
}
