package vvss.features.login.valid;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import vvss.steps.serenity.AccountPageSteps;
import vvss.steps.serenity.LoginPageSteps;
import vvss.steps.serenity.LogoutPageSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/validLoginData.csv")
public class LoginParameterizedTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps public LoginPageSteps loginPage;
    @Steps public AccountPageSteps accountPage;
    @Steps public LogoutPageSteps logoutPage;

    // Column names must match the CSV header exactly
    String server, user, pass, logoutMessage;

    @Test
    public void valid_login_with_multiple_accounts() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        loginPage.click_saveCookies();
        loginPage.login_steps(server, user, pass);
        accountPage.should_be_in_user_directory("/home/" + user);
        accountPage.logout();
        logoutPage.should_see_logout_message(logoutMessage);
    }
}
