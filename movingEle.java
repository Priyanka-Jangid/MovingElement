import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Point;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class movingEle {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/prijangi/Documents/selenium_driver/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);	
		driver.get("https://www.coindhan.com");			
		System.out.println(driver.getTitle());
		
		ArrayList<Integer> xCordVal =  new ArrayList<Integer>();	//list for storing x-coordinate values for Bitcoin element
				
		for(int i=0;i<4;i++) {
			WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'pg-market-strip__item')]/following::span[text()='Bitcoin']"));
			Point point = ele.getLocation();
			xCordVal.add(point.getX());		
		}
		
		System.out.println("cord : "+xCordVal);
		
		ArrayList<Integer> sortVal =  new ArrayList<Integer>();	
		sortVal.addAll(xCordVal);		//copying xcoordinate values another list 
		
		 Collections.sort(sortVal, Collections.reverseOrder());		 // sorting list of coordinate value 
		 Assert.assertEquals(sortVal, xCordVal) ;					// original values are in sorted order.
		 
		 for(int i=0;i<xCordVal.size()-1;i++)
			 Assert.assertTrue(xCordVal.get(i+1) < xCordVal.get(i));	//checking x-coordinate is moving right to left, so left value will me less than right value 
		

	}

}
