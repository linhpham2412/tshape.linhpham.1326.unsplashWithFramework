package nt.tshape.automation.selenium.Endpoint.Unsplash.Collections.Id;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.CollectionDataModel;
import nt.tshape.automation.selenium.DataModel.FollowingDataModel;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Follow.UsersFollowEndpoint;
import nt.tshape.automation.selenium.TestContext;

import java.util.List;

public class CollectionIdEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/collections/%id%";
    public CollectionIdEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer "+ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public CollectionIdEndpoint callDELETERequestsToCollectionIdEndpointToRemoveCollections() {
        super.addHeader("Authorization", unsplashBearerToken, CollectionIdEndpoint.class);
        super.addHeader("Accept-Version", "v1", CollectionIdEndpoint.class);
        setBaseHost(unsplashHost);
        ObjectMapper mapper = new ObjectMapper();
        List<CollectionDataModel> collectionDataModelList = mapper.convertValue(getTestContext().getContextObjectsWithName("CollectionList"),
                new TypeReference<List<CollectionDataModel>>() {
                });
        for (int i = 0; i < collectionDataModelList.size(); i++) {
            endpointPath = endpointPath.replaceAll("%id%", collectionDataModelList.get(i).id);
            setEndpointPath(endpointPath);
            sendDeleteRequest(CollectionIdEndpoint.class);
            endpointPath = endpointPath.replaceAll(collectionDataModelList.get(i).id, "%id%");
        }
        return this;
    }
}
