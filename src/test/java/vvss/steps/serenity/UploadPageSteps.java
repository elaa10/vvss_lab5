package vvss.steps.serenity;

import net.thucydides.core.annotations.Step;
import vvss.pages.UploadPage;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UploadPageSteps {

    UploadPage uploadPage;

    @Step
    public void enter_file_path(String absolutePath) {
        uploadPage.enter_file(absolutePath);
    }

    @Step
    public void click_upload() {
        uploadPage.click_upload();
    }

    @Step
    public void back() {
        uploadPage.back();
    }

    @Step
    public void should_see_message(String message) {
        assertThat(uploadPage.getContent(), hasItem(containsString(message)));
    }

    @Step
    public void uploadFile(String absolutePath, String fileName) {
        enter_file_path(absolutePath);
        click_upload();
        should_see_message("has been transferred to the FTP server");
        back();
    }
}