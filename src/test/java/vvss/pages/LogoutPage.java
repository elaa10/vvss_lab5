package vvss.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class LogoutPage extends PageObject {

    // Returnează toate liniile de text vizibil din pagină
    public List<String> getDefinitions() {
        String bodyText = getDriver().findElement(By.tagName("body")).getText();
        return Arrays.asList(bodyText.split("\\n"));
    }
}