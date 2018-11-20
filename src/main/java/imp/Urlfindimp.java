package imp;

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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class Urlfindimp implements urlfind {
    String[] patterns;
    public List<String> findvideourl(BrowserMobProxy proxy, WebDriver driver, String Htmlurl)throws Exception
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
                        String m3u8url=url;
                        flag = true;
                        URL urltemp=new URL(url);
                        HttpURLConnection conn = (HttpURLConnection)urltemp.openConnection ();
                        conn.connect ();
                        InputStream in = (InputStream)conn.getInputStream ();
                        InputStreamReader isr = new InputStreamReader ( in );
                        BufferedReader br = new BufferedReader ( isr );
                        String line;
                        while((line = br.readLine ())!= null){
                        if(line.endsWith ( ".ts" )){
                            res.add ( m3u8url.substring ( 0, m3u8url.length ()-9 )+line );
                        }
                        }
                        break;
                }
                if(ismp4url(url))
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
    public  void setparttern(String pattern)
    {
        patterns=pattern.split(",");
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
