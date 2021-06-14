package pages;

import base.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ExampleSubElementPage extends TestBase {


    public void findSubElement() throws InterruptedException{
        WebElement cookiesAccpet =  getElementById("onetrust-accept-btn-handler");
        super.clickOnElement(cookiesAccpet);

        Thread.sleep(7000);

        driver.navigate().refresh();
        WebElement parentElement = getElementById("tagItemWrapper");
        List<WebElement> ravMenuElements = this.getSubElementsByCssSelector(parentElement, "li");
        boolean found = false;

            for(WebElement ravMenuElement : ravMenuElements){
            if(super.getElementText(ravMenuElement).equals("Sports")){
                System.out.println("Element znaleziony");
                found = super.clickOnElement(ravMenuElement);
                break;
            }
        }
            Assert.assertTrue(found, "Nie znaleziono elementu");
            Thread.sleep(2000);

        }
}
