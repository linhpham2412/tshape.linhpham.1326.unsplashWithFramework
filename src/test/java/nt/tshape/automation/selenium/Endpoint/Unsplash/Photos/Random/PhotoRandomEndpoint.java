package nt.tshape.automation.selenium.Endpoint.Unsplash.Photos.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.PhotoDataModel;
import nt.tshape.automation.selenium.TestContext;

import java.util.ArrayList;

public class PhotoRandomEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/photos/random";
    public PhotoRandomEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer "+ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public PhotoRandomEndpoint sendGETRequestToPhotoRandomEndpointToGetListOfPhotos(int numberOfPhotos) throws JsonProcessingException {
        super.addHeader("Authorization",unsplashBearerToken, PhotoRandomEndpoint.class);
        super.addHeader("Accept-Version","v1", PhotoRandomEndpoint.class);
        setBaseHost(unsplashHost);
        setEndpointPath(endpointPath);
        ArrayList<PhotoDataModel> randomPhotoList = new ArrayList<>();
        for (int i = 0; i<numberOfPhotos;i++){
            sendGETRequest(PhotoRandomEndpoint.class);
            randomPhotoList.add(convertResponseToObject(PhotoDataModel.class));
        }
        getTestContext().setContextObjectsWithName("RandomPhotoList",randomPhotoList);
        return this;
    }
}
