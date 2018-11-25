package imp;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;



//请使用chorme-------------------------
public class urlfindifeng implements  urlfind {

    public List<String> findvideourl(BrowserMobProxy proxy, WebDriver driver, String Htmlurl) {
        List<String> result = new LinkedList<String>();
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
                    if (url.contains("video.json?"))
                    {
                    String jsondata=httputil.getresoure(url);
                    int index1=jsondata.indexOf("{");
                    int index2=jsondata.lastIndexOf("}");
                    String  data=jsondata.substring(index1,index2+1);

                    JSONObject obj = new JSONObject(data);
                    String videourl= obj.getString("videoPlayUrl");
                    result.add(videourl);
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return result;
    }

}
