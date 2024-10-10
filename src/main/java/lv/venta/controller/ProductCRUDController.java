package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

@RestController
@RequestMapping("/product/crud")
public class ProductCRUDController {

	@Autowired
	private IProductCRUDService crudService;

	@GetMapping("/all") // localhost:8080/product/crud/all
	public ResponseEntity<?> getProductCRUDAll() {
		try {
			ArrayList<Product> allProducts = crudService.retrieveAll();
			return new ResponseEntity<ArrayList<Product>> (allProducts, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/one") // localhost:8080/product/crud/one?id=2
	public ResponseEntity<?> getProductCRUDOne(@RequestParam("id") int id) {
		try {
			Product foundProduct = crudService.retrieveById(id);
			return new ResponseEntity<Product>(foundProduct, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/product/crud/all/2
	public ResponseEntity<?> getProductCRUDById(@PathVariable("id") int id, Model model) {
		try {
			Product foundProduct = crudService.retrieveById(id);
			return new ResponseEntity<Product>(foundProduct, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/insert")
	public ResponseEntity<?> postProductCRUDInsert(@RequestBody @Valid Product product, BindingResult result) {// ienāk aizpildītais produkts
		// vai ir kādi validācijas pāŗkāpumi
		if (result.hasErrors()) {
			return new ResponseEntity<String>("Error with data validation " + result.getFieldError().getField(), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Product returnProduct = crudService.create(product);
			return new ResponseEntity<Product>(returnProduct, HttpStatus.OK);
		}

	}


	@PutMapping("/update/{id}")
	public ResponseEntity<?> postProductCRUDUpdateById(@PathVariable("id") int id, @RequestBody @Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<String>("Error with data validation " + result.getFieldError().getField(), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {

			try {
				Product updatedProduct =crudService.updateById(id, product);
				return new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);			
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}
	//TODO
	@DeleteMapping("/delete/{id}")//localhost:8080/product/crud/delete/1
	public ResponseEntity<?> getProductCRUDDeleteById(@PathVariable("id") int id) {
		
		try {
			Product deletedProduct = crudService.deleteById(id);
		
			return new ResponseEntity<Product>(deletedProduct, HttpStatus.OK);
		
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	//izveidot jaunu kontrolieru kalsi priekš filter servisa funkcijām
	//izveidot 4 get mapping funkcijas, kur katra izsauc savu servisa filtrāciajs funkciju

}
