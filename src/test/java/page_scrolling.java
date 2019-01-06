import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class page_scrolling {
    @Test
    public void test() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("https://yanirta.github.io/hcore/scrollable_elements.html");
        WebElement element = driver.findElement(By.cssSelector(".right .box"));
        scrollThroughPage(driver);
    }

    private void scrollThroughPage(ChromeDriver driver) throws InterruptedException {
        Long scrollHeight = (Long) driver.executeScript("return document.body.scrollHeight;");
        Long clientHeight = (Long) driver.executeScript("return document.body.parentElement.clientHeight;");
        Long scrollPosition = 0L;
        while (scrollPosition < scrollHeight - clientHeight) {
            scrollPosition += clientHeight;
            driver.executeScript("document.body.parentElement.scrollTo(0, arguments[0]);", scrollPosition);
            Thread.sleep(1000); //Replace with your own logic
        }
    }
}
