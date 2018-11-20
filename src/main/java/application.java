import imp.urlfind;
import imp.urlfindimpform3u8;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

public class application {
    public static void  main(String[] args)throws Exception {


        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        proxy.addHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        System.setProperty("webdriver.ie.driver","C:\\Windows\\System32\\IEDriverServer.exe");


        WebDriver driver = new InternetExplorerDriver(capabilities);
        driver.manage().deleteAllCookies();//删除所有的cookie
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        try {
            urlfindType type=urlfindType.UrlfindTencent;
            urlfind find = new urlfindFactory().geturlfind(type);
            List<String> urls= find.findvideourl(proxy, driver, "https://v.qq.com/x/page/b0770m5x1nv.html");

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
