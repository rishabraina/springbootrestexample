package com.example.springboot.model;

public class ProductPriceWrapper {

	private int id;
	private CurrentPriceWrapper current_price;

	public ProductPriceWrapper() {

	}

	public ProductPriceWrapper(CurrentPriceWrapper current_price) {
		this.current_price = current_price;
	}

	public CurrentPriceWrapper getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(CurrentPriceWrapper current_price) {
		this.current_price = current_price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
