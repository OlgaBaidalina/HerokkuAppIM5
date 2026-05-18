import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class FramesTest {

    @Test
    public void checkFrames() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/frames");

        driver.findElement(By.linkText("iFrame")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("textarea")));
        WebElement textarea = driver.findElement(By.tagName("textarea"));
        softAssert.assertEquals(textarea.getText(), "Your content goes here.", "Текст в textarea не совпадает");

        softAssert.assertAll();
        driver.quit();
    }
}