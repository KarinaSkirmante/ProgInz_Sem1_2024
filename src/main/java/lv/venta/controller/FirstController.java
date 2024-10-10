package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.model.Product;

@RestController
public class FirstController {
	
	
	@GetMapping("/hello") //localhost:8080/hello
	public ResponseEntity<String> getHello() {
		System.out.println("First controller works");
		return new ResponseEntity<String>("First controller works", HttpStatus.OK);
	}
	//TODO uztaisīt plāšaku html lapu
	//TODO jaunu kontrolieru funkciju, kas parāda citu html lapu
	
	Random rand = new Random();
	@GetMapping("/hello/msg") //localhost:8080/hello/msg
	public ResponseEntity<String> getHelloMsg() {
		return new ResponseEntity<String>("Ziņa no Karinas:" + rand.nextInt(0, 100), HttpStatus.OK);
	}
	
	//TODO izveidot kontroliera funkciju, 
	//kura caur model pados Jūsu izveidoto produktu 
	//(izveidojam šajā fukcijā jebkādu produktu
	
	@GetMapping("/product/test")//localhost:8080/product/test
	public ResponseEntity<Product> getProductTest()
	{
		Product product = new Product("Abols", 0.99f, "Sarkans un garšīgs", 4);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK );
	}
	
	//TODO izveidot kontroliera dunkciju, kura ieksienē uztaisīt 3 produktus
	//un salikt tos sarakstā. Kā mydata iedot šo sarakstu un parādīt 
	//hmtl lapā
	
	@GetMapping("/product/test/all")//localhost:8080/product/test/all
	public ResponseEntity<ArrayList<Product>> getProductTestAll() {
		ArrayList<Product> allProducts = new ArrayList<>(
				Arrays.asList(
						new Product("Abols", 0.99f, "Sarkans un garšīgs", 4),
						new Product("Tomats", 1.99f, "Dzeltens un garšīgs", 2),
						new Product("Gurkis", 2.99f, "Zaļš un garšīgs", 1)));
	
		
		return new ResponseEntity<ArrayList<Product>>(allProducts, HttpStatus.OK);
	
	
	}
	
	
	
	
}
