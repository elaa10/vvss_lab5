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
@UseTestDataFrom("src/test/resources/renameInvalidData.csv")
public class RenameInvalidParameterizedTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps public LoginPageSteps loginPage;
    @Steps public AccountPageSteps accountPage;
    @Steps public NewDirectoryPageSteps newDirPage;
    @Steps public RenamePageSteps renamePage;
    @Steps public DeleteDirectoryPageSteps deletePage;
    @Steps public LogoutPageSteps logoutPage;

    // Columns in renameInvalidData.csv
    String server, user, pass, oldName, invalidNewName, expectedError;

    @Test
    public void invalid_rename_should_show_error() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        loginPage.click_saveCookies();
        loginPage.login_steps(server, user, pass);
        accountPage.should_be_in_user_directory("/home/" + user);

        // Generează un nume unic la fiecare rulare
        String uniqueOldName = oldName + "_" + System.currentTimeMillis();

        accountPage.click_new_directory();
        newDirPage.createDirectory(uniqueOldName);
        accountPage.should_see_item(uniqueOldName);

        accountPage.select_item(uniqueOldName);
        accountPage.click_rename();
        renamePage.enter_new_name(invalidNewName);
        renamePage.click_rename();
        renamePage.should_see_error(expectedError);
        renamePage.back();

        accountPage.should_see_item(uniqueOldName);
        accountPage.select_item(uniqueOldName);
        accountPage.click_delete();
        deletePage.deleteDirectory(uniqueOldName);

        accountPage.logout();
        logoutPage.should_see_logout_message("You have logged out from the FTP server.");
    }
}
