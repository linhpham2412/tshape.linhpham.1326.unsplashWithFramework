package nt.tshape.automation.selenium.PageModal;

import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class UnsplashHomePage extends ActionManager {

    private WebDriver driver;
    public UnsplashHomePage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver=driver;
    }

    //Locator
    private final String headerMenuButtonByName = "xpath=//a[contains(text(),'%s')]";
    private final String imageBlock = "xpath=//figure[(@itemprop='image')]";
    private final String userProfileModal = "xpath=//div[contains(@class,'ReactModal__Content')]";
    private final String userProfilePicture = "xpath=//div[contains(@class,'ReactModal__Content')]//img";
    private final String userProfileFollowButton = "xpath=//button[contains(@title,'%s')]";
    private final String userProfileButton = "xpath=//button[contains(@title,'Your personal menu button')]";
    private final String userProfileSubMenuByName = "xpath=//div[contains(@role,'menu')]//a[contains(text(),'%s')]";

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

    public UnsplashHomePage clickOnTopLeftImageBlock(){
        waitForElementVisible(imageBlock);
        click(imageBlock);
        return this;
    }

    public UnsplashHomePage hoverToUserProfilePictureAndClickFollowButton() throws InterruptedException {
        waitForElementVisible(userProfileModal);
        mouseHoverToElement(userProfilePicture);
        waitForElementClickable(userProfileFollowButton.formatted("Follow"));
        click(userProfileFollowButton.formatted("Follow"));
        return this;
    }

    public UnsplashHomePage clickUserProfileButton(){
        waitForElementClickable(userProfileButton);
        click(userProfileButton);
        return this;
    }

    public UnsplashHomePage clickUserProfileSubMenuName(String subMenuName){
        waitForElementVisible(userProfileSubMenuByName.formatted(subMenuName));
        click(userProfileSubMenuByName.formatted(subMenuName));
        return this;
    }

    //Verify
    public UnsplashHomePage verifyLoginButtonNotExist(){
        //verify
        assertElementNotExist(headerMenuButtonByName.formatted("Log in"));
        return this;
    }

    public UnsplashHomePage verifyFollowButtonChangeToFollowing() throws IOException {
        //verify
        assertElementExist(userProfileFollowButton.formatted("Following"));
        return this;
    }
}
