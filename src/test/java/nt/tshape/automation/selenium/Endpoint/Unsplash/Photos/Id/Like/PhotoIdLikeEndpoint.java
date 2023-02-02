package nt.tshape.automation.selenium.Endpoint.Unsplash.Photos.Id.Like;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.PhotoDataModel;
import nt.tshape.automation.selenium.TestContext;

import java.util.List;

public class PhotoIdLikeEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/photos/%id%/like";
    public PhotoIdLikeEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer "+ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public PhotoIdLikeEndpoint callDELETERequestsToPhotoIdLikeEndpointToUnLikeAll() {
        super.addHeader("Authorization", unsplashBearerToken, PhotoIdLikeEndpoint.class);
        super.addHeader("Accept-Version", "v1", PhotoIdLikeEndpoint.class);
        setBaseHost(unsplashHost);
        ObjectMapper mapper = new ObjectMapper();
        List<PhotoDataModel> likedPhotoList = mapper.convertValue(getTestContext().getContextObjectsWithName("LikedPhotoList"),
                new TypeReference<List<PhotoDataModel>>() {
                });
        for (int i = 0; i < likedPhotoList.size(); i++) {
            endpointPath = endpointPath.replaceAll("%id%", likedPhotoList.get(i).id);
            setEndpointPath(endpointPath);
            sendDeleteRequest(PhotoIdLikeEndpoint.class);
            endpointPath = endpointPath.replaceAll(likedPhotoList.get(i).id, "%id%");
        }
        return this;
    }
}
