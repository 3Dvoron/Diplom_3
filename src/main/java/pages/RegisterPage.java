package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    private SelenideElement nameField = $x(".//label[text()='Имя']/..//input");
    private SelenideElement emailField = $x(".//label[text()='Email']/..//input");
    private SelenideElement passwordField = $x(".//*[text()='Пароль']/..//input");
    private SelenideElement registerButton = $x(".//button[text()='Зарегистрироваться']");
    private SelenideElement errorMessage = $x(".//p[text()='Некорректный пароль']");
    private SelenideElement enterLink = $x(".//a[text()='Войти']");

    public void setNameField(String name) {
        nameField.shouldBe(visible).setValue(name);
    }

    public void setEmailField(String email) {
        emailField.shouldBe(visible).setValue(email);
    }

    public void setPasswordField(String password) {
        passwordField.shouldBe(visible).setValue(password);
    }

    public void clickRegisterButton() {
        registerButton.shouldBe(visible).click();
    }

    public void clickEnterLink() {
        enterLink.shouldBe(visible).click();
    }

    public void errorMessageVisible() {
        errorMessage.shouldBe(visible);
    }

    public void registration(String email, String name, String password) {
        setEmailField(email);
        setNameField(name);
        setPasswordField(password);
        clickRegisterButton();
    }
}
