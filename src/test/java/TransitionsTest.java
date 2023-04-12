import com.codeborne.selenide.WebDriverRunner;
import jdk.jfr.Description;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegisterPage;
import setup.Browser;
import setup.GenerateRandomData;
import setup.Setup;

import static com.codeborne.selenide.LocalStorageConditions.item;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

public class TransitionsTest extends Setup {
    protected String userToken;
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    ProfilePage profilePage = new ProfilePage();
    User user = new User();
    Browser browser = new Browser();
    GenerateRandomData randomData = new GenerateRandomData();
    private String randomEmail = randomData.getRandomEmail();
    private String randomPassword = randomData.getRandomPassword();
    private String randomName = randomData.getRandomName();
    @Before
    public void createUserAndLogin() {
        browser.getBrowser(REGISTER_URI,"yandex");
        registerPage.registration(randomEmail, randomName, randomPassword);
        loginPage.authorization(randomEmail, randomPassword);
        localStorage().shouldHave(item("accessToken"));
        userToken = localStorage().getItem("accessToken");
        WebDriverRunner.closeWebDriver();
    }

    @Test
    @Description("Этот тест проверяет переход по клику на «Личный кабинет»")
    public void transitionsProfile() {
        browser.getBrowser(BASE_URI,"yandex");
        mainPage.clickEnterAccount();
        loginPage.authorization(randomEmail, randomPassword);
        mainPage.clickProfile();
        profilePage.waitDownloadProfilePage();
        assertEquals("Неверный url", PROFILE_URI, url());
    }

    @Test
    @Description("Этот тест проверяет переход из личного кабинета на главную если нажать на логотип Stellar Burgers")
    public void transitionsLogo() {
        browser.getBrowser(BASE_URI,"yandex");
        mainPage.clickEnterAccount();
        loginPage.authorization(randomEmail, randomPassword);
        mainPage.clickProfile();
        profilePage.clickLogo();
        mainPage.waitDownloadMainPage();
        assertEquals("Неверный url", BASE_URI, url());
    }

    @Test
    @Description("Этот тест проверяет переход из личного кабинета в конструктор")
    public void transitionsConstructorButton() {
        browser.getBrowser(BASE_URI,"yandex");
        mainPage.clickEnterAccount();
        loginPage.authorization(randomEmail, randomPassword);
        mainPage.clickProfile();
        profilePage.waitDownloadProfilePage();
        profilePage.clickConstructor();
        mainPage.waitDownloadMainPage();
        assertEquals("Неверный url", BASE_URI, url());
    }

    @Test
    @Description("Этот тест проверяет выход по кнопке «Выйти» в личном кабинете")
    public void logOut() {
        browser.getBrowser(LOGIN_URI,"yandex");
        loginPage.authorization(randomEmail, randomPassword);
        mainPage.clickProfile();
        profilePage.clickExitButton();
        loginPage.waitDownloadLoginPage();
        assertEquals("Неверный url", LOGIN_URI, url());
    }

    @After
    public void cleanData() {
        webdriver().driver().close();
        user.delete(userToken);
    }
}
