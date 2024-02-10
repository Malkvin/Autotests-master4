package ru.gb.Home2;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class EditingDummyPage {
    @FindBy(xpath = "//*[@id='upsert-item']/div[1]/label/input")
    WebElement firstNameField;
    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement saveButton;
    @FindBy(xpath = "//button[@data-mdc-dialog-action = 'close']")
    WebElement closeButton;

    public EditingDummyPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void editeDummy(WebDriver driver, String firstName) throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofSeconds(5L))
                .ignoring(NoSuchElementException.class);

        firstNameField = wait.until(driver1 -> driver1.findElement(By.xpath("//*[@id='upsert-item']/div[1]/label/input")));
        firstNameField.click();
        Thread.sleep(500L);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        saveButton.click();
        closeButton.click();

    }
}
