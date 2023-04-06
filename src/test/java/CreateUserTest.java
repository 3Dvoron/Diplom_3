import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import model.User;
import org.junit.After;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import setup.Setup;

import static com.codeborne.selenide.LocalStorageConditions.item;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CreateUserTest extends Setup {
    protected String userToken;
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    User user = new User();
    private String randomEmail = getRandomEmail();
    private String randomName = getRandomName();
    private String randomPassword = getRandomPassword();

    @Test
    @Description("Этот тест проверяет что можно создать пользователя")
    public void createUser() {
        open(REGISTER_URI);
        registerPage.registration(randomEmail, randomName, randomPassword);
        loginPage.authorization(randomEmail, randomPassword);
        localStorage().shouldHave(item("accessToken"));
        userToken = localStorage().getItem("accessToken");
        assertFalse("Токена нет", userToken.isEmpty());
        user.delete(userToken);
    }

    @Test
    @Description("Этот тест проверяет что нельзя создать пользователя если пароль меньше 6 символов")
    public void createUserWrongPassword() {
        open(BASE_URI);
        mainPage.clickEnterAccount();
        loginPage.clickRegisterButton();
        registerPage.registration(randomEmail, randomName, "qwert");
        registerPage.errorMessageVisible();
        assertEquals("Неверный url", REGISTER_URI, url());
    }

    @After
    public void closeDriver() {
        WebDriverRunner.closeWebDriver();
    }

}
