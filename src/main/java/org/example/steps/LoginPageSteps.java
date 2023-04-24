package org.example.steps;

import io.qameta.allure.Step;
import org.example.core.basehandle.BaseWebUI;
import org.example.pages.LoginPage;
import org.testng.Assert;

public class LoginPageSteps extends BaseWebUI {
    @Step("Go to : {0}")
    public LoginPageSteps goToUrl(String url){
        visitWebsite(url);
        return this;
    }

    @Step("Verify title")
    public LoginPageSteps verifyTitle(){
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Swag Labs"), "Title not same : [" + title + "] please recheck the script");
        return this;
    }
    @Step("Login to the system")
    public void loginWithCredentials(String user, String pass){
        sendKeys(LoginPage.USERNAME_FIELD, user);
        sendKeys(LoginPage.PASSWORD_FIELD, pass);
        clickElement(LoginPage.LOGIN_BTN);
        containsCurrentURL("/inventory.html");
    }
}
