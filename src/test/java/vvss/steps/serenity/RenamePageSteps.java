package vvss.steps.serenity;

import net.thucydides.core.annotations.Step;
import vvss.pages.RenamePage;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class RenamePageSteps {

    RenamePage renamePage;

    @Step
    public void enter_new_name(String newName) {
        renamePage.enter_new_name(newName);
    }

    @Step
    public void click_rename() {
        renamePage.click_rename();
    }

    @Step
    public void back() {
        renamePage.back();
    }

    @Step
    public void should_see_message(String message) {
        assertThat(renamePage.getContent(), hasItem(containsString(message)));
    }

    @Step
    public void should_see_error(String error) {
        assertThat(renamePage.getContent(), hasItem(containsString(error)));
    }


    @Step
    public void renameDirectory(String oldName, String newName) {
        enter_new_name(newName);
        click_rename();
        should_see_message("was successfully renamed");
        back();
    }
}
