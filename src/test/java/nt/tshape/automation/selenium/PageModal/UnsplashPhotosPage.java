package nt.tshape.automation.selenium.PageModal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.DataModel.PhotoDataModel;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Photos.Random.PhotoRandomEndpoint;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

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
}
