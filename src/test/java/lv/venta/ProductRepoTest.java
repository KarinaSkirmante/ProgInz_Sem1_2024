package lv.venta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProductRepoTest {

	@Autowired
	private IProductRepo prodRepo;
	
	
	
	@Test
	void testCreateProduct() {
		Product p1 = new Product("Burkans", 0.88f, "Oranzs", 12);
		
		Product productSaved = prodRepo.save(p1);
		
		assertEquals("Burkans", productSaved.getTitle());
		assertEquals(0.88f, productSaved.getPrice());
		assertEquals("Oranzs", productSaved.getDescription());
		assertEquals(12, productSaved.getQuantity());
		assertNotEquals(0, productSaved.getId());
		
	}


	@Test
	void testFindByPriceLessThan() {
		Product p1 = new Product("Kaposts", 0.45f, "Zals", 12);
		Product p2 = new Product("Vinogas", 1.88f, "Zalas", 10);
		prodRepo.save(p1);
		prodRepo.save(p2);
		
	    ArrayList<Product> result = prodRepo.findByPriceLessThan(0.5f);
	    assertEquals(1, result.size());
	    assertEquals("Kaposts", result.get(0).getTitle());
		
		
	}
	@Test
	void testUpdateProduct()
	{
		Product p1 = new Product("Ziepes", 1.46f, "Smarzigas", 3);
		Product productSaved = prodRepo.save(p1);
		//TODO var arī citus parametrus rediģēt
		productSaved.setPrice(0.99f);
		Product productAfterEditing = prodRepo.save(productSaved);
		assertEquals(0.99f, productAfterEditing.getPrice());
		
	}
	

}
