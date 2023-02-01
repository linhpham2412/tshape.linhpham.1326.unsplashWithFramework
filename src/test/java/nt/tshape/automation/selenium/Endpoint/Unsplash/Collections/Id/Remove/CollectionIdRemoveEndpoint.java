package nt.tshape.automation.selenium.Endpoint.Unsplash.Collections.Id.Remove;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.DataModel.FollowingDataModel;
import nt.tshape.automation.selenium.DataModel.PhotoDataModel;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Collections.CollectionsEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Collections.Id.CollectionIdEndpoint;
import nt.tshape.automation.selenium.TestContext;

import java.util.List;

public class CollectionIdRemoveEndpoint extends UniversalEndpoint {
    private String unsplashHost;
    private String unsplashBearerToken;
    private String endpointPath = "/collections/%id%/remove";

    public CollectionIdRemoveEndpoint(TestContext testContext) {
        super(testContext);
        unsplashHost = ConfigLoader.getEnvironment("unsplashHost");
        unsplashBearerToken = "Bearer " + ConfigLoader.getEnvironment("unsplashBearerToken");
    }

    public CollectionIdRemoveEndpoint callDELETERequestsToCollectionIdRemoveEndpointToRemove1PhotoInCollection(int index) {
        ObjectMapper mapper = new ObjectMapper();
        List<PhotoDataModel> photoDataModelList = mapper.convertValue(getTestContext().getContextObjectsWithName("RandomPhotoList"),
                new TypeReference<List<PhotoDataModel>>() {
                });
        super.addHeader("Authorization", unsplashBearerToken, CollectionIdRemoveEndpoint.class);
        super.addHeader("Accept-Version", "v1", CollectionIdRemoveEndpoint.class);
        setBaseHost(unsplashHost);
        endpointPath = endpointPath.replaceAll("%id%", getTestContext().getAttributeByName("MyPrivateCollectionId"));
        setEndpointPath(endpointPath);
        addQueryParametersNameWithValue("collection_id", getTestContext().getAttributeByName("MyPrivateCollectionId"), CollectionIdRemoveEndpoint.class);
        addQueryParametersNameWithValue("photo_id", photoDataModelList.get(index).id, CollectionIdRemoveEndpoint.class);
        sendDeleteRequest(CollectionIdEndpoint.class);
        getTestContext().setAttribute("AltValueOfRemovedPhoto",photoDataModelList.get(index).alt_description);
        return this;
    }
}
