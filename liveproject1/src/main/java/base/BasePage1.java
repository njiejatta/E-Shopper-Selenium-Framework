package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage1 {

	public static WebDriver driver;
	
	private String url;
	public static String screenShotDestinationPath;
	
	public WebDriver getDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream data= new FileInputStream("C:\\Users\\IBRAHIMDAK\\Desktop\\Resources folder\\Workspace\\liveproject1\\src\\main\\java\\resources\\config.properties");
		prop.load(data);	
		
		if(prop.getProperty("broswer").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if(prop.getProperty("broswer").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}else {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		//takeSnapShot(driver);
		
		return driver;
	
		
	}
	
	public String getUrl() throws IOException {
		Properties prop = new Properties();
		FileInputStream data= new FileInputStream("C:\\Users\\IBRAHIMDAK\\Desktop\\Resources folder\\Workspace\\liveproject1\\src\\main\\java\\resources\\config.properties");
		prop.load(data);	
		url= prop.getProperty("url");
		return url;
	}
	
	public static String takeSnapShot(String name) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destFile = "C:\\Users\\IBRAHIMDAK\\Desktop\\Resources folder\\Screenshots\\"+ timestamp() +".png";

		screenShotDestinationPath=destFile;
		
		try {
			FileUtils.copyFile(srcFile, new File(destFile));
		}catch (IOException e) {
			e.printStackTrace();
		}
       return name;
	}
	
	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	
	public static String screenShotDestinationPath() {
		return screenShotDestinationPath;
	}
}
