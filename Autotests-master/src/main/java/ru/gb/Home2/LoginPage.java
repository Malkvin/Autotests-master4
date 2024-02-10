package ru.gb.Home2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(css = "form#login input[type='text']")
    WebElement loginField;
    @FindBy(css = "form#login input[type='password']")
    WebElement passwordField;
    @FindBy(css = "form#login button")
    WebElement loginButton;
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void authorization(String login, String password){
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
