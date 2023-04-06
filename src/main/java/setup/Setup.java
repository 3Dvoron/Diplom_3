package setup;

import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Setup {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    public static final String LOGIN_URI = BASE_URI + "login";
    public static final String REGISTER_URI = BASE_URI + "register";
    public static final String RECOVERY_URI = BASE_URI + "forgot-password";
    public static final String PROFILE_URI = BASE_URI + "account/profile";
    public static final String DELETE_USER_URI = BASE_URI + "api/auth/user";
    Faker faker = new Faker();

    public String getRandomName() {
        return faker.name().firstName();
    }

    public String getRandomEmail() {
        return faker.bothify("??????@gmail.com");
    }

    public String getRandomPassword() {
        return faker.regexify("[a-z1-9]{10}");
    }

    public void getYandexBrowser(String link) {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "driver/yandexdriver.exe");
        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
        driver.get(link);
    }
}
