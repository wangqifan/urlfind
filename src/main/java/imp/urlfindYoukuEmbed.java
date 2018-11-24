package imp;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;


//适用于Embed youku或者是 iframe

public class urlfindYoukuEmbed implements  urlfind {
    public List<String> findvideourl(BrowserMobProxy proxy, WebDriver driver, String Htmlurl) {
        List<String> result = new LinkedList<String>();
	    String htmlstr=httputil.getresoure(Htmlurl);
	    Document doc=Jsoup.parse(htmlstr);
        Elements eles=doc.getElementsByTag("embed");
        String youkuurl="";
        if(eles.size()!=0) {
            Element element = eles.first();
            String src = element.attr("src");
            //http://player.youku.com/player.php/sid/XMjk4NzkxMTA4/v.swf
            int lastindex1 = src.lastIndexOf("/");
            int lastindex2 = src.lastIndexOf("/", lastindex1 - 1);
            String videoid = src.substring(lastindex2 + 1, lastindex1);
            youkuurl = " http://vo.youku.com/v_show/id_XMjk4NzkxMTA4==.html".replace("XMjk4NzkxMTA4", videoid);
        }
        else
        {
            eles=doc.getElementsByTag("iframe");
            if(eles.size()==0)return  result;
            for(Element e:eles)
            {
                if(e.attr("src").contains("youku"))
                {
                    youkuurl = e.attr("src");
                    break;
                }
            }

        }
        proxy.newHar("https://youku.com/");
        driver.get(youkuurl);
        List<WebElement> es  = driver.findElements(By.tagName("video"));
        if(es.size()!=0) {
            WebElement ele = es.get(0);
            String videosrc = ele.getAttribute("src");
            if (videosrc.substring(0, 4).equals("http")) {
                result.add(videosrc);
                return result;
            }
        }
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
                if (url.contains("m3u8?")) {
                    System.out.println(url);
                    String m3u8url = url;
                    List<String> templist = httputil.geturlfromm3u8(m3u8url);
                    for (String viedourl : templist) {
                        result.add(viedourl);
                    }
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return result;
    }
}
