package vvss.features.login.invalid;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import vvss.steps.serenity.LoginPageSteps;
import vvss.steps.serenity.LogoutPageSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/invalidLoginData.csv")
public class LoginInvalidParameterizedTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps public LoginPageSteps loginPage;
    @Steps public LogoutPageSteps logoutPage;

    String server, user, pass, errorMessage;

    @Test
    public void invalid_login_should_show_error() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        loginPage.click_saveCookies();
        loginPage.login_steps(server, user, pass);
        logoutPage.should_see_logout_message(errorMessage);
    }
}
