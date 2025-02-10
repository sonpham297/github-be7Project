package com.LastestScrapeData.LastestScrape.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.LastestScrapeData.LastestScrape.entity.Product;
import com.LastestScrapeData.LastestScrape.entity.ScrapedPageEnum;

public class DataUtils {
	public static String whichPageToScrape(int value) {
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

	public static boolean isNextButtonDisplayed(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//button[contains(.,'Next')]")).isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static boolean isDiscountDisplayed(WebElement product) {
		try {
			return product.findElement(By.xpath(".//em[@class='product__price-discount']")).isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static boolean isInStoreOnly(WebElement product) {
		try {
			return (product.findElement(By.xpath(".//img[@role='presentation']")).getAttribute("src")
					.contains("in-store-only"));
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	// this for normal product
	public static boolean isMoreImagesDisplayed(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//div[@class='sub_images readmore ']")).isDisplayed();

		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	// this for normal product
	public static boolean moreImagesButHidden(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//div[@class='sub_images readmore readmorehide']")).isDisplayed();

		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	// this for normal product
	public static boolean isInfoDisplayed(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//section[@class='product-info-section general-info ']")).isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static boolean isUltraBeaury(WebElement product) {
		try {
			return product.findElement(By.xpath(".//div[@class='product--ultra-beauty product']")).isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static void putCategory(Product product, String info) {
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

	public static void putGender(Product product, String info) {
		if (info.contains("Womens"))
			product.setGender("W");
		else if (info.contains("Mens"))
			product.setGender("M");
		else
			product.setGender("U");
	}
}
