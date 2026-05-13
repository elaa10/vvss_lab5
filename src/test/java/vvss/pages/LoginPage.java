package vvss.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://www.scs.ubbcluj.ro/vvta/net2ftp/index.php")
public class LoginPage extends PageObject {

    @FindBy(name = "ftpserver")
    private WebElementFacade ftpServer;

    @FindBy(name = "username")
    private WebElementFacade username;

    @FindBy(name = "password")
    private WebElementFacade password;

    @FindBy(name = "Login")
    private WebElementFacade loginButton;

    @FindBy(id = "LoginButton1")
    private WebElementFacade saveCookies;

    public void select_server(String serverName) {
        ftpServer.clear();
        ftpServer.type(serverName);
    }

    public void enter_username(String userName) {
        username.type(userName);
    }

    public void enter_password(String pwd) {
        this.password.type(pwd);
    }

    public void click_Login() {
        loginButton.click();
    }

    public void saveCookies() {
        saveCookies.click();
    }
}
