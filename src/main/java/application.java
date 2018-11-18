import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class application {
    public static void  main(String[] args)throws Exception {


        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        System.setProperty("webdriver.ie.driver","E:\\Software\\IEDriverServer_Win32_3.3.0\\IEDriverServer.exe");
        WebDriver driver = new InternetExplorerDriver(capabilities);
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);



        urlfind find=new urlfindimp2();
        find.findvideourl(proxy,driver,"http://news.cctv.com/2018/07/04/VIDEF8VtLTIVABYKHdHh5vjF180704.shtml");
        driver.close();
        System.out.println("---------------end--------------");
    }
}
