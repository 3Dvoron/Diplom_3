import com.codeborne.selenide.WebDriverRunner;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;
import setup.Setup;

import static com.codeborne.selenide.Selenide.open;

public class IngredientsListTest extends Setup {
    MainPage mainPage = new MainPage();

    @Before
    public void openBrowser() {
        open(BASE_URI);
    }

    @Test
    @Description("Этот тест проверяет что выбран раздел «Булки»")
    public void bunListCheck() {
        mainPage.clickSauceList();
        mainPage.clickBunList();
        Assert.assertTrue("Выбран другой раздел", mainPage.checkBunSelected());
    }

    @Test
    @Description("Этот тест проверяет что выбран раздел «Соусы»")
    public void sauceListCheck() {
        mainPage.clickSauceList();
        Assert.assertTrue("Выбран другой раздел", mainPage.checkSauceSelected());
    }

    @Test
    @Description("Этот тест проверяет что выбран раздел «Начинки»")
    public void fillingListCheck() {
        mainPage.clickFillingList();
        Assert.assertTrue("Выбран другой раздел", mainPage.checkFillingSelected());
    }

    @After
    public void closeDriver() {
        WebDriverRunner.closeWebDriver();
    }

}
