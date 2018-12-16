package imp;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;


public class urlfindimpform3u8 implements urlfind {

    public List<String>  findvideourl(BrowserMobProxy proxy,WebDriver driver,String Htmlurl)throws Exception
    {
        proxy.newHar ("http://tv.cctv.com");
        driver.get(Htmlurl);
        //需要返回的List
        List<String> list = new ArrayList<String> (  );
        for(int i=0;i<15;i++){
            try {
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace ();
            }
            Har har = proxy.getHar ();
            HarLog log = har.getLog ();
            List<HarEntry> entries = log.getEntries ();
            for (HarEntry entry:entries) {
                HarRequest request = entry.getRequest();
                String url = request.getUrl();
                if (url.contains("getHttpVideoInfo")) {
                    String jsonstr=httputil.getresoure(url);
                    JSONObject obj = new JSONObject(jsonstr);
                    JSONArray data = obj.getJSONObject("video").getJSONArray("chapters");
                    for (int index = 0; index < data.length(); ++index) {
                        JSONObject item =data.getJSONObject(index);
                         String chapterurl= item.getString("url");
                         list.add(chapterurl);
                    }
                    return list;
                }
            }
        }
        return list;

    }

}
