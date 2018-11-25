package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.ProductInfo;
import com.example.springboot.model.ProductPrice;
import com.example.springboot.repository.ProductInfoRepository;
import com.example.springboot.repository.ProductPriceRepository;

@Service
public class ProductInfoService {

	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Autowired
	private ProductPriceRepository productPriceRepository;

	public void updateProductInfo(int id, ProductInfo productInfo) {
		productInfoRepository.save(productInfo);
	}

	public int updateProductPrice(String product_id, ProductPrice productPrice) {
		return productPriceRepository.updateProductPrice(Integer.valueOf(product_id), productPrice.getCurrency_code(),
				productPrice.getCurrent_value());
	}

	public Optional<ProductPrice> getProductPriceInfo(int product_id) {
		return productPriceRepository.findProductPriceByProductId(product_id);
	}

	public Optional<ProductInfo> getProductInfoDetails(int productId) {
		return productInfoRepository.findById(productId);
	}

	public ProductInfo saveProductInfo(ProductInfo productInfo) {
		return productInfoRepository.save(productInfo);
	}

	public ProductPrice saveProductPriceInfo(ProductPrice price) {
		return productPriceRepository.save(price);
	}

	public void deleteProductInfo(int productId) {
		productInfoRepository.deleteById(productId);
	}
	
	public List<ProductInfo> getAllProducts() {
		return productInfoRepository.findAll();
	}

}
