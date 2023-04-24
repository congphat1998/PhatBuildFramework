package testcases;

import bases.BaseTest;
import org.example.model.User;
import org.example.steps.CartPageSteps;
import org.example.steps.CheckoutPageSteps;
import org.example.steps.InventoryPageSteps;
import org.example.steps.LoginPageSteps;
import org.example.utils.ExcelConfig;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.example.constant.Constants.URL;

public class SauceDemoExcel extends BaseTest {
    ExcelConfig excelConfig = new ExcelConfig("src/main/resources/data.xlsx");
    LoginPageSteps loginPageSteps;
    InventoryPageSteps inventoryPageSteps;
    CartPageSteps cartPageSteps;
    CheckoutPageSteps checkoutPageSteps;

    @DataProvider(name = "excelUser")
    public Object[][] getCredential(){
        return excelConfig.data();
    }
    @Test(dataProvider = "excelUser", groups = {"group 2"})
    public void loginTest(String userName, String password, String fn, String ln, String zc){
        loginPageSteps = new LoginPageSteps();
        loginPageSteps.goToUrl(URL).verifyTitle();
        loginPageSteps.loginWithCredentials(userName,password);
    }
    @Test(dataProvider = "excelUser", groups = {"group 2"})
    public void buyTest(String userName, String password, String fn, String ln, String zc){
        loginPageSteps = new LoginPageSteps();
        loginPageSteps.goToUrl(URL)
                .verifyTitle()
                .loginWithCredentials(userName,password);
        inventoryPageSteps = new InventoryPageSteps();
        inventoryPageSteps.addRandomItem()
                .validateRemoveButton(1)
                .validateCartCount(1)
                .addRandomItem()
                .validateRemoveButton(2)
                .validateCartCount(2)
                .clickCartButton();
        cartPageSteps = new CartPageSteps();
        cartPageSteps.validateCartAdded(2)
                .clickCheckoutButton();
        checkoutPageSteps = new CheckoutPageSteps();
        checkoutPageSteps.validateUrlCheckoutStep1()
                .inputUserInfo(fn,ln,zc)
                .validateUrlCheckoutStep2()
                .clickFinishBTN()
                .validateUrlCheckoutComplete()
                .clickBACKHOMEBTN()
                .validateUrlInventory();
    }
}
