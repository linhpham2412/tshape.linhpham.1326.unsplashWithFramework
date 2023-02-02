package nt.tshape.automation.selenium.TestCase;

import lombok.SneakyThrows;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Collections.CollectionsEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Collections.Id.CollectionIdEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Collections.Id.Remove.CollectionIdRemoveEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Me.MeEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Oauth.Token.OauthTokenEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Photos.Id.Like.PhotoIdLikeEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Photos.Random.PhotoRandomEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Collections.UsernameCollectionsEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Follow.UsersFollowEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Following.UsernameFollowingEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Likes.UsernameLikesEndpoint;
import nt.tshape.automation.selenium.PageModal.*;
import nt.tshape.automation.selenium.Utils;
import nt.tshape.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;


public class Unsplash_Auto_Flow_ extends WebDriverTestNGSetupBase {

    @SneakyThrows
    @Test(alwaysRun = true)
    public void GetUserAccessToken(){
        UnsplashOauthApplicationsPage unsplashOauthApplicationsPage = new UnsplashOauthApplicationsPage(getDriver(), getTestContext());
        OauthTokenEndpoint oauthTokenEndpoint = new OauthTokenEndpoint(getTestContext());
        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(),getTestContext());
        MeEndpoint meEndpoint = new MeEndpoint(getTestContext());

        unsplashHomePage
                .openUnsplashHomePage()
                .clickMenuButtonByName("Log in")
                .openUnsplashLoginPage()
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"),
                        ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage)
                .clickUserProfileButtonByTitle("View more links")
                .clickUserProfileViewMoreLinkSubMenuItem("Developers/API")
                .clickMenuButtonByName("Your apps");
        unsplashOauthApplicationsPage
                .clickOnApplicationCardByName("LinhPhamExercise")
                .moveToFieldAndGetDataByName("access_key")
                .moveToFieldAndGetDataByName("secret_key")
                .clickOnAuthorizeLinkThenGetAuthorizationCode();
        oauthTokenEndpoint.callPOSTRequestToCreateNewAccessToken();
        meEndpoint.callPUTRequestToRestoreUserNameToDefaultValue();
    }

    @SneakyThrows
    @Test(alwaysRun = true)
    public void Scenario_1_Follow_A_Photographer_Successfully() {
        UsernameFollowingEndpoint usernameFollowingEndpoint = new UsernameFollowingEndpoint(getTestContext());
        UsersFollowEndpoint usersFollowEndpoint = new UsersFollowEndpoint(getTestContext());
        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(), getTestContext());
        UnsplashPhotosPage unsplashPhotosPage = new UnsplashPhotosPage(getDriver(), getTestContext());


        //Precondition
        usernameFollowingEndpoint
                .callGETRequestToFollowingEndpointToGetListOfFollowings()
                .verifyUsernameFollowingResponseCodeShouldBe(200);
        usersFollowEndpoint
                .callDELETERequestsToUserFollowEndpointToUnfollowAll();

        //Start Testing
        unsplashHomePage
                .openUnsplashHomePage()
                .clickMenuButtonByName("Log in")
                .openUnsplashLoginPage()
                .verifyLoginPageTitleDisplayCorrect("Login | Unsplash")
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"),
                        ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage)
                .verifyLoginButtonNotExist()
                .clickOnTopLeftImageBlock();

        unsplashPhotosPage
                .hoverToUserProfilePictureAndClickFollowButton()
                .verifyFollowButtonChangeToFollowing();
    }

    @SneakyThrows
    @Test(alwaysRun = true)
    public void Scenario_2_Update_The_Username_URL_In_The_Profile_Page() {
        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(), getTestContext());
        UnsplashUserProfilePage unsplashUserProfilePage = new UnsplashUserProfilePage(getDriver(), getTestContext());
        UnsplashAccountPage unsplashAccountPage = new UnsplashAccountPage(getDriver(), getTestContext());
        MeEndpoint meEndpoint = new MeEndpoint(getTestContext());

        //Start Testing
        unsplashHomePage
                .openUnsplashHomePage()
                .clickMenuButtonByName("Log in")
                .openUnsplashLoginPage()
                .verifyLoginPageTitleDisplayCorrect("Login | Unsplash")
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"),
                        ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage)
                .clickUserProfileButtonByTitle("Your personal menu button")
                .clickUserProfileSubMenuName("View profile");

        unsplashUserProfilePage
                .clickEditProfileButtonByName("Edit Tags");

        unsplashAccountPage
                .inputUserNameValue("linhpham2412_" + Utils.generateRandomTestCharacters(5))
                .scrollToButtonNameAndClick("Update account");

        unsplashUserProfilePage
                .openProfilePageWithNewUserName()
                .verifyUserFullNameDisplay("Linh Pham");

        //Clean up
        meEndpoint.callPUTRequestToRestoreUserNameToDefaultValue();
    }

    @SneakyThrows
    @Test(alwaysRun = true)
    public void Scenario_3_List_Of_Liked_Photos() {
        UsernameLikesEndpoint usernameLikesEndpoint = new UsernameLikesEndpoint(getTestContext());
        PhotoIdLikeEndpoint photoIdLikeEndpoint = new PhotoIdLikeEndpoint(getTestContext());
        PhotoRandomEndpoint photoRandomEndpoint = new PhotoRandomEndpoint(getTestContext());
        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(), getTestContext());
        UnsplashPhotosPage unsplashPhotosPage = new UnsplashPhotosPage(getDriver(), getTestContext());
        UnsplashUsernameLikePage unsplashUsernameLikePage = new UnsplashUsernameLikePage(getDriver(), getTestContext());

        //Precondition
        usernameLikesEndpoint.callGETRequestToGetListOfLikedPhotos();
        photoIdLikeEndpoint.callDELETERequestsToPhotoIdLikeEndpointToUnLikeAll();
        photoRandomEndpoint.sendGETRequestToPhotoRandomEndpointToGetListOfPhotos(3);

        //Start Testing
        unsplashHomePage
                .openUnsplashHomePage()
                .clickMenuButtonByName("Log in")
                .openUnsplashLoginPage()
                .verifyLoginPageTitleDisplayCorrect("Login | Unsplash")
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"),
                        ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage);

        unsplashPhotosPage.openPhotoPageInRandomListAndLikeThePhoto();

        unsplashUsernameLikePage
                .openUsernameLikePage()
                .verifyUsernameMenuFieldNameWithValue("Likes", "3")
                .verifyNumberOfImagesInPage("3");
    }

    @SneakyThrows
    @Test(alwaysRun = true)
    public void Scenario_4_Remove_Photos_From_The_Collection_Successfully() {
        UsernameCollectionsEndpoint usernameCollectionsEndpoint = new UsernameCollectionsEndpoint(getTestContext());
        CollectionIdEndpoint collectionIdEndpoint = new CollectionIdEndpoint(getTestContext());
        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(), getTestContext());
        PhotoRandomEndpoint photoRandomEndpoint = new PhotoRandomEndpoint(getTestContext());
        CollectionsEndpoint collectionsEndpoint = new CollectionsEndpoint(getTestContext());
        UnsplashUsernameCollectionsPage unsplashUsernameCollectionsPage = new UnsplashUsernameCollectionsPage(getDriver(), getTestContext());
        CollectionIdRemoveEndpoint collectionIdRemoveEndpoint = new CollectionIdRemoveEndpoint(getTestContext());
        UnsplashCollectionCollectionIdPage unsplashCollectionCollectionIdPage = new UnsplashCollectionCollectionIdPage(getDriver(), getTestContext());

        //Precondition
        usernameCollectionsEndpoint.callGETRequestUsernameCollectionsEndpointToRetrieveListOfCollection();
        collectionIdEndpoint.callDELETERequestsToCollectionIdEndpointToRemoveCollections();
        UnsplashPhotosPage unsplashPhotosPage = new UnsplashPhotosPage(getDriver(), getTestContext());
        photoRandomEndpoint.sendGETRequestToPhotoRandomEndpointToGetListOfPhotos(2);

        //Start Testing
        unsplashHomePage
                .openUnsplashHomePage()
                .clickMenuButtonByName("Log in")
                .openUnsplashLoginPage()
                .verifyLoginPageTitleDisplayCorrect("Login | Unsplash")
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"),
                        ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage)
                .verifyLoginButtonNotExist();

        collectionsEndpoint.callPOSTRequestToCreateNewCollectionWithTitleAndIsPrivate("My new private Collection", "true");

        unsplashPhotosPage.openPhotoPageInRandomListAndAddToCollectionName("My new private Collection");

        unsplashUsernameCollectionsPage
                .openUsernameCollectionsPage()
                .verifyUsernameCollectionMenuFieldNameWithValue("Collections", "1")
                .verifyCollectionNameExist("My new private Collection")
                .clickOnCollectionWithName("My new private Collection");

        unsplashCollectionCollectionIdPage.verifyNumberOfImagesInPage("2");

        collectionIdRemoveEndpoint.callDELETERequestsToCollectionIdRemoveEndpointToRemove1PhotoInCollection(0);

        unsplashCollectionCollectionIdPage
                .openUsernameCollectionsWithSavedCollectionIdPage()
                .verifyRemovedPhotoNotExist()
                .verifyNumberOfImagesInPage("1");
    }

    @SneakyThrows
    @Test(alwaysRun = true)
    public void Scenario_5_Download_Photo_Successfully() {
        PhotoRandomEndpoint photoRandomEndpoint = new PhotoRandomEndpoint(getTestContext());
        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(), getTestContext());
        UnsplashPhotosPage unsplashPhotosPage = new UnsplashPhotosPage(getDriver(), getTestContext());
        UnsplashAccountPage unsplashAccountPage = new UnsplashAccountPage(getDriver(), getTestContext());
        UnsplashUserProfilePage unsplashUserProfilePage = new UnsplashUserProfilePage(getDriver(), getTestContext());
        UnsplashAccountHistoryPage unsplashAccountHistoryPage = new UnsplashAccountHistoryPage(getDriver(), getTestContext());

        //Precondition
        photoRandomEndpoint.sendGETRequestToPhotoRandomEndpointToGetListOfPhotos(1);

        //Start Testing
        unsplashHomePage
                .openUnsplashHomePage()
                .clickMenuButtonByName("Log in")
                .openUnsplashLoginPage()
                .verifyLoginPageTitleDisplayCorrect("Login | Unsplash")
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"),
                        ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage)
                .verifyLoginButtonNotExist();

        unsplashPhotosPage
                .openPhotoPageInRandomListWithIndex(0)
                .clickLinkButtonInPageWithName("Download");

        unsplashHomePage
                .clickUserProfileButtonByTitle("Your personal menu button")
                .clickUserProfileSubMenuName("View profile");

        unsplashUserProfilePage.clickEditProfileButtonByName("Edit Tags");

        unsplashAccountPage.clickOnLinkButtonByName("Download history");

        unsplashAccountHistoryPage
                .getDownloadedItemNameByIndexInPage(0)
                .verifyDownloadedFileExistInDownloadFolder();
    }
}
