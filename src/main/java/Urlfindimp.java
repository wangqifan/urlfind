import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;

public class Urlfindimp implements urlfind {
    public List<String> findvideourl(BrowserMobProxy proxy, WebDriver driver, String Htmlurl)
    {
        List<String> res=new LinkedList<String>();
        proxy.newHar("https://v.com/");
        driver.get(Htmlurl);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
        List<WebElement> es = driver.findElements(By.tagName("iframe"));
        if(es.size()!=0)
        {
            for(WebElement e : es) {
                if (e.getAttribute("src").contains("youku")) {
                    driver.switchTo().frame(e);
                    System.out.println(driver.getPageSource());
                    WebElement youku_e = driver.findElement(By.tagName("video"));
                    String youku_url = youku_e.getAttribute("src");
                    res.add(youku_url);
                    break;
                }
            }
            return res;
        }
      //  System.out.println(driver.getTitle());
        boolean flag=false;
        for(int i=0;i<15;i++)
        {
            try {
                Thread.sleep(5000);     //让线程等待5秒钟,这是广告播放时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Har har = proxy.getHar();
            HarLog log= har.getLog();
            List<HarEntry> entries=log.getEntries();
            for (HarEntry entrie:entries)
            {

                HarRequest request = entrie.getRequest();
                String url = request.getUrl();
                if(url.endsWith ( ".m3u8" ))
                {
                       String  m3u8url = url;
                        flag = true;
                        res.add(m3u8url);
                        break;
                }
                if(url.contains(".mp4?sdtfrom"))
                {
                    res.add(url);
                    flag=true;
                    break;
                }
            }
            if(flag)break;
        }
        proxy.removeHeader("https://v.com/");
        return res;
    }
}
