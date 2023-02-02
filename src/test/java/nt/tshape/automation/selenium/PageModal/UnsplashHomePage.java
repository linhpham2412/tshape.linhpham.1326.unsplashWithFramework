package nt.tshape.automation.selenium.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class UnsplashHomePage extends ActionManager {

    private WebDriver driver;
    public UnsplashHomePage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver=driver;
    }

    //Locator
    private final String headerMenuButtonByName = "xpath=//a[contains(text(),'%s')]";
    private final String imageBlock = "xpath=//figure[(@itemprop='image')]";
    private final String userProfileButtonByTitle = "xpath=//button[contains(@title,'%s')]";
    private final String userProfileSubMenuByName = "xpath=//div[contains(@role,'menu')]//a[contains(text(),'%s')]";
    private final String userProfileViewMoreLinkMenuItemByName = "xpath=//details//*[text()='%s']";

    //Function
    public UnsplashHomePage openUnsplashHomePage(){
        openUrl(ConfigLoader.getEnvironment("unsplashURL"));
        return this;
    }

    public UnsplashLoginPage clickMenuButtonByName(String menuName){
        waitForElementClickable(headerMenuButtonByName.formatted("Log in"));
        click(headerMenuButtonByName.formatted("Log in"));
        return new UnsplashLoginPage(driver,getTestContext());
    }

    public UnsplashHomePage clickOnTopLeftImageBlock(){
        waitForElementVisible(imageBlock);
        click(imageBlock);
        return this;
    }

    public UnsplashHomePage clickUserProfileButtonByTitle(String buttonTitle){
        waitForElementClickable(userProfileButtonByTitle.formatted(buttonTitle));
        click(userProfileButtonByTitle.formatted(buttonTitle));
        return this;
    }

    public UnsplashHomePage clickUserProfileSubMenuName(String subMenuName){
        waitForElementVisible(userProfileSubMenuByName.formatted(subMenuName));
        click(userProfileSubMenuByName.formatted(subMenuName));
        return this;
    }

    @SneakyThrows
    public UnsplashHomePage clickUserProfileViewMoreLinkSubMenuItem(String itemName){
//        waitForElementVisible(userProfileViewMoreLinkMenuItemByName.formatted(itemName));
        click(userProfileViewMoreLinkMenuItemByName.formatted(itemName));
        return this;
    }


    //Verify
    public UnsplashHomePage verifyLoginButtonNotExist(){
        //verify
        assertElementNotExist(headerMenuButtonByName.formatted("Log in"));
        return this;
    }
}
