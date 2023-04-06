package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private SelenideElement linkProfile = $x(".//a[text()='Профиль']");
    private SelenideElement exitButton = $x(".//button[text()='Выход']");
    private SelenideElement constructor = $x(".//p[text()='Конструктор']");
    private SelenideElement logo = $(".AppHeader_header__logo__2D0X2");

    public void clickExitButton() {
        exitButton.shouldBe(visible).click();

    }

    public void clickConstructor() {
        constructor.shouldBe(visible).click();
    }

    public void clickLogo() {
        logo.shouldBe(visible).click();
    }

    public void waitDownloadProfilePage() {
        exitButton.shouldBe(visible);
    }

}
