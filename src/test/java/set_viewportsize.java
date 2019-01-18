import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class set_viewportsize {
    @Test
    public void test() {
        RemoteWebDriver driver = new ChromeDriver();
        driver.navigate().to("https://yanirta.github.io/hcore/responsive_page.html");
        setViewportSize(driver, 1237, 703);

        driver.quit();
    }

    private void setViewportSize(RemoteWebDriver driver, int vs_width, int vs_height) {
        //Validation
        Long maxVSW = (Long) driver.executeScript("return screen.availWidth - (window.outerWidth - window.innerWidth);");
        Long maxVSH = (Long) driver.executeScript("return screen.availHeight - (window.outerHeight - window.innerHeight);");
        if (maxVSW < vs_width || maxVSH < vs_height)
            throw new RuntimeException(
                    String.format("Desired dimensions are not supported, maximum allowed, w:%s h:%s \n", maxVSW, maxVSH));
        //Dummy resizing for FF that starts maximized on Windows
        driver.manage().window().setSize(new Dimension(0, 0));
        //Calculation
        Long desiredBrowserWidth =
                (Long) driver.executeScript("return arguments[0] + window.outerWidth - window.innerWidth;",
                        vs_width);
        Long desiredBrowserHeight =
                (Long) driver.executeScript("return arguments[0] + window.outerHeight - window.innerHeight;",
                        vs_height);
        //Resizing
        driver.manage().window().setSize(
                new Dimension(
                        desiredBrowserWidth.intValue(),
                        desiredBrowserHeight.intValue()));

        //Ensuring
        Long actualVSWidth = (Long) driver.executeScript("return window.innerWidth;");
        Long actualVSHeight = (Long) driver.executeScript("return window.innerHeight;");

        if (actualVSWidth != vs_width || actualVSHeight != vs_height)
            throw new RuntimeException(
                    String.format(
                            "Couldn't achieve the desired viewport size, got: w:%s,h%s",
                            actualVSWidth,
                            actualVSHeight)
            );
    }
}
