package nt.tshape.automation.selenium.Endpoint.Users.UserID;

import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.Endpoint.Users.UserEndpoint;
import nt.tshape.automation.selenium.TestContext;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class UserIdEndpoint extends UniversalEndpoint {
    private final String endpointPath = "api/users/{id}";
    private final String requestBodyLocation = "src/main/resources/RequestJSON/createUsersJSON.json";

    public UserIdEndpoint(TestContext testContext) {
        super(testContext);
        setEndpointPath(endpointPath);
        setBaseHost(ConfigLoader.getEnvironment("apiHost"));
    }

    public UserIdEndpoint callGETRequestBySavedUserId(){
        super.changeEndpointPathParameterNameWithValue("{id}",getTestContext().getAttributeByName("UserID"), UserIdEndpoint.class);
        super.sendGETRequest(UserIdEndpoint.class);
        return this;
    }

    @SneakyThrows
    public UserIdEndpoint addUserIdRequestBody() {
        createRequestBody(Files.readAllLines(Paths.get(requestBodyLocation)).stream().collect(Collectors.joining()), UserEndpoint.class);
        return this;
    }

    public UserIdEndpoint changeUserIdRequestFieldNameToValue(String fieldName, String fieldValue) {
        updateRequestFieldWithValue(fieldName, fieldValue, UserEndpoint.class);
        return this;
    }

    public UserIdEndpoint callUPDATERequestBySavedUserId(){
        super.changeEndpointPathParameterNameWithValue("{id}",getTestContext().getAttributeByName("UserID"), UserIdEndpoint.class);
        super.sendPutRequest(UserIdEndpoint.class);
        return this;
    }

    public UserIdEndpoint callDELETERequestBySavedUserId(){
        super.changeEndpointPathParameterNameWithValue("{id}",getTestContext().getAttributeByName("UserID"), UserIdEndpoint.class);
        super.sendDeleteRequest(UserIdEndpoint.class);
        return this;
    }

    //Verify
    public UserIdEndpoint verifyUserIDEndpointResponseCodeEqual(int expectedCode) {
        verifyEndpointResponseCodeEqual(expectedCode, UserEndpoint.class);
        return this;
    }
}
