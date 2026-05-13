package vvss.steps.serenity;

import net.thucydides.core.annotations.Step;
import vvss.pages.DeleteDirectoryPage;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DeleteDirectoryPageSteps {

    DeleteDirectoryPage deleteDirectoryPage;

    @Step
    public void click_delete() {
        deleteDirectoryPage.click_delete_directory();
    }

    @Step
    public void back() {
        deleteDirectoryPage.back();
    }

    @Step
    public void should_see_message(String message) {
        assertThat(deleteDirectoryPage.getContent(), hasItem(containsString(message)));
    }

    @Step
    public void deleteDirectory(String directory) {
        should_see_message("Directory " + directory);
        click_delete();
        should_see_message("Processing directory");
        back();
    }

    @Step
    public void deleteFile(String file) {
        should_see_message("File " + file);
        click_delete();
        should_see_message("All the selected directories and files have been processed.");
        back();
    }
}