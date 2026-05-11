import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class InputsTest {
    @Test
    public void checkAddRemoveElement() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/inputs");

        driver.findElement(By.tagName("input")).sendKeys("abc");
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "");

        driver.findElement(By.tagName("input")).clear();
        driver.findElement(By.tagName("input")).sendKeys("7456");
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "7456");

        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "7457");

        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "7456");

        driver.quit();
        softAssert.assertAll();
    }
}