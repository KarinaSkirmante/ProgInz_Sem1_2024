package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.model.Product;
import lv.venta.model.dto.ProductDTOForBuy;
import lv.venta.service.IWebStoreService;

@RestController
@RequestMapping("/store")
public class WebStoreController {
	
	@Autowired
	private IWebStoreService webService;
	
	
	@PostMapping("/buy")//localhost:8080/store/buy
	public ResponseEntity<?> postBuyProducts(@RequestBody ArrayList<ProductDTOForBuy> productsToBuy){
		
		try
		{
			ArrayList<Product> result = webService.buy(productsToBuy);
			return new ResponseEntity<ArrayList<Product>>(result, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
