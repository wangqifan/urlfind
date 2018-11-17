import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.WebDriver;

public interface urlfind {
    //输入视频主页地址，嗅探视频真实地址
    String findvideourl(BrowserMobProxy proxy,WebDriver driver,String Htmlurl);

}
