package com.example.springboot;
/*package com.example.server.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.springboot.model.ProductInfo;
import com.example.springboot.model.ProductPrice;
import com.example.springboot.repository.ProductInfoRepository;
import com.example.springboot.repository.ProductPriceRepository;

@SpringBootApplication
@EnableJpaRepositories("com.example.springboot.repository")
@EntityScan("com.example.springboot.model")
public class JpaOneToOneDemoApp implements CommandLineRunner {

	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Autowired
	private ProductPriceRepository productPriceRepository;

	public static void main(String... args) {
		SpringApplication.run(JpaOneToOneDemoApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productInfoRepository.deleteAllInBatch();
		productPriceRepository.deleteAllInBatch();

		ProductInfo pinfo = new ProductInfo("T-Shirt", "Shirt");
		ProductPrice pPrice = new ProductPrice(138546, "USD");
		
		pinfo.setProductPrice(pPrice);
		pPrice.setPid(pinfo);
		
		productInfoRepository.save(pinfo);
	}

}
*/