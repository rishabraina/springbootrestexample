package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.ProductInfo;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer>{

}
