package nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Following;

import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.FollowingDataModel;
import nt.tshape.automation.selenium.TestContext;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UsernameFollowingEndpoint extends UniversalEndpoint {

    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/users/%username%/following";

    public UsernameFollowingEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer "+ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public UsernameFollowingEndpoint callGETRequestToFollowingEndpointToGetListOfFollowings() throws IOException {
        String usernameValue = "";
        if (getTestContext().getAttributeByName("Username") == null){
            usernameValue = ConfigLoader.getEnvironment("unsplashAccount");
        }else{
            usernameValue = getTestContext().getAttributeByName("Username");
        }
        super.addHeader("Authorization",unsplashBearerToken, UsernameFollowingEndpoint.class);
        super.addHeader("Accept-Version","v1", UsernameFollowingEndpoint.class);
        setBaseHost(unsplashHost);
        endpointPath = endpointPath.replaceAll("%username%",usernameValue);
        setEndpointPath(endpointPath);
        sendGETRequest(UsernameFollowingEndpoint.class);
        ArrayList<FollowingDataModel> followingDataModelList = (ArrayList<FollowingDataModel>) convertResponseToListObjects();
        getTestContext().setContextObjectsWithName("FollowingList",followingDataModelList);
        return this;
    }
    //Verify
    public UsernameFollowingEndpoint verifyUsernameFollowingResponseCodeShouldBe(int expectedCode){
        super.verifyEndpointResponseCodeEqual(expectedCode, UsernameFollowingEndpoint.class);
        return this;
    }
}
