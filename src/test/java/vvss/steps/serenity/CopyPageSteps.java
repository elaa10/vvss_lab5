package vvss.steps.serenity;

import net.thucydides.core.annotations.Step;
import vvss.pages.CopyPage;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CopyPageSteps {

    CopyPage copyPage;

    @Step
    public void enter_destination(String destination) {
        copyPage.enter_destination(destination);
    }

    @Step
    public void enter_target_name(String name) {
        copyPage.enter_target_name(name);
    }

    @Step
    public void click_copy() {
        copyPage.click_copy();
    }

    @Step
    public void back() {
        copyPage.back();
    }

    @Step
    public void should_see_message(String message) {
        assertThat(copyPage.getContent(), hasItem(containsString(message)));
    }

    @Step
    public void copyDirectory(String sourceName, String targetDir, String targetName) {
        enter_destination(targetDir);
        enter_target_name(targetName);
        click_copy();
        should_see_message("All the selected directories and files have been processed.");
        back();
    }
}