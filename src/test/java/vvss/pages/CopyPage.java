package vvss.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class CopyPage extends PageObject {

    @FindBy(name = "list[1][targetdirectory]")
    private WebElementFacade destinationInput;

    @FindBy(name = "list[1][newname]")
    private WebElementFacade targetNameInput;

    @FindBy(xpath = "//*[@id=\"CopyMoveDeleteForm\"]/a[2]/img")
    private WebElementFacade copyConfirmButton;

    @FindBy(xpath = "//*[@id=\"CopyMoveDeleteForm\"]/a[1]/img")
    private WebElementFacade backButton;

    public void enter_destination(String destination) {
        destinationInput.clear();
        destinationInput.type(destination);
    }

    public void enter_target_name(String name) {
        targetNameInput.clear();
        targetNameInput.type(name);
    }

    public void click_copy() {
        copyConfirmButton.click();
    }

    public void back() {
        backButton.click();
    }

    public List<String> getContent() {
        String bodyText = getDriver().findElement(By.tagName("body")).getText();
        return Arrays.asList(bodyText.split("\\n"));
    }
}