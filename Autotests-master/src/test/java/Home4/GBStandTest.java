package Home4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.gb.Home4.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class GBStandTest extends AbstractTest {
    private static final String LOGIN = "GB202212c8b3e14";
    private static final String PASSWORD = "123d5e4487";
    private static final String ADMINNAME = "GB202212 c8b3e14";
    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private EditingDummyPage editingDummyPage;

    @Test
    @DisplayName("Тест на успешную авторизацию")
    void authorizationTest() throws InterruptedException {
        checkLogin();
    }

    @Test
    void textErrorLoginTest() {
        String emptyLogin = "";
        String emptyPass = "";
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.authorization(emptyLogin, emptyPass);
        Assertions.assertEquals("401 Invalid credentials.", loginPage.getErrorBlockText());
    }

    private void checkLogin() throws InterruptedException {
        loginPage = new LoginPage(driver, wait);
        // Логин в систему с помощью метода из класса Page Object
        loginPage.authorization(LOGIN, PASSWORD);
        // Инициализация объекта класса MainPage
        mainPage = new MainPage(driver, wait);
        // Проверка, что логин прошёл успешно
        assertTrue(mainPage.getUserNameLabelText().contains(LOGIN));
        Thread.sleep(1000L);
    }

    @Test
    @DisplayName("Проверка успешного добавления dummy")
    void addDummyTest() throws InterruptedException {
        checkLogin();
        String firstName = "DummiesName" + System.currentTimeMillis();
        String dummiesLogin = "DummiesLogin" + System.currentTimeMillis();
        mainPage.addDummy(firstName, dummiesLogin);
        // Проверка, что dummy создан и находится в таблице
        assertTrue(mainPage.waitAndGetDummyNameByText(firstName).isDisplayed());
    }

    @Test
    @DisplayName("Проверка редактирования dummy")
    void EditDummyTest() throws InterruptedException {
        checkLogin();
        List<WebElement> webElements = mainPage.getRowsInUserTable();
        List<UserTableRow> userTableRows = webElements
                .stream().map(UserTableRow::new).toList();
        UserTableRow searchRow = mainPage.getRowsByID("33107");
        searchRow.clickEditIcon();
        editingDummyPage = new EditingDummyPage(driver, wait);
        String newName = "NewName" + System.currentTimeMillis();
        editingDummyPage.editeDummy(newName);
        Thread.sleep(1000L);
        assertTrue(mainPage.waitAndGetDummyNameByText(newName).isDisplayed());


    }

    @Test
    @DisplayName("Проверка окна Credentials")
    void CheckCredentialsTest() throws InterruptedException {
        checkLogin();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@title='Last page']")));
        List<WebElement> webElements = mainPage.getRowsInUserTable();
        List<UserTableRow> userTableRows = webElements
                .stream().map(UserTableRow::new).toList();
        UserTableRow searchRowForKey = mainPage.getRowsByID("33108");
        searchRowForKey.clickCredentials();
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("simple-title")));
        Assertions.assertEquals("Dummy credentials", title.getText());
        WebElement content = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("simple-content")));
        Assertions.assertEquals("Login: DummiesLogin1707376453286\n" +
                "PW: ad37fef9a2", content.getText());

    }

    @Test
    @DisplayName("Проверка страницы Profile")
    public void profilePageTest() throws InterruptedException {
        checkLogin();
        mainPage.clickProfileButton();
        profilePage = new ProfilePage(driver, wait);
        Assertions.assertEquals(ADMINNAME, profilePage.getAdditionalInfoName());
        Assertions.assertEquals(ADMINNAME, profilePage.getUnderAvatarName());
    }

    @Test
    @DisplayName("Проверка смены статуса")
    public void changeStatusTest() throws InterruptedException {
        checkLogin();
        List<WebElement> webElements = mainPage.getRowsInUserTable();
        List<UserTableRow> userTableRows = webElements
                .stream().map(UserTableRow::new).toList();
        UserTableRow searchRow = mainPage.getRowsByName("Hanna");
        String status = searchRow.getStatus();
        Assertions.assertEquals("active", status);
        searchRow.clickTrashIcon();
        String trashStatus = searchRow.getStatus();
        Assertions.assertEquals("inactive", trashStatus);
        searchRow.clickRestoreFromTrashIcon();
        String restoreStatus = searchRow.getStatus();
        Assertions.assertEquals("active", restoreStatus);
    }
    /*
    Написать тест на Profile Page
- Логин в приложение и навигация на Profile Page
- Открыть модальное окно Editing
- Изменить Birthdate значение
- Нажать на кнопку Save и закрыть модальное окно
- Проверить, что изменения применились в поле Date of Birth в секции Additional Info
     */

    @Test
    public void changeBirthdateInProfileTest() throws InterruptedException, ParseException {
        checkLogin();
        mainPage.clickProfileButton();
        Thread.sleep(500L);
        profilePage = new ProfilePage(driver, wait);
        profilePage.editProfile();
        Calendar calendar = new GregorianCalendar(2017, 0 , 24);
        Date date = calendar.getTime();
        String stringDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
        profilePage.changeBirthDate(stringDate);
        Thread.sleep(1000L);
        String searchDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='profile-info svelte-vyyzan']/div[2]/div[2]"))).getText().replace(".", "-");
        Assertions.assertEquals(stringDate, searchDate);

    }
}
