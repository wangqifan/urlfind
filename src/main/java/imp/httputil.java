package imp;



import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class httputil {
    public static String getresoure(String url)
    {
        CloseableHttpClient httpCilent = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
        String srtResult = "";
        try {
            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
                return srtResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpCilent.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    public static List<String> geturlfromm3u8(String url)
    {
        String resource=getresoure(url);
        List<String> res=new LinkedList<String>();
        if(resource.length()!=0)
        {
            String[] temp=resource.split("\\r?\\n", -1);
            for(String item:temp)
            {
                int index=item.indexOf('#');
                if(index<0||index>5)res.add(item);
            }
        }
        return res;
    }

}
