package ru.gb.Home2;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class UserTableRow {
    private final WebElement root;

    public UserTableRow(WebElement root) {
        this.root = root;
    }
    public String getID() {
        return root.findElement(By.xpath("./td[1]")).getText();
    }
    public String getName() {
        return root.findElement(By.xpath("./td[2]")).getText();
    }
    public String getStatus() {
        return root.findElement(By.xpath("./td[4]")).getText();
    }
    public void clickEditIcon(){
        root.findElement(By.xpath("./td/button[text()='edit']")).click();
        ((Wait<WebElement>) new FluentWait<>(root)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class))
                .until(root -> root.findElements(By.xpath("./td/button")));
    }
    public void clickCredentials(){
        root.findElement(By.xpath("./td/button[text()='key']")).click();
        ((Wait<WebElement>) new FluentWait<>(root)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class))
                .until(root -> root.findElements(By.xpath("./td/button")));
    }
    public void clickTrashIcon(){
        root.findElement(By.xpath("./td/button[text()='delete']")).click();
        ((Wait<WebElement>) new FluentWait<>(root)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class))
                .until(root -> root.findElement(By.xpath("./td/button[text()='restore_from_trash']")));
    }

    public void clickRestoreFromTrashIcon(){
        root.findElement(By.xpath("./td/button[text()='restore_from_trash']")).click();
        ((Wait<WebElement>) new FluentWait<>(root)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class))
                .until(root -> root.findElement(By.xpath("./td/button[text()='delete']")));
    }


}
