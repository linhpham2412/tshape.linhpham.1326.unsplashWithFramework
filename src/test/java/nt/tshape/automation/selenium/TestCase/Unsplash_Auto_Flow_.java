package nt.tshape.automation.selenium.TestCase;

import lombok.SneakyThrows;
import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.Endpoint.Unsplash.Users.Username.Following.UsernameFollowingEndpoint;
import nt.tshape.automation.selenium.PageModal.UnsplashHomePage;
import nt.tshape.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;


public class Unsplash_Auto_Flow_ extends WebDriverTestNGSetupBase {

    @SneakyThrows
    @Test(alwaysRun = true)
    public void Scenario_1_Follow_a_photographer_successfully() {
        UsernameFollowingEndpoint usernameFollowingEndpoint = new UsernameFollowingEndpoint(getTestContext());
        usernameFollowingEndpoint
                .callGETRequestToFollowingEndpointToGetListOfFollowings();

        UnsplashHomePage unsplashHomePage = new UnsplashHomePage(getDriver(), getTestContext());
        unsplashHomePage
                .openUnsplashHomePage()
                .clickLoginButton()
                .openUnsplashLoginPage()
                .verifyLoginPageTitleDisplayCorrect("Login | Unsplash")
                .loginWithUsernameAndPasswordAndReturnToHomePage(ConfigLoader.getEnvironment("unsplashEmail"), ConfigLoader.getEnvironment("unsplashPassword"), unsplashHomePage)
                .verifyLoginButtonNotExist();
    }
}
