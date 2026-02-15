package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");
    
    private String url = "https://www.saucedemo.com/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openUrl(url);
    }

    public void enterUsername(String username) {
        enterText(usernameInput, username);
    }

    public void enterPassword(String password) {
        enterText(passwordInput, password);
    }

    public void clickLogin() {
        clickElement(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isErrorDisplayed() {
        return isElementPresent(errorMessage);
    }

    public String getErrorMessage() {
        try {
            return findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
}