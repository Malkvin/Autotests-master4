package ru.gb.Home4;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditingDummyPage {
    private final WebDriverWait wait;
    @FindBy(xpath = "//*[@id='upsert-item']/div[1]/label/input")
    WebElement firstNameField;
    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement saveButton;
    @FindBy(xpath = "//button[@data-mdc-dialog-action = 'close']")
    WebElement closeButton;

    public EditingDummyPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }


    public void editeDummy(String firstName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).click();
        Thread.sleep(500L);
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).clear();
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOf(saveButton)).click();
        wait.until(ExpectedConditions.visibilityOf(closeButton)).click();

    }
}
