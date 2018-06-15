package com.dice;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<String>s1 = new ArrayList();
		
		//Faker faker = new Faker();
		s1.add("Java");
		s1.add("Python");
		s1.add("QTP");
		s1.add("Ruby");
		s1.add("c++");
		
		FileWriter file = new FileWriter("C:\\Users\\Kostia\\Desktop\\ArrayList.txt");
		BufferedWriter outStream = new BufferedWriter(file);
		
		
		
			
		
		
		
		
		// Set up chrome driver path
				WebDriverManager.chromedriver().setup();

				// Invoke selenium webdriver
				WebDriver driver = new ChromeDriver();

				// full Screen
				driver.manage().window().maximize(); // or maximize fullscreen();

				// set universal wait time in case web page is slow
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				/*
				 * Step 1. Launch browser and navigate to dice.com Expected: dice home page
				 * should be displayed.
				 */

				String url = "https://www.dice.com/";
				// driver.navigate().to(url);
				driver.get(url);
				
				for(int a = 0;a<s1.size();a++) {

				String actualTitle = driver.getTitle();
				String expectedTitle = "Job Search for Technology Professionals | Dice.com";

				if (actualTitle.equals(expectedTitle)) {
					System.out.println("Dice homePage succefully loaded");
				} else {
					System.out.println("Step FAIL. Did NOT load succefully");
					throw new RuntimeException("Step FAIL. Did NOT load succefully");
				}

				/*
				 * Step 2. Insert search keyword amd location then click on find tech jobs
				 */
				String keyword = "java developer";
				driver.findElement(By.id("search-field-keyword")).clear();
				driver.findElement(By.id("search-field-keyword")).sendKeys(s1.get(a));

				String location = "22102";
				driver.findElement(By.id("search-field-location")).clear();
				driver.findElement(By.id("search-field-location")).sendKeys(location);

				driver.findElement(By.id("findTechJobs")).click();
				
				String count = driver.findElement(By.id("posiCountId")).getText();

				System.out.println(count);
				
				//ensure count is more than 0
				
				int countResult = Integer.parseInt(count.replace(",", ""));
				s1.set(a,s1.get(a)+"-"+count);
				
				outStream.write(s1.get(a).toString() + "\n");
				
				if(countResult > 1000) {
					System.out.println("more than 1000 jobs");
					
				}else {
					System.out.println("less than 1000 jobs");
				}
		
				
				driver.navigate().back();
				
				}
	
				System.out.println(s1);
				outStream.close();
				driver.close();
				// comment

			}

		}
	


