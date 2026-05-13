package vvss.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import vvss.pages.AccountPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AccountPageSteps {

    AccountPage accountPage;

    @Step
    public void should_be_in_user_directory(String expectedPath) {
        Assert.assertTrue(
            "Expected directory to contain: " + expectedPath,
            expectedPath.contains(accountPage.getCurrentDirectoryName())
        );
    }

    @Step
    public void logout() {
        accountPage.click_Logout();
    }

    // ---------- New directory ----------

    @Step
    public void click_new_directory() {
        accountPage.click_new_directory();
    }

    @Step
    public void should_see_item(String name) {
        assertThat(accountPage.getContent(), hasItem(containsString(name)));
    }

    @Step
    public void should_not_see_item(String name) {
        assertThat(accountPage.getContent(), not(hasItem(containsString(name))));
    }

    // ---------- Delete ----------

    @Step
    public void select_item(String name) {
        accountPage.selectItem(name);
    }

    @Step
    public void click_delete() {
        accountPage.click_delete();
    }

    // ---------- Rename ----------

    @Step
    public void click_rename() {
        accountPage.click_rename();
    }

    // ---------- Copy ----------

    @Step
    public void click_copy() {
        accountPage.click_copy();
    }

    // ---------- Upload ----------

    @Step
    public void click_upload() {
        accountPage.click_upload();
    }

    // ---------- Chmod ----------

    @Step
    public void click_chmod() {
        accountPage.click_chmod();
    }
}
