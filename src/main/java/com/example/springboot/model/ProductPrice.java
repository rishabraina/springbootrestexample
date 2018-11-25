package com.example.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ProductPrice")
public class ProductPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private float current_value;

	@Column
	@Size(max = 100)
	private String currency_code;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	private ProductInfo pid;

	public ProductPrice() {

	}

	public ProductPrice(int currentval, String currencycode) {
		this.current_value = currentval;
		this.currency_code = currencycode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCurrent_value() {
		return current_value;
	}

	public void setCurrent_value(float current_value) {
		this.current_value = current_value;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public ProductInfo getPid() {
		return pid;
	}

	public void setPid(ProductInfo pid) {
		this.pid = pid;
	}

}
