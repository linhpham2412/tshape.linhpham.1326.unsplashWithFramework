package nt.tshape.automation.selenium.PageModal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.DataModel.PhotoDataModel;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Photos.Random.PhotoRandomEndpoint;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnsplashPhotosPage extends ActionManager {
    private WebDriver driver;
    public UnsplashPhotosPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver=driver;
    }

    //Locator
    private final String menuHeaderPhotoButton = "xpath=//button[contains(@title,'%s')]";
    private final String userProfilePhotoModal = "xpath=//div[contains(@class,'ReactModal__Content')]";
    private final String userProfilePhotoPicture = "xpath=//div[contains(@class,'ReactModal__Content')]//img";
    private final String userProfilePhotoButtonByText = "xpath=//button[(text()='%s')]";
    private final String userProfilePhotoInputByName = "xpath=//input[@name='%s']";
    private final String userProfilePhotoModalButtonByTitle= "xpath=//div[contains(@class,'ReactModal__Content')]//button[contains(@title,'%s')]";

    //Function
    public UnsplashPhotosPage openPhotoPageInRandomListAndLikeThePhoto(){
        ObjectMapper mapper = new ObjectMapper();
        List<PhotoDataModel> randomPhotoList = mapper.convertValue(getTestContext().getContextObjectsWithName("RandomPhotoList"),
                new TypeReference<List<PhotoDataModel>>() {
                });

        for (int i = 0;i< randomPhotoList.size();i++){
            openUrl(randomPhotoList.get(i).links.html);
            waitForElementClickable(menuHeaderPhotoButton.formatted("Like"));
            click(menuHeaderPhotoButton.formatted("Like"));
        }
        return this;
    }

    public UnsplashPhotosPage hoverToUserProfilePictureAndClickFollowButton() throws InterruptedException {
        waitForElementVisible(userProfilePhotoModal);
        mouseHoverToElement(userProfilePhotoPicture);
        waitForElementClickable(menuHeaderPhotoButton.formatted("Follow"));
        click(menuHeaderPhotoButton.formatted("Follow"));
        return this;
    }

    public UnsplashPhotosPage clickButtonInPageWithTitle(String titleButton){
        waitForElementClickable(menuHeaderPhotoButton.formatted(titleButton));
        click(menuHeaderPhotoButton.formatted(titleButton));
        return this;
    }

    public UnsplashPhotosPage clickButtonInModalPageWithTitle(String titleButton){
        waitForElementClickable(userProfilePhotoModalButtonByTitle.formatted(titleButton));
        click(userProfilePhotoModalButtonByTitle.formatted(titleButton));
        return this;
    }

    public UnsplashPhotosPage clickButtonInPageWithText(String textButton){
        waitForElementClickable(userProfilePhotoButtonByText.formatted(textButton));
        click(userProfilePhotoButtonByText.formatted(textButton));
        return this;
    }

    public UnsplashPhotosPage inputNewCollectionTitleAndCheckPrivateCheckbox(String titleValue){
        waitForElementVisible(userProfilePhotoInputByName.formatted("title"));
        sendKeys(userProfilePhotoInputByName.formatted("title"),titleValue);
        click(userProfilePhotoInputByName.formatted("privacy"));
        return this;
    }

    //Verify
    public UnsplashPhotosPage verifyFollowButtonChangeToFollowing() throws IOException {
        //verify
        assertElementExist(menuHeaderPhotoButton.formatted("Following"));
        return this;
    }
}
