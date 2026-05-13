package vvss.steps.serenity;

import net.thucydides.core.annotations.Step;
import vvss.pages.NewDirectoryPage;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class NewDirectoryPageSteps {

    NewDirectoryPage newDirectoryPage;

    @Step
    public void enter_directory(String directory) {
        newDirectoryPage.enter_directory(directory);
    }

    @Step
    public void click_create() {
        newDirectoryPage.click_to_create_Directory();
    }

    @Step
    public void back() {
        newDirectoryPage.back();
    }

    @Step
    public void should_see_message(String message) {
        assertThat(newDirectoryPage.getContent(), hasItem(containsString(message)));
    }

    /** Composite: type name → create → assert success → back. */
    @Step
    public void createDirectory(String directory) {
        enter_directory(directory);
        click_create();
        should_see_message("Directory " + directory + " was successfully created.");
        back();
    }
}
