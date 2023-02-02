package nt.tshape.automation.selenium.PageModal;

import lombok.SneakyThrows;
import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.WebDriver;

public class UnsplashOauthApplicationsPage extends ActionManager {
    private WebDriver driver;
    public UnsplashOauthApplicationsPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext);
        this.driver = driver;
    }

    //Locator
    private final String appCardByName = "xpath=//h4[contains(@class,'api-app-card__name') and (text()='%s')]";
    private final String keyFieldByName = "xpath=//input[@name='%s']";
    private final String linkButtonByName = "xpath=//a[contains(text(),'%s')]";
    private final String codeXpath = "xpath=//code";

    //Function
    @SneakyThrows
    public UnsplashOauthApplicationsPage clickOnApplicationCardByName(String cardName){
        waitForShortTime();
        clickOnApplicationCardByName(appCardByName.formatted(cardName));
        return this;
    }

    public UnsplashOauthApplicationsPage moveToFieldAndGetDataByName(String fieldName){
        mouseMoveToElementAndClick(keyFieldByName.formatted(fieldName));
        String fieldValue = getText(keyFieldByName.formatted(fieldName));
        getTestContext().setAttribute(fieldName,fieldValue);
        return this;
    }

    @SneakyThrows
    public void clickOnAuthorizeLinkThenGetAuthorizationCode(){
        waitForShortTime();
        mouseMoveToElementAndClick(linkButtonByName.formatted("Authorize"));
        waitForMediumTime();
        waitForElementVisible(codeXpath);
        getTestContext().setAttribute("code",getText(codeXpath));
    }
}
