package Home2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.gb.Home2.LoginPage;


public class LoginPageTest extends AbstractTest {
    /*
    1) Логин в систему не вводя ни логин, ни пароль. На странице появляется ошибка,
    нужно проверить её текст
     */
    @Test
    void textErrorLoginTest(){
        String emptyLogin ="";
        String emptyPass ="";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(emptyLogin, emptyPass);
        WebElement errorText = driver.findElement(By.xpath("//*[@class = 'error-block svelte-uwkxn9']"));
        Assertions.assertEquals("401\nInvalid credentials.", errorText.getText());
    }
}
