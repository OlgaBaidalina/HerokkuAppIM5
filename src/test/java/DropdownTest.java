import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class DropdownTest  {

    @Test
    public void checkDropdown() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");

        SoftAssert softAssert = new SoftAssert();

        Select dropdown = new Select(driver.findElement(By.id("dropdown")));

        softAssert.assertEquals(dropdown.getOptions().size(), 3, "Должно быть 3 элемента");
        dropdown.selectByIndex(1);
        softAssert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 1");
        dropdown.selectByIndex(2);
        softAssert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 2");

        driver.quit();
        softAssert.assertAll();
    }
}