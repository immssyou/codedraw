/*package crawler.crawler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

*//**
 * Hello world!
 *
 *//*
public class App 
{
    public static void main( String[] args )
    {
    	WebDriver driver = new HtmlUnitDriver();
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
   
    	driver.get("http://fbpage.kr/?pi=2#/search");
    	JavascriptExecutor  js = (JavascriptExecutor) driver;
      	
      	js.executeScript("$('li')[2].click()");
      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      	JavascriptExecutor  js = (JavascriptExecutor) driver;
      	
      	js.executeScript("$('li')[2].click()");
      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      	js.executeScript("$('.btn-success')[0]");
      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	if (driver instanceof JavascriptExecutor) {
    		((JavascriptExecutor) driver)
    			.executeScript("$('.btn-success')[0].click()");
    	}
        //driver.findElement(By.xpath("//*[@id='mainContainer']/div/div[1]/a[1]")).click();
      	System.out.println(driver.findElement(By.tagName("body")).getText());
    	p("getPageSource: %s", driver.getPageSource());  
    	
    }
    
	public static void p(String format, Object... objects) {  
	    System.out.println(String.format(format, objects));
	}
}
*/