import imp.urlfind;
import imp.urlfindimpform3u8;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class application {
    public static void  main(String[] args)throws Exception {


        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        System.setProperty("webdriver.ie.driver","C:\\Windows\\System32\\IEDriverServer.exe");
      // WebDriver driver = new ChromeDriver(capabilities);
        WebDriver driver = new InternetExplorerDriver(capabilities);
        driver.manage().deleteAllCookies();//删除所有的cookie
       // proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        try {
            urlfindType type=urlfindType.urlfindregex;
            urlfind find = new urlfindFactory().geturlfind(type);
            List<String> urls= find.findvideourl(proxy, driver, "http://www.21ic.com/evm/video/201612/695003.htm");

            for(String url:urls)
            {
                System.out.println(url);
            }
        }
        finally {
            proxy.stop();
            driver.close();
        }
    }
}
