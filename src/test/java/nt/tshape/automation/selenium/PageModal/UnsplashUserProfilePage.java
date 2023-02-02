package nt.tshape.automation.selenium.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class UnsplashUserProfilePage extends ActionManager {
    private WebDriver driver;
    public UnsplashUserProfilePage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver=driver;
    }

    //Locator
    private final String buttonEditProfileByName = "xpath=//a[contains(@href,'https://unsplash.com/account') and (text()='%s')]";
    private final String userFullNameLabel = "xpath=//div[contains(text(),'%s')]";

    //Function
    public UnsplashUserProfilePage openProfilePageWithNewUserName(){
        String usernameValue = "";
        if (getTestContext().getAttributeByName("Username") == null){
            usernameValue = ConfigLoader.getEnvironment("unsplashAccount");
        }else{
            usernameValue = getTestContext().getAttributeByName("Username");
        }
        openUrl("https://unsplash.com/@"+usernameValue);
        return this;
    }
    @SneakyThrows
    public UnsplashUserProfilePage clickEditProfileButtonByName(String buttonName){
        waitForShortTime();
        waitForElementVisible(buttonEditProfileByName.formatted(buttonName));
        click(buttonEditProfileByName.formatted(buttonName));
        return this;
    }

    //Verify
    public UnsplashUserProfilePage verifyUserFullNameDisplay(String userFullName) throws IOException {
        //verify
        assertElementExist(userFullNameLabel.formatted(userFullName));
        return this;
    }
}
