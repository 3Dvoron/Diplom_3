package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private SelenideElement profile = $x(".//p[text()='Личный Кабинет']");
    private SelenideElement bunList = $x(".//span[text()='Булки']/..");
    ;
    private SelenideElement sauceList = $x(".//span[text()='Соусы']/..");
    private SelenideElement fillingList = $x(".//span[text()='Начинки']/..");
    private SelenideElement enterAccountMain = $x("//button[text()='Войти в аккаунт']");
    private SelenideElement makeOrderButton = $x(".//button[text()='Оформить заказ']");

    public void clickEnterAccount() {
        enterAccountMain.shouldBe(visible).click();
    }

    public void clickProfile() {
        profile.shouldBe(visible).click();
    }

    public void clickBunList() {
        bunList.shouldBe(visible).click();
    }

    public void clickSauceList() {
        sauceList.shouldBe(visible).click();
    }

    public void clickFillingList() {
        fillingList.shouldBe(visible).click();
    }


    public boolean checkBunSelected() {
        bunList.shouldBe(cssClass("tab_tab_type_current__2BEPc"));
        return true;
    }

    public boolean checkSauceSelected() {
        sauceList.shouldBe(cssClass("tab_tab_type_current__2BEPc"));
        return true;
    }

    public boolean checkFillingSelected() {
        fillingList.shouldBe(cssClass("tab_tab_type_current__2BEPc"));
        return true;
    }
    public void waitDownloadMainPage() {
        bunList.shouldBe(visible);
    }
}
