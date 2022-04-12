import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class App {

    WebDriver driver;
    String BASE_URL = "https://www.instagram.com/";

    By userNameLocator = new By.ByCssSelector("input[name ='username']");
    By passwordLocator = new By.ByCssSelector("input[name ='password']");
    By loginButtonLocator = new By.ByCssSelector("button[type ='submit']");
    By instagramLogoLocator = By.className("MWDvN");
    By profileNameLocator = By.className("XBGH5");
    By postLocator = By.className("_9AhH0");
    
    By likeButtonLocator = new By.ByCssSelector("span.fr66n");
    By postCountLocator = By.className("g47SY");
    By htmlTag = By.tagName("html");

    public App() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        //driver.manage().window().maximize();
    }

    public void waitFor(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void loginWith(String userName, String password) {
        waitFor(userNameLocator);
        driver.findElement(userNameLocator).sendKeys(userName);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();

    }

    public void navigateToProfile(String profileName) {
        waitFor(instagramLogoLocator);
        driver.navigate().to(BASE_URL.concat(profileName));
    }

    public void clickFirstPost(){
        waitFor(profileNameLocator);
        driver.findElements(postLocator).get(0).click();
    }

    private int getPostCount(){
        String count = driver.findElement(postCountLocator).getText();
        return Integer.parseInt(count);
    }

    public void likeAllPost() {
        int count = getPostCount();
        while(count > 0){
            waitFor(likeButtonLocator);
            driver.findElement(likeButtonLocator).click();
            driver.findElement(htmlTag).sendKeys(Keys.ARROW_RIGHT);
            count--;
        }

    }
}
