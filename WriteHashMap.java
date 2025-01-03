package com.scrappingdata.ScrapeData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class WriteHashMap {
	final static String outputFilePath = "/home/ec2-user/be7Project/hashmapFile/hashmap.txt";

	public static void writeHashMap(HashMap<Product, Integer> scrapedData) {
		// new file object
		File file = new File(outputFilePath);

		BufferedWriter bf = null;

		try {

			// create new BufferedWriter for the output file
			bf = new BufferedWriter(new FileWriter(file));

			// iterate map entries
			for (Entry<Product, Integer> entry : scrapedData.entrySet()) {
				// put key and value separated by a colon
				bf.write(entry.getKey().getName() + "," + entry.getKey().getCategory() + "," + entry.getKey().getPrice()
						+ "," + entry.getKey().getPriceDiscount() + ":" + entry.getValue());

				// new line
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
			}
		}
	}
}
