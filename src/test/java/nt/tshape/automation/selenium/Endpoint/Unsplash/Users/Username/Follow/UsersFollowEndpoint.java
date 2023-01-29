package nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Follow;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.FollowingDataModel;
import nt.tshape.automation.selenium.TestContext;

import java.util.List;

public class UsersFollowEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/users/%username%/follow";

    public UsersFollowEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer " + ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public UsersFollowEndpoint callDELETERequestsToUserFollowEndpointToUnfollowAll() {
        super.addHeader("Authorization", unsplashBearerToken, UsersFollowEndpoint.class);
        super.addHeader("Accept-Version", "v1", UsersFollowEndpoint.class);
        setBaseHost(unsplashHost);
        ObjectMapper mapper = new ObjectMapper();
        List<FollowingDataModel> followingDataModels = mapper.convertValue(getTestContext().getContextObjectsWithName("FollowingList"),
                new TypeReference<List<FollowingDataModel>>() {
                });
        for (int i = 0; i < followingDataModels.size(); i++) {
            endpointPath = endpointPath.replaceAll("%username%", followingDataModels.get(i).username);
            setEndpointPath(endpointPath);
            sendDeleteRequest(UsersFollowEndpoint.class);
        }
        return this;
    }

    public UsersFollowEndpoint verifyUsersFollowEndpointResponseCodeShouldBe(int expectedCode) {
        super.verifyEndpointResponseCodeEqual(expectedCode, UsersFollowEndpoint.class);
        return this;
    }
}
