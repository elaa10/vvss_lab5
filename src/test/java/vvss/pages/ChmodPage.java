package vvss.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class ChmodPage extends PageObject {

    @FindBy(name = "chmod1")
    private WebElementFacade chmodValueInput;

    @FindBy(xpath = "//a[@accesskey='v']")
    private WebElementFacade chmodConfirmButton;

    @FindBy(xpath = "//a[contains(@title, 'Back')]")
    private WebElementFacade appBackButton;

    public void enter_chmod_value(String octalValue) {
        chmodValueInput.clear();
        chmodValueInput.type(octalValue);
    }

    public void click_apply() {
        chmodConfirmButton.click();
    }

    public List<String> getContent() {
        String bodyText = getDriver().findElement(By.tagName("body")).getText();
        return Arrays.asList(bodyText.split("\\n"));
    }

    // FOLOSIM BUTONUL ÎN LOC DE NAVIGATE().BACK()
    public void back() {
        appBackButton.click();
    }
}