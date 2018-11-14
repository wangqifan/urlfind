
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

public class application {
    public static void  main(String[] args)throws Exception {

        System.out.println("--------start");

        // start the proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(capabilities);

        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // create a new HAR with the label "yahoo.com"
        proxy.newHar("https://v.qq.com/");

        // open yahoo.com
        driver.get("https://v.qq.com/x/page/n033865szz2.htmlg");//页面的URL
       System.out.println(driver.getTitle());
        try {
            Thread.sleep(50000);     //让线程等待50秒钟,这是广告播放时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // get the HAR data
        Har har = proxy.getHar();
        HarLog log= har.getLog();
        List<HarEntry> entries=log.getEntries();
        for (HarEntry entrie:entries
             ) {
            HarRequest request= entrie.getRequest();
            String url=request.getUrl();
            if(url.contains("1.mp4?")&&url.contains("https"))//腾讯广告用http,视频使用http
          {
                System.out.println(url);
            }
        }
        driver.close();
        System.out.println("---------------end");

    }
}
