package vvss.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class UploadPage extends PageObject {

    @FindBy(name = "file[]")
    private WebElementFacade fileInput;

    @FindBy(xpath = "//a[@accesskey='v']")
    private WebElementFacade uploadConfirmButton;

    @FindBy(xpath = "//a[contains(@title, 'Back')]")
    private WebElementFacade appBackButton;

    public void enter_file(String absolutePath) {
        fileInput.sendKeys(absolutePath);
    }

    public void click_upload() {
        uploadConfirmButton.click();
    }

    public List<String> getContent() {
        String bodyText = getDriver().findElement(By.tagName("body")).getText();
        return Arrays.asList(bodyText.split("\\n"));
    }
    
    public void back() {
        appBackButton.click();
    }
}