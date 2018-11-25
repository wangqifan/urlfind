package imp;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class UrlfindTencent implements urlfind {
    String[] patterns;
    public List<String> findvideourl(BrowserMobProxy proxy, WebDriver driver, String Htmlurl)
    {
        List<String> res=new ArrayList<String>();
        proxy.newHar("https://v.com/");
        driver.get(Htmlurl);
        boolean flag=false;
        for(int i=0;i<15;i++) {
            try {
                Thread.sleep(5000);     //让线程等待5秒钟,这是广告播放时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Har har = proxy.getHar();
            HarLog log = har.getLog();
            List<HarEntry> entries = log.getEntries();
            for (HarEntry entrie : entries) {

                HarRequest request = entrie.getRequest();
                String url = request.getUrl();
                if (ismp4url(url)) {
                    res.add(url);
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return res;
    }
    public  void setparttern(String pattern)
    {
        patterns=pattern.split(",");
        for(String str:patterns)
        {
            System.out.println(str);
        }
    }
    boolean ismp4url(String url)
    {
        for(String str:patterns)
        {
            if(!url.contains(str))return false;
        }
        return true;
    }
}
