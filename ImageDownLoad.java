package com.scrappingdata.ScrapeData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class ImageDownLoad {
	public static void downloadImg(HashMap<Product, Integer> scrapedData) throws IOException {

		for (Entry<Product, Integer> entry : scrapedData.entrySet()) {
			Product key = entry.getKey();

			// URL of the image to download
			LinkedList<String> imageUrl = key.getImage();

			// Target directory where the image will be saved
//			String dir = "C:\\Users\\sonph\\OneDrive\\Desktop\\Be7\\project images" + "\\" + key.getCategory();
			String dir = "/home/ec2-user/be7Project/projectImage" + "/" + key.getCategory();
			Path targetDirectory = Paths.get(dir);

			// create directory base on category, if not directory not exist, create one
			if (!Files.exists(targetDirectory)) {
				Files.createDirectories(targetDirectory);
			}


			// Download the image
			try {
				
				int nth = 1;
				for (String imgUrl : imageUrl) {
					String fileName = null;
					
					// File name for the downloaded image
					if (nth == 1)
						fileName = key.getName() + ".jpg";
					else
						fileName = key.getName()+" "+nth+".jpg";
					
					// Full path to save the image
					Path targetFilePath = targetDirectory.resolve(fileName);
					System.out.println("Starting image download...");
					
					URL urlObj = new URL(imgUrl);
					ReadableByteChannel readableByteChannel = Channels.newChannel(urlObj.openStream());

					FileOutputStream fileOutputStream = new FileOutputStream(targetFilePath.toString());
					FileChannel fileChannel = fileOutputStream.getChannel();

					fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
					fileOutputStream.close();

					System.out.println("Image downloaded successfully to: " + targetFilePath.toAbsolutePath());
					nth++;
				}
				
			} catch (IOException e) {
				System.err.println("An error occurred while downloading the image: " + e.getMessage());
			}
		}
	}

}
