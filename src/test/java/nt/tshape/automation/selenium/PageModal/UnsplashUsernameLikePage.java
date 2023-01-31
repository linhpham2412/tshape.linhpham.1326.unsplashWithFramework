package nt.tshape.automation.selenium.PageModal;

import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UnsplashUsernameLikePage extends ActionManager {
    private WebDriver driver;
    public UnsplashUsernameLikePage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver=driver;
    }

    //Locator
    private String usernameMenuNumberByName = "xpath=//a[contains(@href,'/@%username%') and (text()='%menuname%')]//span//span";
    private final String imageBlock = "xpath=//figure[(@itemprop='image')]";

    //Function
    public UnsplashUsernameLikePage openUsernameLikePage(){
        String usernameValue = "";
        if (getTestContext().getAttributeByName("Username") == null){
            usernameValue = ConfigLoader.getEnvironment("unsplashAccount");
        }else{
            usernameValue = getTestContext().getAttributeByName("Username");
        }
        openUrl("https://unsplash.com/@"+usernameValue+"/likes");
        return this;
    }

    //Verify
    public UnsplashUsernameLikePage verifyUsernameMenuFieldNameWithValue(String menuName, String expectedNumber){
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

    public UnsplashUsernameLikePage verifyNumberOfImagesInPage(String expectedNumber){
        //Act
        List<WebElement> actualElement = findElements(imageBlock);
        String actualNumber = String.valueOf(actualElement.size());

        //Verify
        assertEqual("Number of Images in Page",expectedNumber,actualNumber);
        return this;
    }
}
