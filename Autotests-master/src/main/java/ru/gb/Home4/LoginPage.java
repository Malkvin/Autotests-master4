package ru.gb.Home4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriverWait wait;
    @FindBy(css = "form#login input[type='text']")
    WebElement loginField;
    @FindBy(css = "form#login input[type='password']")
    WebElement passwordField;
    @FindBy(css = "form#login button")
    WebElement loginButton;
    @FindBy(css = "div.error-block")
    private WebElement errorBlock;
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }
    public void authorization(String login, String password){
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
    }
    public String getErrorBlockText() {
        return wait.until(ExpectedConditions.visibilityOf(errorBlock))
                .getText().replace("\n", " ");
    }
}
