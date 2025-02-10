package com.LastestScrapeData.LastestScrape;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.LastestScrapeData.LastestScrape.entity.Product;
import com.LastestScrapeData.LastestScrape.utility.DataUtils;
import com.LastestScrapeData.LastestScrape.utility.ImageDownLoad;
import com.LastestScrapeData.LastestScrape.utility.InsertData;
import com.LastestScrapeData.LastestScrape.utility.ReadHashMap;
import com.LastestScrapeData.LastestScrape.utility.UpdateData;
import com.LastestScrapeData.LastestScrape.utility.WriteHashMap;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScrapeDataMain {
	public static void main(String[] args) {
		try {
			// the correct path is being used in Selenium setup.
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

			WebDriver driver;
			WebDriverWait wait;

//		HashMap<Product, Integer> oldScrapedData = new HashMap<>();
			HashMap<String, Product> oldScrapedData = new HashMap<>();

			// these parameters will identify which product will be scrapped first
			// and which page will be scrapped data
			int[] direction = ReadHashMap.readHashMap(oldScrapedData);
			int page = direction[0];
			int startPos = direction[1];
			
			
			//After scrape all product in 5 pages
			//this part will reset the page and start position
			//so the app will scrape from the beginning 
			//to update the products price if need
			if (page == 6) 				
				WriteHashMap.writeHashMap(oldScrapedData, 1, 0);
			

//		HashMap<Product, Integer> scrapedData = new HashMap<>();
			HashMap<String, Product> scrapedData = new HashMap<>();
			HashMap<String, Product> updateScrapedData = new HashMap<>();

			// ensure ChromeDriver is configured to use the correct Chrome binary
			ChromeOptions options = new ChromeOptions();
//		options.setBinary("/usr/bin/google-chrome");
			options.setBinary("/opt/google/chrome/chrome");

			// If you're running this on a headless environment like an EC2 instance, ensure
			// Chrome is run in headless mode.
			// Modify Selenium code to include the headless argument:
			options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--remote-allow-origins=*");

			// Increase Shared Memory Size: Chrome may crash if the shared memory is
			// insufficient. Use this argument:
			options.addArguments("--disable-dev-shm-usage");

			WebDriverManager.chromedriver().clearDriverCache().setup();
			WebDriverManager.chromedriver().clearResolutionCache().setup();
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver(options);
//		driver = new ChromeDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(300L));
			int productCount = 0;
			// scrape data from 5 categories fragrances, vitamins, beauty, cosmetics and
			// clearance
			while (page < 6) {
				// Navigate to chemistwarehouse website
				driver.get("https://www.chemistwarehouse.com.au/");

				// maximize the browser window
				driver.manage().window().maximize();

				// wait for elements to be visible and then click on it
				wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='unstyled inline first']")));
				WebElement pageList = driver.findElement(By.xpath("//ul[@class='unstyled inline first']"));
				pageList.findElement(By.xpath(DataUtils.whichPageToScrape(page))).click();

//			int pageCount = 0;

				// wait for all products element to be visible
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//div[@class='category-product-grid']/a")));

//			while (true) {
				while (productCount != 20) {

					// fetch and store the reference to all products parent web element
					WebElement allProducts = driver.findElement(By.xpath("//div[@class='category-product-grid']"));

					// use the parent web element to fetch list of web elements for each individual
					// product
					List<WebElement> productList = allProducts.findElements(By.xpath(".//a"));

					// if reach the last product of the current page, move to the next page
					if (startPos == productList.size()) {
						startPos = 0;
						page++;
						break;
					}

					WebElement product = productList.get(startPos);

					// make sure just only sale and not-in-store-only product will be put to the
					// hashmap
					if (DataUtils.isDiscountDisplayed(product) && DataUtils.isInStoreOnly(product) == false) {
						WebElement name = product.findElement(By.xpath(".//div[@class='product__title']"));

						WebElement price = product.findElement(By.xpath(".//span[@class='product__price-current']"));
						WebElement priceDiscount = product
								.findElement(By.xpath(".//em[@class='product__price-discount']"));
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

						// check if it a ultra beauty product
						if (DataUtils.isUltraBeaury(product)) {
							product.click();
							wait.until(ExpectedConditions
									.presenceOfElementLocated(By.xpath("//div[@class='slick-track']/div")));
							wait.until(ExpectedConditions
									.presenceOfElementLocated(By.xpath("//div[@data-testid='product-description']")));
							// check if the product has more images, if not just get 1 main image
							LinkedList<String> img = new LinkedList<String>();
							WebElement moreImg = driver.findElement(By.xpath("//div[@class='slick-track']"));
							List<WebElement> listImg = moreImg.findElements(By.xpath(".//div"));

							for (WebElement extra : listImg) {
								String temp = extra.findElement(By.xpath(".//img")).getAttribute("src");
								System.out.println(temp);
								if (img.contains(temp) == false)
									img.add(temp);
							}
							pd.setImage(img);

							wait.until(ExpectedConditions
									.presenceOfElementLocated(By.xpath(".//div[@class='sc-bRlCZA dDwNuF']")));

							// some products don't have general information
							WebElement productInfo = driver.findElement(By.xpath(".//div[@class='sc-bRlCZA dDwNuF']"));
							pd.setGeneralInfo(productInfo.getAttribute("innerText"));

							// store product category and gender
							DataUtils.putCategory(pd,
									driver.findElement(By.xpath("//ul[@class='sc-gikAfH lohDFu']")).getText());
							DataUtils.putGender(pd,
									driver.findElement(By.xpath("//ul[@class='sc-gikAfH lohDFu']")).getText());

							Select availQty = new Select(driver
									.findElement(By.xpath("//select[@class='react-select-control-native__select']")));
							List<WebElement> elementCount = availQty.getOptions();
							pd.setAvailQty(elementCount.size());

						} else {
							product.click();
							wait.until(ExpectedConditions
									.presenceOfElementLocated(By.xpath(".//div[@class='slider_pi slider-container']")));
							// check if the product has more images, if not just get 1 main image
							LinkedList<String> img = new LinkedList<String>();
							if (DataUtils.isMoreImagesDisplayed(driver) || DataUtils.moreImagesButHidden(driver)) {
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
							if (DataUtils.isInfoDisplayed(driver)) {
								WebElement productInfo = driver.findElement(
										By.xpath(".//section[@class='product-info-section general-info ']"));
								WebElement generalInfo = productInfo.findElement(By.xpath(".//div[@class='details']"));
								pd.setGeneralInfo(generalInfo.getAttribute("innerText"));
							}

							// store product category and gender
							DataUtils.putCategory(pd,
									driver.findElement(By.xpath("//div[@class='breadcrumbs']")).getText());
							DataUtils.putGender(pd, driver.findElement(By.id("curr_cat_id")).getText());

							Select availQty = new Select(driver.findElement(By.id("avail_qty")));
							List<WebElement> elementCount = availQty.getOptions();
							pd.setAvailQty(elementCount.size());
						}

						// add each product to product hashmap, if the name's already existed,
						// check if the price is changed or not, then update data
						if (oldScrapedData.containsKey(pd.getName()) == false) {
							oldScrapedData.put(pd.getName(), pd);
							scrapedData.put(pd.getName(), pd);
						} else {
							Product temp = oldScrapedData.get(pd.getName());
							if (temp.getPrice() != pd.getPrice()) {
								updateScrapedData.put(pd.getName(), pd);
							}
						}

						// driver nagivate back to the main category
						driver.navigate().back();
					}
					productCount++;
					startPos++;
				}

				if (productCount == 20) {
					break;
				}

//			pageCount++;

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

			// insert data and download image from scraped data to database
			if (scrapedData.size() > 0) {
				InsertData.insertData(scrapedData);
				ImageDownLoad.downloadImg(scrapedData);
			}

			// update data if need
			if (updateScrapedData.size() > 0)
				UpdateData.updateData(updateScrapedData);

			// write products hashmap into a file so when we load data
			// from original web again, we can read the product hashmap
			// from a file and compare, which one change, then we change data
			// if not, don't worry
			WriteHashMap.writeHashMap(oldScrapedData, page, startPos);

			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}