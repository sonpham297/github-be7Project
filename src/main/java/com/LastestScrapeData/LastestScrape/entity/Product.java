package com.LastestScrapeData.LastestScrape.entity;

import java.util.LinkedList;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
	private String name;
	private String Category;
	private String gender;
	private LinkedList<String> image;
	private float price;
	private float priceDiscount;
	private float ratingStarBar;
	private int rateCount;
	private String generalInfo;
	private int availQty;
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(name, other.name) && 
				price == other.price && priceDiscount == other.priceDiscount;
	}
	
}
