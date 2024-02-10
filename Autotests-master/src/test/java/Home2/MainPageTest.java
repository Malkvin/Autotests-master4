package Home2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.gb.Home2.EditingDummyPage;
import ru.gb.Home2.LoginPage;
import ru.gb.Home2.MainPage;
import ru.gb.Home2.UserTableRow;

import java.time.Duration;
import java.util.List;
/*
Более сложный тест на редактирование dummy (здесь будет важно добавить методы для поиска ID dummy по имени,
чтобы сохранить его в переменную и потом в завершении теста использовать для проверки). Сценарий теста:
- Открываем модальное окно для редактирования dummy
- Редактируем имя
- Сохраняем
- Проверяем в таблице Dummies по ID, что имя изменилось
 */

public class MainPageTest extends AbstractTest {

    @Test
    void EditDummyAndCheckCredentialsTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(login, password);
        List<WebElement> webElementList = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, webElementList.size());

        MainPage mainPage = new MainPage(driver);
        List<WebElement> rowsWebElements = mainPage.getRowsInUserTable();
        List<UserTableRow> userTableRows = rowsWebElements
                .stream().map(UserTableRow::new).toList();
        UserTableRow searchRow = mainPage.getRowsByID("32850");
        searchRow.clickEditIcon();
        EditingDummyPage editingDummyPage = new EditingDummyPage(driver);
        String newName = "NewName1";
        editingDummyPage.editeDummy(driver, newName);
        Thread.sleep(1000L);
        Assertions.assertEquals("NewName1", searchRow.getName());
/*
3) Тест на проверку модального окна Credentials
- Открыть модальное окно на созданном Dummy
- Проверить заголовок и статический контент
 */
        UserTableRow searchRowForKey = mainPage.getRowsByID("32851");
        searchRowForKey.clickCredentials();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated((By.id("simple-title"))));
        Assertions.assertEquals("Dummy credentials", title.getText());
        WebElement content = wait.until(ExpectedConditions.presenceOfElementLocated((By.id("simple-content"))));
        Assertions.assertEquals("Login: DummiesLogin1707037058898\n" +
                "PW: bf0b81419e", content.getText());

    }
}
