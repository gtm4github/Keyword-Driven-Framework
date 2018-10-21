package script;
/*
--># Keyword Driven Framework: we write some 'Keywords' either in excel sheet or in property file and calling those keywords,
                                to executing the framework is called as Keyword driven F/W
   # what is 'Keywords'-->'enter' is the keyword for username textbox,'click' is keyword for login button
   # here no. of manual test cases = no. of excel sheets,here we will write only one 'Test Class'[we r writing test cases in test class]
--> Advantage: only one automation expert is required,other than manual engg and 'xpath expert' who will write excel sheet
              # its a light weight,bcz only one Test class is there
--> Limitation: we cannot automate complex scenario like module dependent type cannot be automated
 
 */
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import generic.IAutoConst;
import generic.Utility;

public class ScriptLess implements IAutoConst{
	static {
        System.setProperty(CHROME_KEY, CHROME_VALUE);//---> better way to write by using/implementing IAutoConst interface
		System.setProperty(GECKO_KEY, GECKO_VALUE);

		//System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		}
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.actitime.com");
		
		for (int i=1; i <=4; i++) {
			String k1=Utility.getXLData("./input/dataUsingPropertyFile.xlsx", "Keys", i, 0);
			String k2=Utility.getXLData("./input/dataUsingPropertyFile.xlsx", "Keys", i, 1);
			String k3=Utility.getXLData("./input/dataUsingPropertyFile.xlsx", "Keys", i, 2);
			
			System.out.println("k1: "+k1);
			System.out.println("k2: "+k2);
			System.out.println("k3: "+k3);
		
			String xp=Utility.getPropertyValue(CONFIG_PATH, k2);
			System.out.println(xp);
			
			if(k1.equals("enter")) {
				driver.findElement(By.xpath(xp)).sendKeys(k3);
			}else if(k1.equals("click")) {
				driver.findElement(By.xpath(xp)).click();
			}else {
				System.out.println("invalid k1");
			}
			Thread.sleep(1000);
			
		}
		driver.close();
	}

}
