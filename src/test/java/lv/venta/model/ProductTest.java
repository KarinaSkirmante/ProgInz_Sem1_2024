package lv.venta.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


class ProductTest {

	private static Product productDefault;
	private static Product productGood;
	private static Product productBad;
	private static Product productNull;
	private static Product productNullVariables;

	
	private static Validator validator;
	
	@BeforeAll
	static void setUp() {
		productDefault = new Product();
		productGood = new Product("Burkans", 0.88f, "Oranzs", 12);
		productBad = new Product("23761da", -1f, "", -34);
		productNull = null;
		productNullVariables =  new Product(null, 0, null, 0);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		
	}

	//TODO lai tests tiku iziets, vajadzetu tomēr beargumenta konstruktorā uzsettot ne null vērtības
	@Test
	void testProductDefault() {
		assertEquals("", productDefault.getTitle());
		assertEquals(0, productDefault.getPrice(), 0.001);
		assertEquals("", productDefault.getDescription());
		assertEquals(0, productDefault.getQuantity());
		
	}
	@Test
	void testProductGood() {
		assertEquals("Burkans", productGood.getTitle());
		assertEquals(0.88f, productGood.getPrice(), 0.001);
		assertEquals("Oranzs", productGood.getDescription());
		assertEquals(12, productGood.getQuantity());
	}
	
	//TODO lai tests tikuti iziets, nepieciešams Produkt klasē definēt savas set funkcijas ar pārbaudēm
	@Test
	void testProductBad() {
		assertEquals("", productBad.getTitle());
		assertEquals(0, productBad.getPrice(), 0.001);
		assertEquals("", productBad.getDescription());
		assertEquals(0, productBad.getQuantity());
	}
	
	@Test
	void testProductNull() {
		assertThrows(NullPointerException.class, ()->{productNull.getId();});
	}
	
	//TODO izveidot savu set funkciju un pēc nepiecieamsības mest izņemumu, ja title vai description taisa kā null
	
	@Test
	void testProductNullVariables() {
		assertThrows(NullPointerException.class, ()->{productNullVariables.getTitle();});
		assertThrows(NullPointerException.class, ()->{productNullVariables.getDescription();});
	}
	
	
	
	//--------------- Validāciju testi -----------------
	
	@Test
	void testProductValidationTitleSize() {
		Product p1 = new Product("B", 0.88f, "Oranzs", 12);
		
		Set<ConstraintViolation<Product>> result = validator.validate(p1);
		
		assertEquals(1, result.size());//jābūt tikai vienam validācijas pārkāpumam
		assertTrue(result.iterator().next().getMessage().contains("size must be"));
	}
	
	

}
