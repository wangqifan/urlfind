package imp;

import java.util.LinkedList;
import java.util.List;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.json.JSONObject;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import net.lightbody.bmp.BrowserMobProxy;
import org.w3c.css.sac.ElementSelector;

public class urlfindyouku implements urlfind {
	public List<String> findvideourl(BrowserMobProxy proxy,WebDriver driver,String Htmlurl) {
        List<String> result = new LinkedList<String>();
		//"http://bbs.chinahpsy.com/thread-37906-1-1.html";
		//"http://www.afzhan.com/video/play/t1/list_c1202_p1.html";

/*
	    String htmlstr=httputil.getresoure(Htmlurl);

	    Document doc=Jsoup.parse(htmlstr);
	    Elements eles= doc.getElementsByTag("iframe");
	    String src="";
	    if(eles.size()!=0)
        {
            Element element=eles.first();
            src=element.attr("src"); //http://player.youku.com/embed/XMjk4NzkxMTA4==
        }
        else
        {                                     //http://player.youku.com/player.php/sid/XMjk4NzkxMTA4/v.swf
            eles=doc.getElementsByTag("embed");
            if(eles.size()==0)return result;
            Element element=eles.first();
            src=element.attr("src");
            src=src.replace("player.php/sid","embed");
            src= src.replace("/v.swf","==");
        }
        */
//https://v.youku.com/v_show/id_XMjk4NzkxMTA4==.html
        proxy.newHar("youku");
        driver.get(Htmlurl);
        boolean flag = false;
        for (int i = 0; i < 15; i++) {
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
                if (url.contains("get.json")) {
                    String jsonstr=httputil.getresoure(url);
                    System.out.println(jsonstr);
                    JSONObject obj = new JSONObject(jsonstr);
                    JSONObject data = obj.getJSONObject("data");
                    JSONArray stream = data.getJSONArray("stream");
                    for (int index = 0; index < stream.length(); ++index) {
                        JSONObject item = stream.getJSONObject(index);
                        String m3u8url = item.getString("m3u8_url");
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
        }
        return result;
    }
}
