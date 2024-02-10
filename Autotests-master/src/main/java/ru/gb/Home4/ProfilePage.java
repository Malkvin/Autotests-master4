package ru.gb.Home4;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private final WebDriverWait wait;
    @FindBy(xpath = "//div[contains(text(), 'Full name')]/following-sibling::div")
    private WebElement additionalInfoName;
    @FindBy(xpath = "//div[@class='mdc-typography--body2 smui-card__content']")
    private WebElement underAvatarName;
    @FindBy(xpath = "//button[@title = 'More options']")
    private WebElement editingButton;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;
    @FindBy(xpath = "//div[@class='field svelte-vyyzan']//input[@type='date']")
    private WebElement birthDateField;
    @FindBy(xpath = "//button[@data-mdc-dialog-action = 'close']")
    WebElement closeButton;

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }

    public String getAdditionalInfoName() {
        return wait.until(ExpectedConditions.visibilityOf(additionalInfoName)).getText();
    }
    public String getUnderAvatarName() {
        return wait.until(ExpectedConditions.visibilityOf(underAvatarName)).getText();
    }
    public void editProfile(){
        wait.until(ExpectedConditions.visibilityOf(editingButton)).click();

    }
    public void changeBirthDate(String date) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(birthDateField)).click();
        Thread.sleep(2000L);
        wait.until(ExpectedConditions.visibilityOf(birthDateField)).clear();
        wait.until(ExpectedConditions.visibilityOf(birthDateField)).sendKeys(date);
        wait.until(ExpectedConditions.visibilityOf(saveButton)).click();
        wait.until(ExpectedConditions.visibilityOf(closeButton)).click();
    }
}
