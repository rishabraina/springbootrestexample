package com.example.springboot.model;

public class CurrentPriceWrapper {

	private float value;
	private String currency_code;

	public CurrentPriceWrapper() {

	}

	public CurrentPriceWrapper(float value, String currency_code) {
		this.value = value;
		this.currency_code = currency_code;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}
