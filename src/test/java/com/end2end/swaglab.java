package com.end2end;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class swaglab {

	WebDriver driver;
	private String text2;

	@Parameters("url")
	@Test
	public void launchswaglab(String url) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Parameters({ "username", "password" })
	@Test
	public void loginCredentials(String username, String password) {

		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        
        /**
         * Verify the user is on the Products page
         */
        
        WebElement products = driver.findElement(By.xpath("//span[text()='Products']"));
        String Actualproduct = products.getText();
        String Expectedproduct = "PRODUCTS";
        Assert.assertEquals(Actualproduct, Expectedproduct,"Verify the user is on the product page");
        System.out.println("----------PRODUCT PAGE----------");
        
	}
	
	@Test
	public void products() {
		int count=1;
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
		
        for (int i = 0; i < findElements.size(); i++) {
			
        	WebElement a = findElements.get(i);
        	
			String actual = a.getText();
			
			System.out.println("ADD TO CART is clicked");
			
			driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
			
			/**
			 * "Verify the user is on the YOUR CART PAGE"
			 */
			
			driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
			
		    WebElement cartactual = driver.findElement(By.xpath("//span[text()='Your Cart']"));
		    
		    String cartcheck = cartactual.getText();
		    
		    String Expectedcart =  "YOUR CART";
		    
		    Assert.assertEquals(cartcheck, Expectedcart,"Verify your cart");
		    
		    System.out.println("----------YOUR CART PAGE----------");
		   
		   /**
		    * Using assert verifying the same product is ADDED OR NOT
		    */
		    
			WebElement check = driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
			
			String text = check.getText();
			
			String expected = text;
			
			Assert.assertEquals(actual, expected,"verify the selected product message"); 
			
			System.out.println("product is verified "+count);
			
			count++;
			
			driver.findElement(By.id("checkout")).click();
			
			/**
			 * Verify the user is on the "Checkout: Your Information page"
			 */
			
			WebElement Checkout = driver.findElement(By.xpath("//span[text()='Checkout: Your Information']"));
			
			String Actualcheckout = Checkout.getText();
			
			String Expectedcheckout="CHECKOUT: YOUR INFORMATION";
			
			Assert.assertEquals(Actualcheckout,Expectedcheckout,"verify the user on the checkout page");
			
			System.out.println("----------Checkout page-----------");
			
			/**
			 * Get the input from USER
			 */
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the Firstname :");
			
			String firstname = scanner.nextLine();
			System.out.println("Enter the Lastname :");
			
			String lastname = scanner.nextLine();
			System.out.println("Enter the Pincode :");
			
			int pincode = scanner.nextInt();
			
			driver.findElement(By.id("first-name")).sendKeys(firstname);
			
			driver.findElement(By.id("last-name")).sendKeys(lastname);
			
			WebElement findElement = driver.findElement(By.id("postal-code"));
			
			findElement.sendKeys("pincode");
			
			driver.findElement(By.id("continue")).click();
			
			/**
			 * Verify User is on the Checkout review
			 * 
			 */
			
			WebElement overview = driver.findElement(By.xpath("//span[text()='Checkout: Overview']"));
			
			String Actualoverview = overview.getText();
			
			String Expectedoverview="CHECKOUT: OVERVIEW";
			
			Assert.assertEquals(Actualoverview, Expectedoverview,"Verify the user is on the overview page");
			
			System.out.println("----------Checkout-Overview page----------");
			
			driver.findElement(By.id("finish")).click();
			
			WebElement complete = driver.findElement(By.xpath("//span[text()='Checkout: Complete!']"));
			
			String Actualcomplete = complete.getText();
			
			String Expectedfinish="CHECKOUT: COMPLETE!";
			
			System.out.println("----------Checkout complete page----------");
			
			WebElement finalcheck = driver.findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']"));
			
		    String output = finalcheck.getText();
		    
		    System.out.println(output);
		    
		    driver.findElement(By.id("back-to-products")).click();
		    
		    
			
		}
	}

}
