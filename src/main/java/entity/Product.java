package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
	private String name;
	private String category;
	private String gender;
	private float price;
	private float priceDiscount;
	private float ratingStarBar;
	private int rateCount;
	private String generalInfo;
	private int availQty;
}