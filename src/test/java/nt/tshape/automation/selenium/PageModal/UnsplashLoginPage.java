package nt.tshape.automation.selenium.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class UnsplashLoginPage extends ActionManager {

    WebDriver driver;

    public UnsplashLoginPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver = driver;
    }

    //Locator
    private final String loginPage_UserNameTextField = "id=user_email";
    private final String loginPage_UserPasswordTextField = "id=user_password";
    private final String loginPage_LoginButton = "xpath=//input[contains(@type,'submit')]";

    //Function
    public UnsplashLoginPage openUnsplashLoginPage(){
        openUrl(ConfigLoader.getEnvironment("unsplashURL")+"login");
        return this;
    }

    public UnsplashHomePage loginWithUsernameAndPasswordAndReturnToHomePage(String username, String password, UnsplashHomePage unsplashHomePage){
        waitForElementVisible(loginPage_UserNameTextField);
        sendKeys(loginPage_UserNameTextField,username);
        sendKeys(loginPage_UserPasswordTextField,password);
        click(loginPage_LoginButton);
        return unsplashHomePage;
    }

    //Verify
    @SneakyThrows
    public UnsplashLoginPage verifyLoginPageTitleDisplayCorrect(String expectedPageTitle) {
        //Act
        String actualPageTitle = driver.getTitle();

        //Verify
        assertEqual("Page Title",expectedPageTitle,actualPageTitle);
        return this;
    }
}
