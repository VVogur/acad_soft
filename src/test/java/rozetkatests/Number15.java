package rozetkatests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Number15 {
    String initialUrl = "https://rozetka.com.ua/";
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    String bookPageURL;
    String mainPageURL;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);

    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateToSite() {
        driver.get(initialUrl);
    }

    @Test
    public void checkRozetkaLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".menu-wrapper_state_static >ul > li:nth-child(13) > a")));
        driver.findElement(By.cssSelector(".menu-wrapper_state_static >ul > li:nth-child(13) > a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tile-cats > a > img")));
        WebElement logoR = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header__logo")));
        bookPageURL = driver.getCurrentUrl();
        logoR.click();
        mainPageURL = driver.getCurrentUrl();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".menu-wrapper_state_static >ul > li:nth-child(13) > a")));
        Assert.assertNotEquals(bookPageURL, mainPageURL);
    }
}