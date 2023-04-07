import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPasswordPage;
import pages.RegisterPage;
import setup.GenerateRandomData;
import setup.Setup;

import static com.codeborne.selenide.LocalStorageConditions.item;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertFalse;

public class AuthorizationTest extends Setup {
    protected String userToken;
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    RecoveryPasswordPage recoveryPage = new RecoveryPasswordPage();
    User user = new User();
    GenerateRandomData randomData = new GenerateRandomData();
    private String randomEmail = randomData.getRandomEmail();
    private String randomPassword = randomData.getRandomPassword();
    private String randomName = randomData.getRandomName();
    @Before
    public void createUserAndLogin() {
        open(REGISTER_URI);
        registerPage.registration(randomEmail, randomName, randomPassword);
        WebDriverRunner.closeWebDriver();
    }

    @Test
    @Description("Этот тест проверяет вход по кнопке «Войти в аккаунт» на главной")
    public void loginMainStream() {
        open(BASE_URI);
        mainPage.clickEnterAccount();
        loginPage.authorization(randomEmail, randomPassword);
        localStorage().shouldHave(item("accessToken"));
        userToken = localStorage().getItem("accessToken");
        assertFalse("Токена нет", userToken.isEmpty());
    }

    @Test
    @Description("Этот тест проверяет вход через кнопку «Личный кабинет»")
    public void loginProfileStream() {
        open(BASE_URI);
        mainPage.clickProfile();
        loginPage.authorization(randomEmail, randomPassword);
        localStorage().shouldHave(item("accessToken"));
        userToken = localStorage().getItem("accessToken");
        assertFalse("Токена нет", userToken.isEmpty());
    }

    @Test
    @Description("Этот тест проверяет вход через кнопку в форме регистрации")
    public void loginRegisterStream() {
        open(REGISTER_URI);
        registerPage.clickEnterLink();
        loginPage.authorization(randomEmail, randomPassword);
        localStorage().shouldHave(item("accessToken"));
        userToken = localStorage().getItem("accessToken");
        assertFalse("Токена нет", userToken.isEmpty());
    }

    @Test
    @Description("Этот тест проверяет вход через кнопку в форме восстановления пароля")
    public void loginRecoveryStream() {
        open(RECOVERY_URI);
        recoveryPage.clickEnterLink();
        loginPage.authorization(randomEmail, randomPassword);
        localStorage().shouldHave(item("accessToken"));
        userToken = localStorage().getItem("accessToken");
        assertFalse("Токена нет", userToken.isEmpty());
    }


    @After
    public void cleanData() {
        WebDriverRunner.closeWebDriver();
        user.delete(userToken);
    }
}
