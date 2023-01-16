package nt.tshape.automation.selenium.TestCase;

import nt.tshape.automation.config.ConfigLoader;
import nt.tshape.automation.selenium.PageModal.UnsplashHomePage;
import nt.tshape.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;


public class Unsplash_Auto_Flow_ extends WebDriverTestNGSetupBase {

    @Test(alwaysRun = true)
    public void Scenario_1_Follow_a_photographer_successfully() {
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
