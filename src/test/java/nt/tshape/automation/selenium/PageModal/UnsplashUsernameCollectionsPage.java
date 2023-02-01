package nt.tshape.automation.selenium.PageModal;

import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class UnsplashUsernameCollectionsPage extends ActionManager {
    private WebDriver driver;
    public UnsplashUsernameCollectionsPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver=driver;
    }

    //Locator
    private String usernameMenuNumberByName = "xpath=//a[contains(@href,'/@%username%') and (text()='%menuname%')]//span//span";
    private final String imageBlock = "xpath=//figure[(@itemprop='image')]";
    private final String usernameCollectionCardName = "xpath=//div[text()='%s']";

    //Function
    public UnsplashUsernameCollectionsPage openUsernameCollectionsPage(){
        String usernameValue = "";
        if (getTestContext().getAttributeByName("Username") == null){
            usernameValue = ConfigLoader.getEnvironment("unsplashAccount");
        }else{
            usernameValue = getTestContext().getAttributeByName("Username");
        }
        openUrl("https://unsplash.com/@"+usernameValue+"/collections");
        return this;
    }

    public UnsplashUsernameCollectionsPage clickOnCollectionWithName(String collectionName){
        click(usernameCollectionCardName.formatted(collectionName));
        return this;
    }

    //Verify
    public UnsplashUsernameCollectionsPage verifyUsernameCollectionMenuFieldNameWithValue(String menuName, String expectedNumber){
        //Act
        String usernameValue = "";
        if (getTestContext().getAttributeByName("Username") == null){
            usernameValue = ConfigLoader.getEnvironment("unsplashAccount");
        }else{
            usernameValue = getTestContext().getAttributeByName("Username");
        }
        usernameMenuNumberByName = usernameMenuNumberByName.replaceAll("%username%",usernameValue);
        usernameMenuNumberByName = usernameMenuNumberByName.replaceAll("%menuname%",menuName);
        String actualNumber = getText(usernameMenuNumberByName);

        //Verify
        assertEqual(menuName,expectedNumber,actualNumber);
        return this;
    }

    public UnsplashUsernameCollectionsPage verifyNumberOfImagesInPage(String expectedNumber){
        //Act
        List<WebElement> actualElement = findElements(imageBlock);
        String actualNumber = String.valueOf(actualElement.size());

        //Verify
        assertEqual("Number of Images in Page",expectedNumber,actualNumber);
        return this;
    }

    public UnsplashUsernameCollectionsPage verifyCollectionNameExist(String expectedCollectionName) throws IOException {
        //verify
        assertElementExist(usernameCollectionCardName.formatted(expectedCollectionName));
        return this;
    }
}
