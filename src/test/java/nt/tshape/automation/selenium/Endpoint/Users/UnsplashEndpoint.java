package nt.tshape.automation.selenium.Endpoint.Users;

import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.selenium.TestContext;

public class UnsplashEndpoint extends UniversalEndpoint {

    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "";

    public UnsplashEndpoint(TestContext testContext) {
        super(testContext);
    }


}
