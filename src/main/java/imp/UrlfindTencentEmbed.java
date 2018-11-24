package imp;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;

public class UrlfindTencentEmbed implements  urlfind {
    private String[] patterns;
    public List<String> findvideourl(BrowserMobProxy proxy, WebDriver driver, String Htmlurl) {
        List<String> result = new LinkedList<String>();
        String htmlstr = httputil.getresoure(Htmlurl);
        Document doc = Jsoup.parse(htmlstr);
        Elements eles = doc.getElementsByTag("embed");
        Element element=eles.first();
        String src= element.attr("src");
        //https://imgcache.qq.com/tencentvideo_v1/playerv3/TPout.swf?max_age=86400&v=20161117&vid=c0723fillma&auto=0
        src=src.replace("auto=0","auto=1");
        proxy.newHar("https://youku.com/");
        driver.get(src);
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
                    result.add(url);
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return result;
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
