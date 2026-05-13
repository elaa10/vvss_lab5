package vvss.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteDirectoryPage extends PageObject {

    // Confirm delete button (second anchor in CopyMoveDeleteForm)
    @FindBy(xpath = "//*[@id=\"CopyMoveDeleteForm\"]/a[2]/img")
    private WebElementFacade deleteButton;

    // Back button (first anchor)
    @FindBy(xpath = "//*[@id=\"CopyMoveDeleteForm\"]/a[1]/img")
    private WebElementFacade backButtonBtn;

    public void click_delete_directory() {
        deleteButton.click();
    }

    public void back() {
        backButtonBtn.click();
    }

    public List<String> getContent() {
        WebElementFacade definitionList = find(By.tagName("div"));
        return definitionList.findElements(By.tagName("form")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
