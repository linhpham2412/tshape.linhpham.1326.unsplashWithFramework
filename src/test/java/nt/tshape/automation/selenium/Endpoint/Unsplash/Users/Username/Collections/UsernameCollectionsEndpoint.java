package nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.CollectionDataModel;
import nt.tshape.automation.selenium.TestContext;

import java.util.ArrayList;

public class UsernameCollectionsEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/users/%username%/collections";
    public UsernameCollectionsEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer " + ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public UsernameCollectionsEndpoint callGETRequestUsernameCollectionsEndpointToRetrieveListOfCollection() throws JsonProcessingException {
        String usernameValue = "";
        if (getTestContext().getAttributeByName("Username") == null){
            usernameValue = ConfigLoader.getEnvironment("unsplashAccount");
        }else{
            usernameValue = getTestContext().getAttributeByName("Username");
        }
        super.addHeader("Authorization", unsplashBearerToken, UsernameCollectionsEndpoint.class);
        super.addHeader("Accept-Version", "v1", UsernameCollectionsEndpoint.class);
        setBaseHost(unsplashHost);
        endpointPath = endpointPath.replaceAll("%username%",usernameValue);
        setEndpointPath(endpointPath);
        sendGETRequest(UsernameCollectionsEndpoint.class);
        ArrayList<CollectionDataModel> collectionDataModelArrayList = (ArrayList<CollectionDataModel>) convertResponseToListObjects();
        getTestContext().setContextObjectsWithName("CollectionList", collectionDataModelArrayList);
        return this;
    }
}
