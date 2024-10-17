package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.model.dto.ProductDTOForBuy;

@RestController
@RequestMapping("/store")
public class WebStoreController {
	
	
	@PostMapping("/buy")//localhost:8080/store/buy
	public ResponseEntity<?> postBuyProducts(@RequestBody ArrayList<ProductDTOForBuy> productsToBuy){
		System.out.println(productsToBuy);
		//TODO veikt produktu pirkšanu, izsaucot servisu
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	

}
