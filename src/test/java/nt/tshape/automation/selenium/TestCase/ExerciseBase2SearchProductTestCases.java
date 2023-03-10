package nt.tshape.automation.selenium.TestCase;

import nt.tshape.automation.selenium.Constant;
import nt.tshape.automation.selenium.PageModal.AutomationPracticeIndexPage;
import nt.tshape.automation.setup.WebDriverTestNGSetupBase;
import org.testng.annotations.Test;

public class ExerciseBase2SearchProductTestCases extends WebDriverTestNGSetupBase {

    @Test
    public void CollectProductNameAndSearch() {
        AutomationPracticeIndexPage automationPracticeIndexPage = new AutomationPracticeIndexPage(getDriver(), getTestContext());

        automationPracticeIndexPage
                .openPage()
                .moveToMenuWithNameAndClickOnButtonByFieldName("Women", "T-shirts")
                .focusOnProductInListByIndex("1")
                .getDetailDataOfFocusedProductByName(Constant.PRODUCT_NAME)
                .getDetailDataOfFocusedProductByName(Constant.PRODUCT_CURRENT_PRICE)
                .inputSearchTextIntoSearchField(getTestContext().getAttributeByName(Constant.PRODUCT_NAME))
                .clickButtonByName("Search")
                .verifyDetailDataOfSavedProductDisplayOnSearchByName(Constant.PRODUCT_NAME)
                .verifyDetailDataOfSavedProductDisplayOnSearchByName(Constant.PRODUCT_CURRENT_PRICE);
    }
}
