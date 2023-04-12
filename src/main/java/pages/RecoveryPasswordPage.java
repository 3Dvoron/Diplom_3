package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RecoveryPasswordPage {
    private SelenideElement enterLink = $x(".//a[text()='Войти']");

    public void clickEnterLink() {
        enterLink.shouldBe(visible).click();
    }
}
