package imp;

import net.lightbody.bmp.BrowserMobProxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class urlfindregex implements urlfind {

    public List<String> findvideourl(BrowserMobProxy proxy, WebDriver driver, String Htmlurl) {
        List<String> result = new LinkedList<String>();
        String htmlstr=httputil.getresoure(Htmlurl);
        String pattern = "http.*?mp4";
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(htmlstr);
        if(m.find())
        {
             result.add(m.group(0));

        }
        return result;
    }
}
