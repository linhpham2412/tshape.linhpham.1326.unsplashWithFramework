package nt.tshape.automation.selenium.PageModal;

import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class UnplashAccountPage extends ActionManager {
    private WebDriver driver;
    public UnplashAccountPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver=driver;
    }

    //Locator
    private final String userNameTextField = "id=user_username";
    private final String profileButtonLocatorByName = "xpath=//input[contains(@class,'btn') and (@value='%s')]";

    //Function
    public UnplashAccountPage inputUserNameValue(String usernameValue){
        waitForElementVisible(userNameTextField);
        clearText(userNameTextField);
        sendKeys(userNameTextField,usernameValue);
        getTestContext().setAttribute("Username",usernameValue);
        return this;
    }

    public UnplashAccountPage scrollToButtonNameAndClick(String buttonName){
        mouseMoveToElementAndClick(profileButtonLocatorByName.formatted(buttonName));
        return this;
    }
}
