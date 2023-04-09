import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import model.User;
import org.junit.After;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import setup.Browser;
import setup.GenerateRandomData;
import setup.Setup;

import static com.codeborne.selenide.LocalStorageConditions.item;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CreateUserTest extends Setup {
    protected String userToken;
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    User user = new User();
    GenerateRandomData randomData = new GenerateRandomData();
    Browser browser = new Browser();
    private String randomEmail = randomData.getRandomEmail();
    private String randomPassword = randomData.getRandomPassword();
    private String randomName = randomData.getRandomName();

    @Test
    @Description("Этот тест проверяет что можно создать пользователя")
    public void createUser() {
        browser.getBrowser(REGISTER_URI,"yandex");
        registerPage.registration(randomEmail, randomName, randomPassword);
        loginPage.authorization(randomEmail, randomPassword);
        localStorage().shouldHave(item("accessToken"));
        userToken = localStorage().getItem("accessToken");
        assertFalse("Токена нет", userToken.isEmpty());
    }

    @Test
    @Description("Этот тест проверяет что нельзя создать пользователя если пароль меньше 6 символов")
    public void createUserWrongPassword() {
        browser.getBrowser(BASE_URI,"yandex");
        mainPage.clickEnterAccount();
        loginPage.clickRegisterButton();
        registerPage.registration(randomEmail, randomName, "yandex");
        registerPage.errorMessageVisible();
        assertEquals("Неверный url", REGISTER_URI, url());
    }

    @After
    public void closeDriverAndCleanData() {
        webdriver().driver().close();
        if(userToken != null) {
            user.delete(userToken);
        }
    }
}
