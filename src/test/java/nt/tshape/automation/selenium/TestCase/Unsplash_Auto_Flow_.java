package nt.tshape.automation.selenium.TestCase;

import lombok.SneakyThrows;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Photos.Id.Like.PhotoIdLikeEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Photos.Random.PhotoRandomEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Follow.UsersFollowEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Following.UsernameFollowingEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Me.MeEndpoint;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Likes.UsernameLikesEndpoint;
import nt.tshape.automation.selenium.PageModal.*;
import nt.tshape.automation.selenium.Utils;
import nt.tshape.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;


public class Unsplash_Auto_Flow_ extends WebDriverTestNGSetupBase {

    @SneakyThrows
    @Test(alwaysRun = true)
    public void Scenario_1_Follow_A_Photographer_Successfully() {
        UsernameFollowingEndpoint usernameFollowingEndpoint = new UsernameFollowingEndpoint(getTestContext());
        UsersFollowEndpoint usersFollowEndpoint = new UsersFollowEndpoint(getTestContext());
        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(), getTestContext());

        //Precondition
        usernameFollowingEndpoint
                .callGETRequestToFollowingEndpointToGetListOfFollowings()
                .verifyUsernameFollowingResponseCodeShouldBe(200);

        usersFollowEndpoint
                .callDELETERequestsToUserFollowEndpointToUnfollowAll();

        //Start Testing
        unsplashHomePage
                .openUnsplashHomePage()
                .clickLoginButton()
                .openUnsplashLoginPage()
                .verifyLoginPageTitleDisplayCorrect("Login | Unsplash")
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"),
                        ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage)
                .verifyLoginButtonNotExist()
                .clickOnTopLeftImageBlock()
                .hoverToUserProfilePictureAndClickFollowButton()
                .verifyFollowButtonChangeToFollowing();
    }
    @SneakyThrows
    @Test(alwaysRun = true)
    public void Scenario_2_Update_The_Username_URL_In_The_Profile_Page(){
        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(),getTestContext());
        UnsplashUserProfilePage unsplashUserProfilePage = new UnsplashUserProfilePage(getDriver(),getTestContext());
        UnplashAccountPage unplashAccountPage = new UnplashAccountPage(getDriver(),getTestContext());
        MeEndpoint meEndpoint = new MeEndpoint(getTestContext());

        //Start Testing
        unsplashHomePage
                .openUnsplashHomePage()
                .clickLoginButton()
                .openUnsplashLoginPage()
                .verifyLoginPageTitleDisplayCorrect("Login | Unsplash")
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"),
                        ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage)
                .clickUserProfileButton()
                .clickUserProfileSubMenuName("View profile");

        unsplashUserProfilePage
                .clickEditProfileButtonByName("Edit Tags");

        unplashAccountPage
                .inputUserNameValue("linhpham2412_"+ Utils.generateRandomTestCharacters(5))
                .scrollToButtonNameAndClick("Update account");

        unsplashUserProfilePage
                .openProfilePageWithNewUserName()
                .verifyUserFullNameDisplay("Linh Pham");

        //Clean up
        meEndpoint.callPUTRequestToRestoreUserNameToDefaultValue();
    }

    @SneakyThrows
    @Test(alwaysRun = true)
    public void Scenario_3_List_Of_Liked_Photos(){
        UsernameLikesEndpoint usernameLikesEndpoint = new UsernameLikesEndpoint(getTestContext());
        PhotoIdLikeEndpoint photoIdLikeEndpoint = new PhotoIdLikeEndpoint(getTestContext());
        PhotoRandomEndpoint photoRandomEndpoint = new PhotoRandomEndpoint(getTestContext());
        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(),getTestContext());
        UnsplashPhotosPage unsplashPhotosPage = new UnsplashPhotosPage(getDriver(),getTestContext());
        UnsplashUsernameLikePage unsplashUsernameLikePage = new UnsplashUsernameLikePage(getDriver(),getTestContext());

        //Precondition
        usernameLikesEndpoint.callGETRequestToGetListOfLikedPhotos();
        photoIdLikeEndpoint.callDELETERequestsToPhotoIdLikeEndpointToUnLikeAll();
        photoRandomEndpoint.sendGETRequestToPhotoRandomEndpointToGetListOfPhotos(3);

        //Start Testing
        unsplashHomePage
                .openUnsplashHomePage()
                .clickLoginButton()
                .openUnsplashLoginPage()
                .verifyLoginPageTitleDisplayCorrect("Login | Unsplash")
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"),
                        ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage);

        unsplashPhotosPage.openPhotoPageInRandomListAndLikeThePhoto();

        unsplashUsernameLikePage
                .openUsernameLikePage()
                .verifyUsernameMenuFieldNameWithValue("Likes","3")
                .verifyNumberOfImagesInPage("3");
    }
}
