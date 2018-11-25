package imp;



import net.lightbody.bmp.BrowserMobProxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;


import java.util.LinkedList;
import java.util.List;


///html/body/section/video
public class urlfindStatic implements urlfind {
  //  private String xpath="";
    public List<String> findvideourl(BrowserMobProxy proxy, WebDriver driver, String Htmlurl) {
        List<String> result = new LinkedList<String>();
        String htmlstr=httputil.getresoure(Htmlurl);
        Document doc=Jsoup.parse(htmlstr);
        Elements eles=doc.getElementsByTag("video");
          Element e= eles.first();
        String MP4URL = e.attr("src");
        result.add(MP4URL);
        return result;
    }

}
