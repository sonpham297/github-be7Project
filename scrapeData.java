package com.scrappingdata.ScrapeData;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class scrapeData {

	public static void main(String[] args) {
		
		//the correct path is being used in Selenium setup.
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		
		WebDriver driver;
		WebDriverWait wait;
		HashMap<Product, Integer> scrapedData = new HashMap<>();
		
		//ensure ChromeDriver is configured to use the correct Chrome binary
		ChromeOptions options = new ChromeOptions();
		options.setBinary("/usr/bin/google-chrome");
		
		//If you're running this on a headless environment like an EC2 instance, ensure Chrome is run in headless mode. 
		//Modify  Selenium code to include the headless argument:
		options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
		
		//Increase Shared Memory Size: Chrome may crash if the shared memory is insufficient. Use this argument:
		options.addArguments("--disable-dev-shm-usage");

		WebDriverManager.chromedriver().clearDriverCache().setup();
		WebDriverManager.chromedriver().clearResolutionCache().setup();
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
		int productCount = 0;
		// scrape data from 5 categories fragrances, vitamins, beauty, cosmetics and
		// clearance
		for (int i = 1; i < 6; i++) {
			// Navigate to chemistwarehouse website
			driver.get("https://www.chemistwarehouse.com.au/");

			// maximize the browser window
			driver.manage().window().maximize();

			// wait for elements to be visible and then click on it
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='unstyled inline first']")));
			WebElement pageList = driver.findElement(By.xpath("//ul[@class='unstyled inline first']"));
			pageList.findElement(By.xpath(whichPageToScrape(i))).click();

//			int pageCount = 0;

			int productInd = 0;

			// wait for all products element to be visible
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='category-product-grid']//a")));

			while (true) {
				// fetch and store the reference to all products parent web element
				WebElement allProducts = driver.findElement(By.xpath("//div[@class='category-product-grid']"));

				// use the parent web element to fetch list of web elements for each individual
				// product
				List<WebElement> productList = allProducts.findElements(By.xpath(".//a"));

				if (productInd == productList.size())
					break;

				WebElement product = productList.get(productInd);

				// make sure just only sale and not-in-store-only product will be put to the
				// hashmap
				if (isDiscountDisplayed(product) && isInStoreOnly(product) == false) {
					WebElement name = product.findElement(By.xpath(".//div[@class='product__title']"));
					
					WebElement price = product.findElement(By.xpath(".//span[@class='product__price-current']"));
					WebElement priceDiscount = product.findElement(By.xpath(".//em[@class='product__price-discount']"));
					WebElement rating = product
							.findElement(By.xpath(".//div[@class='attraqt-star-rating-stars__bar']"));
					WebElement ratingCount = product
							.findElement(By.xpath(".//div[@class='attraqt-star-rating__count']"));

					// store product info from web in to product object
					Product pd = new Product();
					
					pd.setName(name.getText());
					pd.setPrice(Float.parseFloat(price.getText().replace("$", "")));

					String[] strArray = priceDiscount.getText().split("\\s+");
					pd.setPriceDiscount(Float.parseFloat(strArray[0].replace("$", "")));

					if (rating.getAttribute("style") == "") {
						pd.setRatingStarBar(0);
					} else {
						String[] strArray1 = rating.getAttribute("style").split("\\s+");
						pd.setRatingStarBar(Float.parseFloat(strArray1[1].replaceAll("[%;]", "")));
					}
					pd.setRateCount(Integer.parseInt(ratingCount.getText().replaceAll("[()]", "")));

					// click the link to get more info about the product
					product.click();

					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(".//div[@class='slider_pi slider-container']")));
                      //check if the product has more images, if not just get 1 main image
					LinkedList<String> img = new LinkedList<String>();
					if (isMoreImagesDisplayed(driver) || moreImagesButHidden(driver)) {
                    	  WebElement moreImg = driver.findElement(By.xpath("//div[@debug-id='slide-board']"));
                    	  List<WebElement> listImg = moreImg.findElements(By.xpath(".//a"));
                    	  
                    	  for (WebElement extra : listImg) {
                        	  img.add(extra.getAttribute("href"));
						}
                  	  
                      } else {
                     	  WebElement image = driver.findElement(By.xpath("//a[@class='image_enlarger']"));
      					  img.add(image.getAttribute("href"));
                     }
					pd.setImage(img);                	  

					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(".//div[@class='product-info-container']")));

					// some products don't have general information
					if (isInfoDisplayed(driver)) {
						WebElement productInfo = driver
								.findElement(By.xpath(".//section[@class='product-info-section general-info ']"));
						WebElement generalInfo = productInfo.findElement(By.xpath(".//div[@class='details']"));
						pd.setGeneralInfo(generalInfo.getAttribute("innerText"));
					}

					// store product category and gender
					putCategory(pd, driver.findElement(By.xpath("//div[@class='breadcrumbs']")).getText());
					putGender(pd, driver.findElement(By.id("curr_cat_id")).getText());

					Select availQty = new Select(driver.findElement(By.id("avail_qty")));
					List<WebElement> elementCount = availQty.getOptions();
					pd.setAvailQty(elementCount.size());

					// add each product to product hashmap
					if (scrapedData.containsKey(pd) == false)
						scrapedData.put(pd, productCount);

					// driver nagivate back to the main category
					driver.navigate().back();
				}
				productCount++;
				productInd++;
			}
//			pageCount++;

			System.out.println("Total product: " + productCount);

//        	  // This part handles the pagination, but for now just scrape data from first page
//        	  while(true){
//        			implement the above code here	  	
//                
//                if (isNextButtonDisplayed(driver))
//        			  driver.findElement(By.xpath("//button[contains(.,'Next')]")).click();
//        	  	else
//        		 	 break;
//        	   }
		}

		// ImageDownLoad downloadImg = new ImageDownLoad();
		try {
			ImageDownLoad.downloadImg(scrapedData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// write products hashmap into a file so when we load data
		// from original web again, we can read the product hashmap
		// from a file and compare, which one change, then we change data
		// if not, don't worry
		WriteHashMap.writeHashMap(scrapedData);

		// insert data from scraped data to database
		InsertData.insertData(scrapedData);

		driver.quit();
	}

	private static String whichPageToScrape(int value) {
		switch (value) {
		case 1:
			return ScrapedPageEnum.FRAGRANCES.xpath;
		case 2:
			return ScrapedPageEnum.VITAMINS.xpath;
		case 3:
			return ScrapedPageEnum.BEAUTY.xpath;
		case 4:
			return ScrapedPageEnum.COSMETICS.xpath;
		default:
			return ScrapedPageEnum.CLEARANCE.xpath;
		}
	}

	private static boolean isNextButtonDisplayed(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//button[contains(.,'Next')]")).isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	private static boolean isDiscountDisplayed(WebElement product) {
		try {
			return product.findElement(By.xpath(".//em[@class='product__price-discount']")).isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	private static boolean isInStoreOnly(WebElement product) {
		try {
			return (product.findElement(By.xpath(".//img[@role='presentation']")).getAttribute("src")
					.contains("in-store-only"));
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	private static boolean isMoreImagesDisplayed(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//div[@class='sub_images readmore ']")).isDisplayed();
			
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	private static boolean moreImagesButHidden(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//div[@class='sub_images readmore readmorehide']")).isDisplayed();
			
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	private static boolean isInfoDisplayed(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//section[@class='product-info-section general-info ']")).isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	private static void putCategory(Product product, String info) {
		if (info.contains("Fragrances"))
			product.setCategory("Fragrances");
		else if (info.contains("Vitamins & Supplements"))
			product.setCategory("Vitamins & Supplements");
		else if (info.contains("Cosmetics"))
			product.setCategory("Cosmetics");
		else if (info.contains("Clearance"))
			product.setCategory("Clearance");
		else if (info.contains("Baby Care"))
			product.setCategory("Baby Care");
		else if (info.contains("Hair Care"))
			product.setCategory("Hair Care");
		else if (info.contains("Skin Care"))
			product.setCategory("Skin Care");
		else
			product.setCategory("Beauty");
	}

	private static void putGender(Product product, String info) {
		if (info.contains("Womens"))
			product.setGender("W");
		else if (info.contains("Mens"))
			product.setGender("M");
		else
			product.setGender("U");
	}
}
