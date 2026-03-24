package dtm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String username, String password) {
        WebElement usernameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
        );
        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password"))
        );
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("login-button"))
        );

        usernameInput.clear();
        usernameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginButton.click();
    }

    public String getErrorMessage() {
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h3[data-test='error']")
                )
        );
        return errorMsg.getText();
    }

    @Test(description = "Dang nhap thanh cong voi tai khoan hop le")
    public void testLoginSuccess() {
        login("standard_user", "secret_sauce");

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("inventory.html"),
                "Dang nhap thanh cong nhung khong chuyen sang trang inventory!"
        );
    }

    @Test(description = "Dang nhap sai mat khau")
    public void testLoginWrongPassword() {
        login("standard_user", "wrong_password");

        String actualError = getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Username and password do not match"),
                "Thong bao loi khi nhap sai mat khau khong dung!"
        );
    }

    @Test(description = "Bo trong username")
    public void testLoginEmptyUsername() {
        login("", "secret_sauce");

        String actualError = getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Username is required"),
                "Khong hien thi thong bao 'Username is required'!"
        );
    }

    @Test(description = "Bo trong password")
    public void testLoginEmptyPassword() {
        login("standard_user", "");

        String actualError = getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Password is required"),
                "Khong hien thi thong bao 'Password is required'!"
        );
    }

    @Test(description = "Dang nhap voi tai khoan bi khoa")
    public void testLoginLockedUser() {
        login("locked_out_user", "secret_sauce");

        String actualError = getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Sorry, this user has been locked out"),
                "Khong hien thi thong bao tai khoan bi khoa!"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}