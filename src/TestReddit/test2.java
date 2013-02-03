package TestReddit;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;



public class test2 {

	/**
	 * @param args
	 */
	
	static String myURL = "http://reddit.com";
	
	
	public static void main(String[] args) throws InterruptedException {
		
		ProfilesIni allProfs = new ProfilesIni();
		FirefoxProfile testProf = allProfs.getProfile("tester");
		WebDriver driver = new FirefoxDriver(testProf);
		
		JavascriptExecutor js =  (JavascriptExecutor)driver;
		//js = (JavascriptExecutor) driver;
		
		driver.get(myURL);
		Thread.sleep(2000L);
		js.executeScript("scroll(0, 250)");
		//js.executeScript("alert('POPUP');");
		
		//Verify website title
		String _title = "reddit: the front page of the internet";
		if (_title.equals(driver.getTitle()))
			System.out.println("reddit: the front page of the internet");
		String current = driver.getWindowHandle();
		System.out.println(current);
		
		//Parse 3 pages of content
		int page = 1;
		while (page < 4) {
			System.out.println("-----------------------------------------PAGE " + page + "-----------------------------------------");
			parseKarma(driver, page);
			page++;
		}

		driver.close();
	}

	private static void parseKarma(WebDriver driver, int page) {
		
		List <WebElement> votesList = new ArrayList <WebElement>();
		List <WebElement> titleList = new ArrayList <WebElement>();
		int i = 1;
		int j = 1;
		
		while(true) {
			try {
				
				WebElement votes = driver.findElement(By.xpath("//*[@id='siteTable']/div["+   i   +"]/div[1]/div[3]"));
				WebElement title = driver.findElement(By.xpath("//*[@id='siteTable']/div["+   i   +"]/div[2]/p[1]/a"));
				System.out.println(votes.getText() + " " + title.getText());
				
				i+=2;
				j++;
			}
			catch (Throwable t){
				System.out.println("%%%%%% END OF PAGE, CONTAINS " + j + " ITEMS");
				//System.out.println(t.getMessage());
				break;
			}
		}
		
		WebElement nextPage;
		if (page == 1) {
			 nextPage = driver.findElement(By.xpath("html/body/div[3]/div[2]/p/a"));
		}
		else {
			nextPage = driver.findElement(By.xpath("html/body/div[3]/div[2]/p/a[2]"));
		}
		nextPage.click();
	}

}
