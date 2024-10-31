package lv.venta.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.impl.ProductServiceImpl;

class ProductCRUDServiceTest {

	@Mock
	private static IProductRepo prodRepo;
	
	@InjectMocks
	private static ProductServiceImpl prodService;
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	void testCreateService()
	{
		Product p1 = new Product("Dators", 13.88f, "Asus", 3);
		
		when(prodRepo.save(any())).thenReturn(p1);
		//TODO
	   //when(prodRepo.findByTitleAndDescriptionAndPrice(any(), any(), 0)).thenReturn(null);
		
		Product productFromService = prodService.create(p1);
		assertEquals(3, productFromService.getQuantity());
	
		
		
	}
	
	@Test
	void testRetrieveByIdService()
	{
		Product p1 = new Product("Abols", 0.55f, "Sarkans un garšīgs", 4);
		when(prodRepo.existsById(1)).thenReturn(true);
		when(prodRepo.findById(1)).thenReturn(Optional.of(p1));
		try
		{
			Product productFromService = prodService.retrieveById(1);
			assertEquals("Abols", productFromService.getTitle());
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	@Test
	void testRetrieveByIdServiceException()
	{

		when(prodRepo.existsById(-1)).thenReturn(false);
		
		try
		{
			assertThrows(Exception.class, ()->{ prodService.retrieveById(-1);});
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
	

}
