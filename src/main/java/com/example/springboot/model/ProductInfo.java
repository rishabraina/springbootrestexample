package com.example.springboot.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ProductInfo")
public class ProductInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;

	@Size(max = 100)
	@Column(unique=true)
	private String product_name;

	@Size(max = 100)
	@Column(unique=true)
	private String product_desc;
	
	@OneToOne(fetch = FetchType.LAZY ,
			  cascade = CascadeType.ALL,
			  mappedBy = "pid")
	private ProductPrice productPrice;
	
	public ProductInfo() {
		
	}
	
	public ProductInfo(String productname, String productdesc) {
		this.product_name = productname;
		this.product_desc = productdesc;
	}

	public ProductPrice getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

}
