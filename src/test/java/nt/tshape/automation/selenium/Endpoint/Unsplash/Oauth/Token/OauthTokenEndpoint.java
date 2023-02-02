package nt.tshape.automation.selenium.Endpoint.Unsplash.Oauth.Token;

import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.AccessTokenModel;
import nt.tshape.automation.selenium.TestContext;

public class OauthTokenEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/oauth/token";

    public OauthTokenEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer " + ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public OauthTokenEndpoint callPOSTRequestToCreateNewAccessToken() {
        super.addHeader("Accept-Version", "v1", OauthTokenEndpoint.class);
        setBaseHost(unsplashHost);
        setEndpointPath(endpointPath);
        super.addQueryParametersNameWithValue("client_id",
                getTestContext().getAttributeByName("access_key"), OauthTokenEndpoint.class);
        super.addQueryParametersNameWithValue("client_secret",
                getTestContext().getAttributeByName("secret_key"), OauthTokenEndpoint.class);
        super.addQueryParametersNameWithValue("code",
                getTestContext().getAttributeByName("code"), OauthTokenEndpoint.class);
        super.addQueryParametersNameWithValue("grant_type",
                "authorization_code", OauthTokenEndpoint.class);
        super.addQueryParametersNameWithValue("redirect_uri",
                ConfigLoader.getEnvironment("unsplashURI"), OauthTokenEndpoint.class);
        sendPostRequest(OauthTokenEndpoint.class);
        AccessTokenModel accessTokenModel = convertResponseToObject(AccessTokenModel.class);
        getTestContext().setAttribute("BearerToken", accessTokenModel.token_type + " " + accessTokenModel.access_token);
        return this;
    }
}
