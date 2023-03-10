package nt.tshape.automation.selenium.Endpoint.Users;

import nt.tshape.automation.apimanager.UniversalEndpoint;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.TestContext;
import lombok.SneakyThrows;
import nt.tshape.automation.selenium.DataModel.UsersDataModel;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class UserEndpoint extends UniversalEndpoint {
    private final String endpointPath = "api/users";
    private final String requestBodyLocation = "src/main/resources/RequestJSON/createUsersJSON.json";

    public UserEndpoint(TestContext testContext) {
        super(testContext);
        setEndpointPath(endpointPath);
        setBaseHost(ConfigLoader.getEnvironment("apiHost"));
    }

    @SneakyThrows
    public UserEndpoint callGETRequest() {
        sendGETRequest(UserEndpoint.class);
        return this;
    }

    public UserEndpoint addQueryParamNameWithValue(String paramName, String paramValue) {
        addQueryParametersNameWithValue(paramName, paramValue, UserEndpoint.class);
        return this;
    }


    public UserEndpoint addCustomHeader(String headerName, String headerValue) {
        addHeader(headerName, headerValue, UserEndpoint.class);
        return this;
    }

    @SneakyThrows
    public UserEndpoint addUserRequestBody() {
        createRequestBody(Files.readAllLines(Paths.get(requestBodyLocation)).stream().collect(Collectors.joining()), UserEndpoint.class);
        return this;
    }

    public UserEndpoint changeRequestFieldNameToValue(String fieldName, String fieldValue) {
        updateRequestFieldWithValue(fieldName, fieldValue, UserEndpoint.class);
        return this;
    }

    @SneakyThrows
    public UserEndpoint callPostToUserEndpointRequestWithBodyAndSaveCreatedUserId() {
        sendPostRequest(UserEndpoint.class);
        UsersDataModel createdUser = super.convertResponseToObject(UsersDataModel.class);
        getTestContext().setAttribute("UserID", createdUser.id);
        return this;
    }

    //Verify
    public UserEndpoint verifyUserEndpointResponseCodeEqual(int expectedCode) {
        verifyEndpointResponseCodeEqual(expectedCode, UserEndpoint.class);
        return this;
    }

    public UserEndpoint verifyResponseUserFieldWithValue(String fieldName, String expectedValue) {
        verifyResponseFieldNameWithValue(fieldName, expectedValue, UserEndpoint.class);
        return this;
    }

    public UserEndpoint verifyResponseUserFieldExist(String fieldName) {
        verifyResponseFieldExist(fieldName, UserEndpoint.class);
        return this;
    }
}
