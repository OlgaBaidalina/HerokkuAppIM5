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

public class DynamicControlsTest {

    @Test
    public void checkDynamicControls() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        driver.findElement(By.xpath("//*[text()='Remove']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        WebElement inputField = driver.findElement(By.xpath("//input[@type='text']"));
        softAssert.assertFalse(driver.findElement(By.xpath("//input[@type='text']")).isEnabled(),
                "Инпут должен быть disable");
        driver.findElement(By.xpath("//*[text() = 'Enable']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        inputField = driver.findElement(By.xpath("//input[@type='text']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text']")));

        softAssert.assertTrue(inputField.isEnabled(), "Инпут должен быть enabled");

        driver.quit();
        //softAssert.assertAll();
    }
}