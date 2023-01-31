package nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Likes;

import com.fasterxml.jackson.core.JsonProcessingException;
import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.FollowingDataModel;
import nt.tshape.automation.selenium.DataModel.PhotoDataModel;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Following.UsernameFollowingEndpoint;
import nt.tshape.automation.selenium.TestContext;

import java.util.ArrayList;

public class LikesEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/users/%username%/likes";
    public LikesEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer "+ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public LikesEndpoint callGETRequestToGetListOfLikedPhotos() throws JsonProcessingException {
        String usernameValue = "";
        if (getTestContext().getAttributeByName("Username") == null){
            usernameValue = ConfigLoader.getEnvironment("unsplashAccount");
        }else{
            usernameValue = getTestContext().getAttributeByName("Username");
        }
        super.addHeader("Authorization",unsplashBearerToken, LikesEndpoint.class);
        super.addHeader("Accept-Version","v1", LikesEndpoint.class);
        setBaseHost(unsplashHost);
        endpointPath = endpointPath.replaceAll("%username%",usernameValue);
        setEndpointPath(endpointPath);
        sendGETRequest(LikesEndpoint.class);
        ArrayList<PhotoDataModel> likedPhotoList = (ArrayList<PhotoDataModel>) convertResponseToListObjects();
        getTestContext().setContextObjectsWithName("LikedPhotoList",likedPhotoList);
        return this;
    }
}
