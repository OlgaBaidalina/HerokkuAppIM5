import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class TyposTest {
    @Test
    public void checkTyposTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        SoftAssert softAssert = new SoftAssert();

        driver.get("https://the-internet.herokuapp.com/typos");

        String expectedText = "Sometimes you'll see a typo, other times you won't.";

        for (int i = 1; i <= 10; i++) {
            driver.navigate().refresh();
            String actualText = driver.findElement(By.xpath("(//p)[2]")).getText();
            softAssert.assertEquals(actualText, expectedText);
        }

        driver.quit();
        softAssert.assertAll();
    }
}