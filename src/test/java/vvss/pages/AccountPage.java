package vvss.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AccountPage extends PageObject {

    @FindBy(css = "#BrowseForm > input[type=text]:nth-child(24)")
    private WebElementFacade currentDirectory;

    @FindBy(xpath = "//*[@id=\"StatusbarForm\"]/a[4]/img")
    private WebElementFacade logoutButton;

    @FindBy(css = "#buttontable > tbody")
    private WebElementFacade buttonList;

    @FindBy(id = "maintable")
    private WebElementFacade listOfItems;

    public String getCurrentDirectoryName() {
        System.out.println("Current dir value: " + currentDirectory.getValue());
        return currentDirectory.getValue();
    }

    public void click_Logout() {
        logoutButton.click();
    }

    private void clickToolbarButton(String value) {
        List<WebElement> inputs = buttonList.findElements(By.tagName("input"));
        for (WebElement input : inputs) {
            if (value.equalsIgnoreCase(input.getAttribute("value"))) {
                input.click();
                return;
            }
        }
        throw new RuntimeException("Toolbar button not found: " + value);
    }

    public void selectItem(String itemName) {
        listOfItems.waitUntilVisible();
        List<WebElement> inputs = listOfItems.findElements(By.tagName("input"));
        for (WebElement input : inputs) {
            String val = input.getAttribute("value");
            if (val != null && val.equalsIgnoreCase(itemName)) {
                input.click();
                return;
            }
        }
        throw new RuntimeException("Item not found in listing: " + itemName);
    }

    public List<String> getContent() {
        WebElementFacade body = find(By.tagName("body"));
        return body.findElements(By.tagName("a")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void click_new_directory() { clickToolbarButton("New dir"); }
    public void click_delete()        { clickToolbarButton("Delete"); }
    public void click_rename()        { clickToolbarButton("Rename"); }
    public void click_copy()          { clickToolbarButton("Copy"); }
    public void click_upload()        { clickToolbarButton("Upload"); }
    public void click_chmod()         { clickToolbarButton("Chmod"); }
}