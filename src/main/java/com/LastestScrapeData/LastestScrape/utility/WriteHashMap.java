package com.LastestScrapeData.LastestScrape.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import com.LastestScrapeData.LastestScrape.entity.Product;

public class WriteHashMap {
//	final static String outputFilePath = "C:\\Users\\sonph\\OneDrive\\Desktop\\Be7\\project hashmap\\hashmap.txt";
//	final static String outputFilePath = "/home/ec2-user/be7Project/hashmapFile/hashmap.txt";
	final static String outputFilePath = "/root/be7Project/hashmapFile/hashmap.txt";

	public static void writeHashMap(HashMap<String, Product> scrapedData, int page, int startPos) {
		// new file object
		File file = new File(outputFilePath);

		File parentDir = file.getParentFile();
		if (!parentDir.exists()) {
			if (parentDir.mkdirs()) {
				System.out.println("Directories created successfully!");
			} else {
				System.out.println("Failed to create directories!");
				return;
			}
		}

		BufferedWriter bf = null;

		try {

			// create new BufferedWriter for the output file
			bf = new BufferedWriter(new FileWriter(file));
			bf.write("Scrape the page: " + page);		
			bf.newLine();
			bf.write("Next product starts at: " + startPos);
			bf.newLine();
			// iterate map entries
			for (Entry<String, Product> entry : scrapedData.entrySet()) {
				bf.write(entry.getValue().getName() + "," + entry.getValue().getPrice()
						+ "," + entry.getValue().getPriceDiscount());

				bf.newLine();
			}
			bf.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// close the writer
				bf.close();
				System.out.println("Image downloaded successfully to: " + outputFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
