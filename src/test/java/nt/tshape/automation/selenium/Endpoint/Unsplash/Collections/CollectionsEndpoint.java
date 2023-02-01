package nt.tshape.automation.selenium.Endpoint.Unsplash.Collections;

import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.CollectionDataModel;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Me.MeEndpoint;
import nt.tshape.automation.selenium.TestContext;

public class CollectionsEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/collections";
    public CollectionsEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer "+ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public CollectionsEndpoint callPOSTRequestToCreateNewCollectionWithTitleAndIsPrivate(String collectionTitle,String true_falseIsPrivate){
        super.addHeader("Authorization",unsplashBearerToken, CollectionsEndpoint.class);
        super.addHeader("Accept-Version","v1", CollectionsEndpoint.class);
        setBaseHost(unsplashHost);
        setEndpointPath(endpointPath);
        addQueryParametersNameWithValue("title", collectionTitle, CollectionsEndpoint.class);
        addQueryParametersNameWithValue("private", true_falseIsPrivate, CollectionsEndpoint.class);
        sendPostRequest(CollectionsEndpoint.class);
        getTestContext().setAttribute("MyPrivateCollectionId",convertResponseToObject(CollectionDataModel.class).id);
        return this;
    }
}
