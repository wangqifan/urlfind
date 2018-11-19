import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.net.HttpURLConnection;
import java.net.URL;


public class urlfindimp2 implements  urlfind {

    public List<String>  findvideourl(BrowserMobProxy proxy,WebDriver driver,String Htmlurl)throws Exception
    {
        proxy.newHar ("http://tv.cctv.com");
        driver.get(Htmlurl);
        //需要返回的List
        List<String> list = new ArrayList<String> (  );
        for(int i=0;i<15;i++){
            boolean flag = false;
            try {
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace ();
            }
            Har har = proxy.getHar ();
            HarLog log = har.getLog ();
            List<HarEntry> entries = log.getEntries ();
            String m3u8url = "";

            for (HarEntry entry:entries){
                HarRequest request = entry.getRequest();
                String url = request.getUrl ();
                if(url.endsWith ( ".m3u8" ))
                {
                    m3u8url = url;
                    flag = true;
                    break;

                }
            }
            if(!flag) {
                continue;
            }
            System.out.println ( "m3u8"+i+m3u8url );
            //使用URLconnect 下载m3u8 读取response
            URL urltemp = new URL ( m3u8url );
            HttpURLConnection conn = (HttpURLConnection)urltemp.openConnection ();
            conn.connect ();
            InputStream in = (InputStream)conn.getInputStream ();
            InputStreamReader isr = new InputStreamReader ( in );
            BufferedReader br = new BufferedReader ( isr );

            //读取bufferreader里面的每一行。将符合条件的放入List中
            String line;

//        int tempi = 0;
            while((line = br.readLine ())!= null){
                if(line.endsWith ( ".ts" )){
                    list.add ( m3u8url.substring ( 0, m3u8url.length ()-9 )+line );
//                tempi+=1;
                }
            }
            break;

        }

        System.out.println ( list.size() );
        System.out.println ( "-------------------------------end" );
        return list;

    }

}
