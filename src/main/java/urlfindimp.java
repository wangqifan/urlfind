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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;


public class urlfindimp implements  urlfind {
    public String findvideourl(BrowserMobProxy proxy,WebDriver driver,String Htmlurl)
    {
        String res="";
        proxy.newHar("https://v.com/");
        driver.get(Htmlurl);
        System.out.println(driver.getTitle());
        boolean flag=false;
        for(int i=0;i<15;i++)
        {
            try {
                Thread.sleep(5000);     //让线程等待5秒钟,这是广告播放时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Har har = proxy.getHar();
            HarLog log= har.getLog();
            List<HarEntry> entries=log.getEntries();
            for (HarEntry entrie:entries)
            {

                HarRequest request = entrie.getRequest();
                String url = request.getUrl();
                if(url.contains(".mp4?sdtfrom"))//腾讯广告用http,视频使用http
                {
                    res=url;
                    flag=true;
                    break;
                }
            }
            if(flag)break;
        }
        proxy.removeHeader("https://v.com/");
        return res;
    }

}
