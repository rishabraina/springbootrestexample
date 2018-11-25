package com.example.springboot.model;

public class ProductInfoWrapper extends CurrentPriceWrapper{
	
	private String product_desc;

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

}
