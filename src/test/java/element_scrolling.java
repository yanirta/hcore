import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class element_scrolling {
    @Test
    public void test() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("https://yanirta.github.io/hcore/scrollable_elements.html");
        WebElement element = driver.findElement(By.cssSelector(".right .box"));
        scrollThroughElement(driver, element);
    }

    private void scrollThroughElement(ChromeDriver driver, WebElement element) throws InterruptedException {
        if (!element.isDisplayed()) return;
        driver.executeScript("arguments[0].scrollIntoView(true);", element);
        Long scrollHeight = (Long) driver.executeScript("return arguments[0].scrollHeight", element);
        Long clientHeight = (Long) driver.executeScript("return arguments[0].clientHeight", element);
        Long scrollPosition = 0L;

        while (scrollPosition < scrollHeight - clientHeight) {
            scrollPosition += clientHeight;
            driver.executeScript("arguments[0].scrollTo(0, arguments[1]);", element, scrollPosition);
            Thread.sleep(1000); //Replace with your own logic
        }
    }
}
