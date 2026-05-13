package vvss.features.login.valid;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import vvss.steps.serenity.AccountPageSteps;
import vvss.steps.serenity.LoginPageSteps;
import vvss.steps.serenity.LogoutPageSteps;

@RunWith(SerenityRunner.class)
public class LoginTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps public LoginPageSteps loginPage;
    @Steps public AccountPageSteps accountPage;
    @Steps public LogoutPageSteps logoutPage;

    @Issue("#Valid-login-test")
    @Test
    public void valid_login_should_land_on_account_page() {
        // Navigate directly with HTTP Basic Auth credentials embedded in URL
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        loginPage.click_saveCookies();
        loginPage.login_steps("localhost", "vvta1", "vvta1");
        accountPage.should_be_in_user_directory("/home/vvta1");
        accountPage.logout();
        logoutPage.should_see_logout_message("You have logged out from the FTP server.");
    }
}
