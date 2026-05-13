package vvss.steps.serenity;

import net.thucydides.core.annotations.Step;
import vvss.pages.ChmodPage;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ChmodPageSteps {

    ChmodPage chmodPage;

    @Step
    public void enter_permissions(String octalValue) {
        chmodPage.enter_chmod_value(octalValue);
    }

    @Step
    public void click_apply() {
        chmodPage.click_apply();
    }

    public void back() {
        chmodPage.back();
    }

    @Step
    public void should_see_message(String message) {
        assertThat(chmodPage.getContent(), hasItem(containsString(message)));
    }

    @Step
    public void changePermissions(String itemName, String octalValue) {
        enter_permissions(octalValue);
        click_apply();
        should_see_message("All the selected directories and files have been processed.");
        back();
    }
}