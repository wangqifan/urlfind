

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.lightbody.bmp.BrowserMobProxy;

public class urlfindyouku implements  urlfind{
	public List<String> findvideourl(BrowserMobProxy proxy,WebDriver driver,String Htmlurl) {
		List<String> res = new LinkedList<String>();
		/*
		driver.get(Htmlurl );
		//�ýӿڽ����һ��������վ����Ϊ�ſ�iframe��ʽ
		//"http://bbs.chinahpsy.com/thread-37906-1-1.html";
		//"http://www.afzhan.com/video/play/t1/list_c1202_p1.html";
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
		List<WebElement> es = driver.findElements(By.tagName("iframe"));
		
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
		*/
		return res;
	}
}
