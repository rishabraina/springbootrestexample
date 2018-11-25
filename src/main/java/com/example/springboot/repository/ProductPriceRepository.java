package com.example.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.model.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer>{
	
    @Query(value = "select * from product_price where product_id = :product_id",  nativeQuery=true)
	Optional<ProductPrice> findProductPriceByProductId(@Param("product_id") int product_id);
    
    @Query(value = "select * from product_info where product_id = :product_id",  nativeQuery=true)
	Optional<ProductPrice> findProductInfoByProductId(@Param("product_id") int product_id);
    
    @Transactional
    @Modifying
    @Query(value = "update ProductPrice set currency_code = :currency_code , current_value = :current_value where product_id = :product_id")
    int updateProductPrice(@Param("product_id") int product_id, @Param("currency_code") String currency_code , @Param("current_value") float current_value);

}
