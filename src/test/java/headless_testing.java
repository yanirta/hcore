import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class headless_testing {
    @Test
    public void run_remote_xvfb_test() throws IOException {
        Capabilities caps = DesiredCapabilities.chrome();
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        driver.navigate().to("https://yanirta.github.io/qualitalks/viewport_size.html");
        driver.manage().window().maximize();
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("xvfb_screenshot.png"));
        driver.quit();
    }

    @Test
    public void run_local_headless_chrome_test() throws IOException {
        ChromeOptions opts = new ChromeOptions();
        opts.setHeadless(true);
        opts.addArguments("window-size=5000,5000");
        RemoteWebDriver driver = new ChromeDriver(opts);
        driver.navigate().to("https://yanirta.github.io/qualitalks/viewport_size.html");
        driver.manage().window().maximize();
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("headless_chrome_screenshot.png"));
        driver.quit();
    }

    @Test
    public void run_local_headless_phantomJS_test() throws IOException {
        Settings settings = Settings.builder().screen(new Dimension(5000, 5000)).build();
        JBrowserDriver driver = new JBrowserDriver(settings);
        driver.navigate().to("https://yanirta.github.io/qualitalks/viewport_size.html");
        driver.manage().window().maximize();
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("jbrowser_screenshot.png"));
        driver.quit();
    }
}
