package ru.gb.Home2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    @FindBy(xpath = "//table[@aria-label='Dummies list']/tbody/tr")
    List<WebElement> rowsInUserTable;

    @FindBy(id = "create-btn")
    WebElement plusButton;
    @FindBy(xpath = "//button[@data-mdc-dialog-action='close']")
    WebElement closeButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addDummy() {
        plusButton.click();
    }
    public void closeAddDummyWindow(){
        closeButton.click();
    }

    public List<WebElement> getRowsInUserTable() {
        return rowsInUserTable;
    }
    public UserTableRow getRowsByID(String ID){
        return rowsInUserTable.stream()
                .map(UserTableRow::new)
                .filter(row -> row.getID().equals(ID))
                .findFirst().orElseThrow();
    }
}
