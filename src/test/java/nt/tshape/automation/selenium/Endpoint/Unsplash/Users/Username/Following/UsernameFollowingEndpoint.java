package nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Following;

import com.fasterxml.jackson.core.JsonProcessingException;
import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.FollowingDataModel;
import nt.tshape.automation.selenium.TestContext;

import java.util.List;

public class UsernameFollowingEndpoint extends UniversalEndpoint {

    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/users/%username%/following";

    public UsernameFollowingEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer "+ConfigLoader.getEnvironment("unsplashBearerToken");
    }


    public void callGETRequestToFollowingEndpointToGetListOfFollowings() throws JsonProcessingException {
        super.addHeader("Authorization",unsplashBearerToken, UsernameFollowingEndpoint.class);
        setBaseHost(unsplashHost);
        endpointPath = endpointPath.replaceAll("%username%",ConfigLoader.getEnvironment("unsplashAccount"));
        setEndpointPath(endpointPath);
        sendGETRequest(UsernameFollowingEndpoint.class);
        List<FollowingDataModel> followingDataModelList = (List<FollowingDataModel>) convertResponseToListObjects();
    }
}
