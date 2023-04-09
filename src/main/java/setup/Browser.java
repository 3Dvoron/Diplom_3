package setup;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;

public class Browser {
    public void getBrowser(String link, String typeBrowser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        if(typeBrowser.toLowerCase(Locale.ROOT).equals("yandex")){
            System.setProperty("webdriver.chrome.driver", "driver/yandexdriver.exe");
        }
        else {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        }
        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
        driver.get(link);
    }
}
