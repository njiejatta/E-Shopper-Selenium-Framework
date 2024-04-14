package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BasePage1;
import base.ExtentManager;
import pageObjects.Homepage;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormPayment;
import pageObjects.OrderFormPersInfo;
import pageObjects.OrderFormShippingMethod;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

public class OrderCompleteTest extends BasePage1 {

	public OrderCompleteTest() throws IOException {
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
		ExtentManager.log("Starting OrderCompleteTest ...");
		
		Homepage home = new Homepage(driver);
		//handles cookie prompt
		home.getCookie().click();
		home.getToggle().click();
		Thread.sleep(3000);
        home.getTestStoreLink().click();
        ExtentManager.pass("Reached the TestStoreLink");
		
		Thread.sleep(3000);
		
		ShopHomepage shopHome = new ShopHomepage(driver);
		shopHome.getProdOne().click();
		ExtentManager.pass("Successfully click on productOne");
		ShopProductPage shopProd = new ShopProductPage(driver);
		ExtentManager.pass("Successfully reached the shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Successfully increase the product quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Successfully added product to basket");
		ShopContentPanel shopcontent= new ShopContentPanel(driver);
		Thread.sleep(3000);
		shopcontent.getCheckoutBtn().click();
		ExtentManager.pass("Successfully checkout proudct");
		
		ShoppingCart shopcart= new ShoppingCart(driver);
		ExtentManager.pass("Successfully reached the shoppingCart page");
		Thread.sleep(3000);
		shopcart.getHavePromo().click();
		ExtentManager.pass("Successfully clicked the getpromo button");
		shopcart.getPromoTextbox().sendKeys("20OFF");
		shopcart.getPromoAddBtn().click();
		Thread.sleep(3000);
		shopcart.getProceedCheckoutBtn().click();
		ExtentManager.pass("Successfully clicked the proceed to checkout button");
		
		OrderFormPersInfo personForm= new OrderFormPersInfo(driver);
		ExtentManager.pass("Successfully reached the order form info page");
		personForm.getGenderMr().click();
		personForm.getFirstNameField().sendKeys("tafa");
		personForm.getLastnameField().sendKeys("njie");
		personForm.getEmailField().sendKeys("abdulqa2020@yahoo.com");
		personForm.getTermsConditionsCheckbox().click();
		Thread.sleep(3000);
		personForm.getContinueBtn().click();
		ExtentManager.pass("Successfully clicked the checkout button");
		
		OrderFormDelivery deliverForm= new OrderFormDelivery(driver);
		ExtentManager.pass("Successfully reached the order delivery form page");
		deliverForm.getAddressField().sendKeys("3091 bingo st");
		deliverForm.getCityField().sendKeys("banjul");
		Select state= new Select(deliverForm.getStateDropdown());
		state.selectByVisibleText("Hawaii");
		deliverForm.getPostcodeField().sendKeys("30077");
		Thread.sleep(3000);
		deliverForm.getContinueBtn().click();
		
		
		OrderFormShippingMethod shipping= new OrderFormShippingMethod(driver);
		ExtentManager.pass("Successfully reached the order form shipping page");
		shipping.getDeliveryMsgTextbox().sendKeys("Pleae ship with gift card");
		shipping.getContinueBtn().click();
		Thread.sleep(3000);
		
		OrderFormPayment payment= new OrderFormPayment(driver);
		ExtentManager.pass("Successfully reached the order payment page");
		payment.getPayByCheckRadioBtn().click();
		payment.getTermsConditionsCheckbox().click();
		Thread.sleep(3000);
		payment.getOrderBtn().click();
		ExtentManager.pass("Successfully completed order");
		
		
		
		


	}	
}
