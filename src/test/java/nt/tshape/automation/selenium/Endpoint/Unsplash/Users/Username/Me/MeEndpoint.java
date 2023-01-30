package nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Me;

import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Following.UsernameFollowingEndpoint;
import nt.tshape.automation.selenium.TestContext;

public class MeEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/me";
    public MeEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer "+ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public MeEndpoint callPUTRequestToRestoreUserNameToDefaultValue(){
        String usernameValue = ConfigLoader.getEnvironment("unsplashAccount");
        super.addHeader("Authorization",unsplashBearerToken, MeEndpoint.class);
        super.addHeader("Accept-Version","v1", MeEndpoint.class);
        setBaseHost(unsplashHost);
        setEndpointPath(endpointPath);
        addQueryParametersNameWithValue("username", usernameValue, MeEndpoint.class);
        sendPutRequest(MeEndpoint.class);
        getTestContext().setAttribute("Username",usernameValue);
        return this;
    }
}
