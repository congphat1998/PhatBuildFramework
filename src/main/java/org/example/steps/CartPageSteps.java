package org.example.steps;

import io.qameta.allure.Step;
import org.example.core.basehandle.BaseWebUI;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static org.example.pages.CartPage.CHECKOUT_BTN;
import static org.example.pages.CartPage.COUNT_ITEM;

public class CartPageSteps extends BaseWebUI {

    @Step("Check cart added is {0}")
    public CartPageSteps validateCartAdded(int number){
        List<WebElement> CARTADDED = driver.findElements(COUNT_ITEM);
        Assert.assertTrue(CARTADDED.size() == number, "Remove button is " + CARTADDED.size() + " and not equals with " + number);
        return this;
    }
    @Step("Click Checkout Button")
    public CartPageSteps clickCheckoutButton(){
        clickElement(CHECKOUT_BTN);
        return this;
    }

}
