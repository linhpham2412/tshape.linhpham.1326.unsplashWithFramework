package nt.tshape.automation.selenium.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import nt.tshape.automation.selenium.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UnsplashAccountHistoryPage extends ActionManager {
    private WebDriver driver;

    public UnsplashAccountHistoryPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver = driver;
    }

    //Locator
    private final String downloadedFileName = "xpath=//div[@class='download-history__file ']";

    //Function
    public UnsplashAccountHistoryPage getDownloadedItemNameByIndexInPage(int index) {
        List<WebElement> downloadedItemList = findElements(downloadedFileName);
        getTestContext().setAttribute("CheckingDownloadedItem", getTextFromElement(downloadedItemList.get(index)));
        return this;
    }

    //Verify
    @SneakyThrows
    public UnsplashAccountHistoryPage verifyDownloadedFileExistInDownloadFolder() {
        //Act
        String downloadFolderLocation = "";
        if (ConfigLoader.getConfiguration("OS").equalsIgnoreCase("Windows")) {
            String home = System.getProperty("user.home");
            downloadFolderLocation = home + "\\Download";
        }

        //Verify
        assertConditionTrue("Download Image Exist", Utils.checkIfFileExistInLocation(downloadFolderLocation, getTestContext().getAttributeByName("CheckingDownloadedItem")));
        return this;
    }
}
