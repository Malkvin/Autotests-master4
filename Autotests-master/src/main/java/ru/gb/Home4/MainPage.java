package ru.gb.Home4;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final WebDriverWait wait;
    @FindBy(xpath = "//table[@aria-label='Dummies list']/tbody/tr")
    List<WebElement> rowsInUserTable;

    @FindBy(id = "create-btn")
    WebElement plusButton;
    @FindBy(xpath = "//button[@data-mdc-dialog-action='close']")
    WebElement closeButton;
    @FindBy(css = "nav li.mdc-menu-surface--anchor a")
    private WebElement userNameLinkInNavBar;
    @FindBy(xpath = "//*[@id='upsert-item']/div[1]/label/input")
    private WebElement firstNameField;
    @FindBy(xpath = "//*[@id='upsert-item']/div[5]/label/input")
    private WebElement loginField;
    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement saveButton;
    @FindBy(xpath = "//div[@id='contentId-content']")
    private WebElement modalWindow;
    @FindBy(xpath = "//li[@role='menuitem']//span[text()='Profile']")
    private WebElement profileButton;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void addDummy(String firstName, String dummiesLogin) {
        wait.until(ExpectedConditions.visibilityOf(plusButton)).click();
        wait.until(ExpectedConditions.visibilityOf(modalWindow)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOf(loginField)).sendKeys(dummiesLogin);
        wait.until(ExpectedConditions.visibilityOf(saveButton)).click();
        wait.until(ExpectedConditions.visibilityOf(closeButton)).click();
    }
    public WebElement waitAndGetDummyNameByText(String title) {
        String xpath = String.format("//table[@aria-label='Dummies list']/tbody//td[text()='%s']", title);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
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
    public String getUserNameLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(userNameLinkInNavBar))
                .getText().replace("\n", " ");
    }
    public void clickProfileButton(){
        wait.until(ExpectedConditions.visibilityOf(userNameLinkInNavBar)).click();
        wait.until(ExpectedConditions.visibilityOf(profileButton)).click();
    }
    public UserTableRow getRowsByName(String name) {
        return rowsInUserTable
                .stream()
                .map(UserTableRow::new)
                .filter(row -> row.getName().equals(name))
                .findFirst().orElseThrow();
    }

}
