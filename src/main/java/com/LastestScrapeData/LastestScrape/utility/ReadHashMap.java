package com.LastestScrapeData.LastestScrape.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.LastestScrapeData.LastestScrape.entity.Product;

public class ReadHashMap {
//	final static String inputFilePath = "C:\\Users\\sonph\\OneDrive\\Desktop\\Be7\\project hashmap\\hashmap.txt";
//	final static String inputFilePath = "/home/ec2-user/be7Project/hashmapFile/hashmap.txt";
	final static String inputFilePath = "/root/be7Project/hashmapFile/hashmap.txt";

	public static int[] readHashMap(HashMap<String, Product> scrapedData) {
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
			String line;
			int startProd = 0;
			int pageNum = 0;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Scrape the page:"))
					// Extract number of products
					pageNum = Integer.parseInt(line.split(":")[1].trim());
				else if (line.startsWith("Next product starts at:"))
					// Extract number of products
					startProd = Integer.parseInt(line.split(":")[1].trim());
				else {
					// Create Product object and extract product details
					Product prod = new Product();
					String[] parts = line.split(",");
					prod.setName(parts[0]);
					prod.setPrice(Float.parseFloat(parts[1]));
					String[] discountParts = parts[2].split(":");
					prod.setPriceDiscount(Float.parseFloat(discountParts[0]));

					scrapedData.put(parts[0], prod);
				}
			}
			int[] direction = {pageNum,startProd};
			return direction;

		} catch (IOException e) {
			System.out.println("Create a new hashmap file!");
			int[] direction = { 1, 0 };
			return direction;
		}
	}
}
