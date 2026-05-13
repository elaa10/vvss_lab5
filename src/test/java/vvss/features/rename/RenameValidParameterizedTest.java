package vvss.features.rename;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import vvss.steps.serenity.*;


@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/renameValidData.csv")
public class RenameValidParameterizedTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps public LoginPageSteps loginPage;
    @Steps public AccountPageSteps accountPage;
    @Steps public NewDirectoryPageSteps newDirPage;
    @Steps public RenamePageSteps renamePage;
    @Steps public DeleteDirectoryPageSteps deletePage;
    @Steps public LogoutPageSteps logoutPage;

    // Columns in renameValidData.csv
    String server, user, pass, oldName, newName;

    @Test
    public void valid_rename_should_succeed() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        loginPage.click_saveCookies();
        loginPage.login_steps(server, user, pass);
        accountPage.should_be_in_user_directory("/home/" + user);

        // Create the directory we're going to rename
        accountPage.click_new_directory();
        newDirPage.createDirectory(oldName);
        accountPage.should_see_item(oldName);

        // Select it, then rename
        accountPage.select_item(oldName);
        accountPage.click_rename();
        renamePage.renameDirectory(oldName, newName);

        // Verify rename result
        accountPage.should_see_item(newName);
        accountPage.should_not_see_item(oldName);

        // Cleanup: delete the renamed directory
        accountPage.select_item(newName);
        accountPage.click_delete();
        deletePage.deleteDirectory(newName);
        accountPage.should_not_see_item(newName);

        accountPage.logout();
        logoutPage.should_see_logout_message("You have logged out from the FTP server.");
    }
}
