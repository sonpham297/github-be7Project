package com.scrappingdata.ScrapeData;

public enum ScrapedPageEnum {
	FRAGRANCES(1, ".//a[contains(.,'Fragrances')]"), VITAMINS(2, ".//a[contains(.,'Vitamins')]"),
	BEAUTY(3, ".//a[contains(.,'Beauty')]"), COSMETICS(4, ".//a[contains(.,'Cosmetics')]"),
	CLEARANCE(5, ".//a[contains(.,'Clearance')]");

	public final int value;
	public final String xpath;

	private ScrapedPageEnum(int value, String xpath) {
		this.value = value;
		this.xpath = xpath;
	}
}
