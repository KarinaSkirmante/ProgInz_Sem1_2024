package lv.venta.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

class ProductCrudControllerTest {

	@Mock
	private static IProductCRUDService productService;
	

	@InjectMocks
	private static ProductCRUDController productController;
	
	@Autowired
	private MockMvc mockMVC;
	
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	void testGetAllController() {
		Product p1 = new Product("Galds", 12.3f, "Bruns", 23);
		Product p2 = new Product("Kresls", 22.3f, "Melns", 3);
		ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(p1, p2));
		
		try
		{
			when(productService.retrieveAll()).thenReturn(allProducts);
			
			mockMVC.perform(get("/product/crud/all"))
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
				.andExpect((ResultMatcher) jsonPath("$[0].title", "Galds"))
				.andExpect((ResultMatcher) jsonPath("$[1].title", "Kresls"));

		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	@Test
	void testPostInsertController() {
		Product p1 = new Product("Mape", 2.3f, "Zila", 2);

		try
		{
			when(productService.create(p1)).thenReturn(p1);
			
			mockMVC.perform(MockMvcRequestBuilders.post("/product/crud/insert")
			.content(new ObjectMapper().writeValueAsString(p1))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) jsonPath("$.title", "Mape"));

		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
}
