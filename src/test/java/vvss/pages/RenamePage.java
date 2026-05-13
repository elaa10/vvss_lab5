package vvss.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import java.util.Arrays;
import java.util.List;

public class RenamePage extends PageObject {

    @FindBy(name = "newNames[1]")
    private WebElementFacade newNameInput;

    @FindBy(xpath = "//*[@id=\"RenameForm\"]/a[2]/img")
    private WebElementFacade renameConfirmButton;

    @FindBy(xpath = "//a[contains(@title, 'Back')]")
    private WebElementFacade appBackButton;

    public void enter_new_name(String newName) {
        newNameInput.clear();
        newNameInput.type(newName);
    }

    public void click_rename() {
        renameConfirmButton.click();
    }

    public void back() {
        appBackButton.click();
    }

    public List<String> getContent() {
        String bodyText = getDriver().findElement(By.tagName("body")).getText();
        return Arrays.asList(bodyText.split("\\n"));
    }
}