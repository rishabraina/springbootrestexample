package com.example.springboot.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.CurrentPriceWrapper;
import com.example.springboot.model.ProductInfo;
import com.example.springboot.model.ProductInfoWrapper;
import com.example.springboot.model.ProductPrice;
import com.example.springboot.model.ProductPriceWrapper;
import com.example.springboot.service.ProductInfoService;

@RestController
public class ProductController {

	@Autowired
	private ProductInfoService productInfoService;
	public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@RequestMapping(value = "product/{product_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductPriceData(@PathVariable String product_id) {
		logger.info("Fetching product price details for product id {} ", product_id);
		Optional<ProductPrice> productPrice = productInfoService.getProductPriceInfo(Integer.valueOf(product_id));

		if (!productPrice.isPresent()) {
			logger.error("Product id {} not found ", product_id);
			return new ResponseEntity<String>("Product with id " + product_id + "not found", HttpStatus.NOT_FOUND);
		}

		ProductPriceWrapper priceWrapper = new ProductPriceWrapper();
		CurrentPriceWrapper currWrapper = new CurrentPriceWrapper();
		currWrapper.setCurrency_code(productPrice.get().getCurrency_code());
		currWrapper.setValue(productPrice.get().getCurrent_value());
		priceWrapper.setId(productPrice.get().getPid().getProduct_id());
		priceWrapper.setCurrent_price(currWrapper);
		return new ResponseEntity<ProductPriceWrapper>(priceWrapper, HttpStatus.OK);
	}

	@RequestMapping(value = "product/{product_id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProductPrice(@RequestBody ProductPriceWrapper price,
			@PathVariable String product_id) {
		logger.info("Updating product price details with product id {} ", product_id);

		Optional<ProductPrice> productPrice = productInfoService.getProductPriceInfo(Integer.valueOf(product_id));
		if (!productPrice.isPresent()) {
			logger.error("Product id {} not found ", product_id);
			return new ResponseEntity<String>(
					"Unable to update product details . Product id {} not found " + product_id, HttpStatus.NOT_FOUND);
		}

		ProductPrice prPrice = new ProductPrice();
		prPrice.setCurrent_value(price.getCurrent_price().getValue());
		prPrice.setCurrency_code(price.getCurrent_price().getCurrency_code());

		int info = productInfoService.updateProductPrice(product_id, prPrice);

		return new ResponseEntity<Integer>(info, HttpStatus.OK);
	}

	@RequestMapping(value = "product/productInfo/{product_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductFullDetails(@PathVariable String product_id) {
		logger.info("Fetching product entire detail for product id {} ", product_id);
		Optional<ProductPrice> productPrice = productInfoService.getProductPriceInfo(Integer.valueOf(product_id));
		Optional<ProductInfo> productInfo = productInfoService.getProductInfoDetails(Integer.valueOf(product_id));

		if (!productInfo.isPresent()) {
			logger.error("Product id {} not found ", product_id);
			return new ResponseEntity<String>("Product with id " + product_id + "not found", HttpStatus.NOT_FOUND);
		}

		ProductPriceWrapper priceWrapper = new ProductPriceWrapper();
		ProductInfoWrapper currWrapper = new ProductInfoWrapper();
		currWrapper.setProduct_desc(productInfo.get().getProduct_desc());
		currWrapper.setCurrency_code(productPrice.get().getCurrency_code());
		currWrapper.setValue(productPrice.get().getCurrent_value());
		priceWrapper.setId(productPrice.get().getPid().getProduct_id());
		priceWrapper.setCurrent_price(currWrapper);
		return new ResponseEntity<ProductPriceWrapper>(priceWrapper, HttpStatus.OK);
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseEntity<?> createProduct(@RequestBody ProductInfo productInfo) {
		logger.info("Creating Product Info");
		ProductInfo prInfo;
		try {
			prInfo = productInfoService.saveProductInfo(productInfo);
		} catch (Exception ex) {
			logger.error(" Product with prod desc {} already exist ", productInfo.getProduct_desc());
			return new ResponseEntity<String>(
					"Product with product description already exist " + productInfo.getProduct_desc(),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ProductInfo>(prInfo, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{product_id}/price", method = RequestMethod.POST)
	public ResponseEntity<?> createProductPriceInfo(@PathVariable String product_id,
			@RequestBody CurrentPriceWrapper priceInfo) {
		logger.info(" Creating Product's Price entry for product id {} ", product_id);
		Optional<ProductInfo> productInfo = productInfoService.getProductInfoDetails(Integer.valueOf(product_id));
		if (!productInfo.isPresent()) {
			logger.error(
					" Cannot create price entry for the product with id {} . Product details not present in product information table",
					product_id);
			return new ResponseEntity<String>(" Product Price Entry for product with id {} not created ",
					HttpStatus.BAD_REQUEST);
		}

		Optional<ProductPrice> price = productInfoService.getProductPriceInfo(Integer.valueOf(product_id));
		if (price.isPresent()) {
			logger.error(" Product Price Entry for this product id {} cannot be created as it is already present ",
					product_id);
			return new ResponseEntity<String>("Price Entry cannot be created as it is already present " + product_id,
					HttpStatus.UNAUTHORIZED);
		}

		ProductPrice prPrice = new ProductPrice();
		prPrice.setPid(productInfo.get());
		prPrice.setCurrency_code(priceInfo.getCurrency_code());
		prPrice.setCurrent_value(priceInfo.getValue());

		return new ResponseEntity<ProductPrice>(productInfoService.saveProductPriceInfo(prPrice), HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{product_id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProductInfo(@PathVariable String product_id) {
		logger.info(" deleting product info for product id {} ", product_id);
		Optional<ProductInfo> productInfo = productInfoService.getProductInfoDetails(Integer.valueOf(product_id));
		if (!productInfo.isPresent()) {
			logger.error(" Product with product id {} does not exist ", product_id);
			return new ResponseEntity<String>(" Product with product id " + product_id + " does not exist",
					HttpStatus.NOT_FOUND);
		}

		productInfoService.deleteProductInfo(Integer.valueOf(product_id));
		return new ResponseEntity<String>(" Product Info for product id {} has been deleted " + product_id,
				HttpStatus.OK);
	}

}
