package uk.co.automationtesting;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage1;
import base.ExtentManager;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;
@Listeners(resources.Listeners.class)
public class EmptyBasket extends BasePage1 {
	public EmptyBasket() {
		super();
	}
	
	@BeforeTest
	public void setup() throws IOException {
		driver = getDriver();
		driver.get(getUrl());
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		driver = null;
	}
	
	@Test
	public void endToEndTest() throws InterruptedException, IOException {
		ExtentManager.log("Starting AddRemaoveItemBasketTest ...");
		
		
		Homepage home = new Homepage(driver);
		//handles cookie prompt
		home.getCookie().click();
		home.getToggle().click();
		Thread.sleep(3000);
        home.getTestStoreLink().click();
		
		Thread.sleep(3000);
		
		ShopHomepage shopHome = new ShopHomepage(driver);
		ExtentManager.pass("Reached the shop homepage");
		shopHome.getProdOne().click();
		Thread.sleep(3000);
		
		ShopProductPage shopProd = new ShopProductPage(driver);
		ExtentManager.pass("Reached the shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have sucessfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have sucessfully increased product quantity");
		shopProd.getAddToCartBtn().click();
		Thread.sleep(3000);
		ExtentManager.pass("Have sucessfully added product to basket");
		
		ShopContentPanel shopcontent= new ShopContentPanel(driver);
		shopcontent.getContinueShopBtn().click();
		Thread.sleep(3000);
		shopProd.getHomepageLink().click();
		Thread.sleep(3000);
		shopHome.getProdTwo().click();
		Thread.sleep(3000);
		Select option2 = new Select(shopProd.getSizeOption());
		option2.selectByVisibleText("L");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();
		Thread.sleep(3000);
		shopcontent.getCheckoutBtn().click();
		
		ShoppingCart shopcart= new ShoppingCart(driver);
		shopcart.getDeleteItemTwo().click();
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.invisibilityOf(shopcart.getDeleteItemTwo()));

		System.out.println(shopcart.getTotalAmount().getText());
		
		Assert.assertEquals(shopcart.getTotalAmount().getText(), "$45.24");
		
		
		
		
		
		
	
	
	}	
}
