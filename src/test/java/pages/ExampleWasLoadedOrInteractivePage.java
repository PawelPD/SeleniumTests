package pages;

import base.TestBase;
import org.testng.Assert;

public class ExampleWasLoadedOrInteractivePage extends TestBase {

    public void infiniteLoadingPageTest(){

        boolean result = super.waitForPageToLoadedOrInteractive();
        Assert.assertTrue(result, "Strona jest w trakcie ładowania");
    }
}
