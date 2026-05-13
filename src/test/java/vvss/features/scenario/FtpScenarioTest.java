package vvss.features.scenario;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import vvss.steps.serenity.*;

import java.io.File;


@RunWith(SerenityRunner.class)
public class FtpScenarioTest {

    private static final String BASE_URL =
            "https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php";

    private static final String FTP_SERVER = "localhost";
    private static final String FTP_USER   = "vvta1";
    private static final String FTP_PASS   = "vvta1";

    private static final String ORIGINAL_DIR = "scenarioDir_" + System.currentTimeMillis();
    private static final String RENAMED_DIR  = "renamedDir_" + System.currentTimeMillis();
    private static final String COPIED_DIR   = "copiedDir_" + System.currentTimeMillis();
    private static final String TEST_FILE    = "test_upload_vvss.txt";

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps public LoginPageSteps      loginPage;
    @Steps public AccountPageSteps    accountPage;
    @Steps public NewDirectoryPageSteps newDirPage;
    @Steps public RenamePageSteps     renamePage;
    @Steps public CopyPageSteps       copyPage;
    @Steps public UploadPageSteps     uploadPage;
    @Steps public ChmodPageSteps      chmodPage;
    @Steps public DeleteDirectoryPageSteps deletePage;
    @Steps public LogoutPageSteps     logoutPage;

    @Issue("#Scenario-FTP-full-flow")
    @Test
    public void full_ftp_scenario_with_valid_data() {

        // ── Step 1: Login ────────────────────────────────────────────────
        webdriver.get(BASE_URL);
        loginPage.click_saveCookies();
        loginPage.login_steps(FTP_SERVER, FTP_USER, FTP_PASS);
        accountPage.should_be_in_user_directory("/home/" + FTP_USER);

        // ── Step 2: Create working directory ────────────────────────────
        accountPage.click_new_directory();
        newDirPage.createDirectory(ORIGINAL_DIR);
        accountPage.should_see_item(ORIGINAL_DIR);

        // ── Step 3: [RENAME] Rename directory ───────────────────────────
        accountPage.select_item(ORIGINAL_DIR);
        accountPage.click_rename();
        renamePage.renameDirectory(ORIGINAL_DIR, RENAMED_DIR);
        // Verify: new name visible, old name gone
        accountPage.should_see_item(RENAMED_DIR);
        accountPage.should_not_see_item(ORIGINAL_DIR);

        // ── Step 4: [COPY] Copy directory ───────────────────────────────
        accountPage.select_item(RENAMED_DIR);
        accountPage.click_copy();
        copyPage.copyDirectory(RENAMED_DIR, "/home/" + FTP_USER, COPIED_DIR);
        // Verify: both original and copy visible
        accountPage.should_see_item(RENAMED_DIR);
        accountPage.should_see_item(COPIED_DIR);

        // ── Step 5: [UPLOAD] Upload a test file ─────────────────────────
        // The file src/test/resources/test_upload.txt is included in the project.
        String absolutePath = new File("src/test/resources/" + TEST_FILE).getAbsolutePath();
        accountPage.click_upload();
        uploadPage.uploadFile(absolutePath, TEST_FILE);
        accountPage.should_see_item(TEST_FILE);

        // ── Step 6: [CHMOD] Change permissions on renamedDir ────────────
        accountPage.select_item(RENAMED_DIR);
        accountPage.click_chmod();
        chmodPage.changePermissions(RENAMED_DIR, "755");
        // After chmod we are back on the browse page
        accountPage.should_see_item(RENAMED_DIR);

        // ── Step 7: Cleanup ─────────────────────────────────────────────
        // Delete RENAMED_DIR
        accountPage.select_item(RENAMED_DIR);
        accountPage.click_delete();
        deletePage.deleteDirectory(RENAMED_DIR);
        accountPage.should_not_see_item(RENAMED_DIR);

        // Delete COPIED_DIR
        accountPage.select_item(COPIED_DIR);
        accountPage.click_delete();
        deletePage.deleteDirectory(COPIED_DIR);
        accountPage.should_not_see_item(COPIED_DIR);

        // Delete uploaded file (same delete flow works for files too)
        accountPage.select_item(TEST_FILE);
        accountPage.click_delete();
        deletePage.deleteFile(TEST_FILE);
        accountPage.should_not_see_item(TEST_FILE);

        // ── Step 8: Logout ───────────────────────────────────────────────
        accountPage.logout();
        logoutPage.should_see_logout_message("You have logged out from the FTP server.");
    }
}
