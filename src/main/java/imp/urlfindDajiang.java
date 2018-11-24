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

public class urlfindDajiang implements  urlfind {

     //http://bbs.chinahpsy.com/thread-37906-1-1.html
    //https://www.djivideos.com/video_play/25c2cc3a-57fa-43a5-8cd9-3315e78d70a1?autoplay=false

    public List<String> findvideourl(BrowserMobProxy proxy, WebDriver driver, String Htmlurl) {
        List<String> result = new LinkedList<String>();
        String htmlstr=httputil.getresoure(Htmlurl);
        Document doc=Jsoup.parse(htmlstr);
        Elements eles=doc.getElementsByTag("iframe");
        if(eles.size()==0)return  result;
        String newurl="";
        for(Element e:eles)
        {
                if(e.attr("src").contains("dji"))
                {
                    newurl = e.attr("src");
                    break;
                }

        }
        proxy.newHar("https://v.com/");
        newurl=newurl.replace("autoplay=false","autoplay=true");
        driver.get(newurl);
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
                if (url.contains(".mp4")) {
                    result.add(url);
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return result;
    }
}
