package imp;

import java.util.LinkedList;
import java.util.List;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import org.json.JSONObject;
import org.json.JSONArray;
import org.openqa.selenium.WebDriver;
import net.lightbody.bmp.BrowserMobProxy;

public class urlfindYoukuiframe implements urlfind {
	public List<String> findvideourl(BrowserMobProxy proxy,WebDriver driver,String Htmlurl) {
        List<String> result = new LinkedList<String>();
		//"http://www.afzhan.com/video/play/t1/list_c1202_p1.html";
        //driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
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
                System.out.println(url);
                if (url.contains("get.json")) {
                    System.out.println(url);
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
