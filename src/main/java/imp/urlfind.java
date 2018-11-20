package imp;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.WebDriver;
import java.util.List;

public interface urlfind {
    //输入视频主页地址，嗅探视频真实地址

    List<String> findvideourl(BrowserMobProxy proxy,WebDriver driver,String Htmlurl)throws Exception;

}