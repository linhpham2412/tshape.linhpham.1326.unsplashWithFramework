package nt.tshape.automation.selenium.PageModal;

import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UnsplashCollectionCollectionIdPage extends ActionManager {
    private WebDriver driver;

    public UnsplashCollectionCollectionIdPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver = driver;
    }

    //Locator
    private final String imageBlock = "xpath=//figure[(@itemprop='image')]";
    private final String imageBlockByAltValue = "xpath=//img[@alt='%s']";

    //Function
    public UnsplashCollectionCollectionIdPage openUsernameCollectionsWithSavedCollectionIdPage() {
        String collectionId = getTestContext().getAttributeByName("MyPrivateCollectionId");
        openUrl("https://unsplash.com/collections/" + collectionId);
        return this;
    }

    //Verify
    public UnsplashCollectionCollectionIdPage verifyNumberOfImagesInPage(String expectedNumber) {
        //Act
        List<WebElement> actualElement = findElements(imageBlock);
        String actualNumber = String.valueOf(actualElement.size());

        //Verify
        assertEqual("Number of Images in Page", expectedNumber, actualNumber);
        return this;
    }

    public UnsplashCollectionCollectionIdPage verifyRemovedPhotoNotExist(){
        //Verify
        assertElementNotExist(imageBlockByAltValue.formatted(getTestContext().getAttributeByName("AltValueOfRemovedPhoto")));
        return this;
    }
}
