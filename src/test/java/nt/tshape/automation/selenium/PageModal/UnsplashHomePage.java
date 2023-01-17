package nt.tshape.automation.selenium.PageModal;

import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UnsplashHomePage extends ActionManager {

    private WebDriver driver;
    public UnsplashHomePage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver=driver;
    }

    //Locator
    private final String headerMenuButtonByName = "xpath=//a[contains(text(),'%s')]";

    //Function
    public UnsplashHomePage openUnsplashHomePage(){
        openUrl(ConfigLoader.getEnvironment("unsplashURL"));
        return this;
    }

    public UnsplashLoginPage clickLoginButton(){
        waitForElementClickable(headerMenuButtonByName.formatted("Log in"));
        click(headerMenuButtonByName.formatted("Log in"));
        return new UnsplashLoginPage(driver,getTestContext());
    }

    //Verify
    public UnsplashHomePage verifyLoginButtonNotExist(){
        //veriry
        assertElementNotExist(headerMenuButtonByName.formatted("Log in"));
        return this;
    }
}
