package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private SelenideElement emailField = $x(".//label[text()='Email']/..//input");
    private SelenideElement passwordField = $x(".//label[text()='Пароль']/..//input");
    private SelenideElement buttonEnter = $x(".//button[text()='Войти']");
    private SelenideElement registerButton = $x(".//*[text()='Зарегистрироваться']");

    public void setLogin(String email) {
        buttonEnter.shouldBe(visible);
        emailField.should(visible).setValue(email);
    }

    public void setPasswordField(String password) {
        passwordField.shouldBe(visible).setValue(password);
    }

    public void clickButtonEnter() {
        buttonEnter.shouldBe(visible).click();
    }

    public void clickRegisterButton() {
        registerButton.shouldBe(visible).click();
    }

    public void authorization(String email, String password) {
        setLogin(email);
        setPasswordField(password);
        clickButtonEnter();
    }
    public void waitDownloadLoginPage() {
        buttonEnter.shouldBe(visible);
    }
}

