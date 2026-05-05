import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class NotificationMessagesTest {

    @Test
    public void checkNotificationMessages() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        driver.findElement(By.linkText("Click here")).click();
        String notificationText = driver.findElement(By.id("flash")).getText();
        softAssert.assertTrue(notificationText.contains("Action unsuccesful, please try again") ,
                "Текст нотификации не содержит ожидаемой фразы");

        driver.quit();
        softAssert.assertAll();
    }
}