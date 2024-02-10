package ru.gb.Seminar4;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.gb.Seminar4.elements.GroupTableRow;
import ru.gb.Seminar4.elements.StudentTableRow;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

//    private final WebDriverWait wait;

//    @FindBy(css = "nav li.mdc-menu-surface--anchor a")
   private SelenideElement usernameLinkInNavBar = $("nav li.mdc-menu-surface--anchor a");

    // Create Group Modal Window
//    @FindBy(id = "create-btn")
    private SelenideElement createGroupButton = $(By.id("create-btn"));
//    @FindBy(xpath = "//form//span[contains(text(), 'Group name')]/following-sibling::input")
//    private WebElement groupNameField;
   private SelenideElement groupNameField = $x("//form//span[contains(text(), 'Group name')]/following-sibling::input");
//    @FindBy(css = "form div.submit button")
//    private WebElement submitButtonOnModalWindow;
    private SelenideElement submitButtonOnModalWindow = $("form div.submit button");
//    @FindBy(xpath = "//span[text()='Creating Study Group']" +
//            "//ancestor::div[contains(@class, 'form-modal-header')]//button")
//    private WebElement closeCreateGroupIcon;
    private SelenideElement closeCreateGroupIcon = $x("//span[text()='Creating Study Group']" +
            "//ancestor::div[contains(@class, 'form-modal-header')]//button");

    // Create Students Modal Window
//    @FindBy(css = "div#generateStudentsForm-content input")
//    private WebElement createStudentsFormInput;
    private SelenideElement createStudentsFormInput = $("div#generateStudentsForm-content input");
//    @FindBy(css = "div#generateStudentsForm-content div.submit button")
//    private WebElement saveCreateStudentsForm;
    private SelenideElement saveCreateStudentsForm = $("div#generateStudentsForm-content div.submit button");
//    @FindBy(xpath = "//h2[@id='generateStudentsForm-title']/../button")
//    private WebElement closeCreateStudentsFormIcon;
    private SelenideElement closeCreateStudentsFormIcon = $x("//h2[@id='generateStudentsForm-title']/../button");

//    @FindBy(xpath = "//table[@aria-label='Tutors list']/tbody/tr")
//    private List<WebElement> rowsInGroupTable;
    private ElementsCollection rowsInGroupTable = $$x("//table[@aria-label='Tutors list']/tbody/tr");
//    @FindBy(xpath = "//table[@aria-label='User list']/tbody/tr")
//    private List<WebElement> rowsInStudentTable;
    private ElementsCollection rowsInStudentTable = $$x("//table[@aria-label='User list']/tbody/tr");

//    @FindBy(xpath = "//li[@role='menuitem']//span[text()='Profile']")
//    private WebElement profileButton;
    private SelenideElement profileButton = $x("//li[@role='menuitem']//span[text()='Profile']");

//    public MainPage(WebDriver driver, WebDriverWait wait) {
//        this.wait = wait;
//        PageFactory.initElements(driver, this);
//    }

    public SelenideElement waitAndGetGroupTitleByText(String title) {
        String xpath = String.format("//table[@aria-label='Tutors list']/tbody//td[text()='%s']", title);
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return $x(xpath).should(Condition.visible);
    }
public void clickProfileButton(){
        usernameLinkInNavBar.should(Condition.visible).click();
//        wait.until(ExpectedConditions.visibilityOf(usernameLinkInNavBar)).click();
    profileButton.should(Condition.visible).click();
//        wait.until(ExpectedConditions.visibilityOf(profileButton)).click();
}
    public void createGroup(String groupName) {
//        wait.until(ExpectedConditions.visibilityOf(createGroupButton)).click();
        createGroupButton.should(Condition.visible).click();
//        wait.until(ExpectedConditions.visibilityOf(groupNameField)).sendKeys(groupName);
        groupNameField.should(Condition.visible).setValue(groupName);
        submitButtonOnModalWindow.should(Condition.visible).click();
        waitAndGetGroupTitleByText(groupName);
    }

    public void closeCreateGroupModalWindow() {
        closeCreateGroupIcon.should(Condition.visible).click();
//        wait.until(ExpectedConditions.invisibilityOf(closeCreateGroupIcon));
    }

    public void typeAmountOfStudentsInCreateStudentsForm(int amount) {
        createStudentsFormInput.should(Condition.visible).setValue(String.valueOf(amount));
//        wait.until(ExpectedConditions.visibilityOf(createStudentsFormInput))
//                .sendKeys(String.valueOf(amount));
    }

    public void clickSaveButtonOnCreateStudentsForm() {
        saveCreateStudentsForm.should(Condition.visible).click();
//        wait.until(ExpectedConditions.visibilityOf(saveCreateStudentsForm)).click();
    }

    public void closeCreateStudentsModalWindow() {
        closeCreateStudentsFormIcon.should(Condition.visible).click();
//        wait.until(ExpectedConditions.invisibilityOf(closeCreateStudentsFormIcon));
    }

    public String getUsernameLabelText() {
        return usernameLinkInNavBar.should(Condition.visible).getText().replace("\n", " ");
//        return wait.until(ExpectedConditions.visibilityOf(usernameLinkInNavBar))
//                .getText().replace("\n", " ");
    }

    // Group Table Section
    public void clickTrashIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickRestoreFromTrashIcon();
    }

    public void clickAddStudentsIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickAddStudentsIcon();
    }

    public void clickZoomInIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickZoomInIcon();
    }

    public String getStatusOfGroupWithTitle(String title) {
        return getGroupRowByTitle(title).getStatus();
    }

    public void waitStudentsCount(String groupTestName, int studentsCount) {
         getGroupRowByTitle(groupTestName).waitStudentsCount(studentsCount);
    }

    private GroupTableRow getGroupRowByTitle(String title) {
        return rowsInGroupTable
                .asDynamicIterable()
                .stream()
                .map(GroupTableRow::new)
                .filter(row -> row.getTitle().equals(title))
                .findFirst().orElseThrow();
    }

    // Students Table Section

    public void clickTrashIconOnStudentWithName(String name) {
        getStudentRowByName(name).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnStudentWithName(String name) {
        getStudentRowByName(name).clickRestoreFromTrashIcon();
    }

    public String getStatusOfStudentWithName(String name) {
        return getStudentRowByName(name).getStatus();
    }

    public String getStudentNameByIndex(int index) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable()
                .stream()
                .map(StudentTableRow::new)
                .toList().get(index).getName();
    }

    private StudentTableRow getStudentRowByName(String name) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable()
                .stream()
                .map(StudentTableRow::new)
                .filter(row -> row.getName().equals(name))
                .findFirst().orElseThrow();
    }
}
