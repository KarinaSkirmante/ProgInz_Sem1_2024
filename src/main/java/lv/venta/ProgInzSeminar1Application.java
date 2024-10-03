package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.venta.model.Product;
import lv.venta.model.security.MyAuthority;
import lv.venta.model.security.MyUser;
import lv.venta.repo.IProductRepo;
import lv.venta.repo.security.IMyAuthorityRepo;
import lv.venta.repo.security.IMyUserRepo;

@SpringBootApplication
public class ProgInzSeminar1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProgInzSeminar1Application.class, args);
	}
	
	@Bean //funkcija tiks izsaukta automātiski, līdz ko palaižas sistēma
	public CommandLineRunner testDatabase(IProductRepo productRepo,
			IMyAuthorityRepo authRepo, IMyUserRepo userRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				//TODO izveidot 3 produktus
				//ar save fukciju saglabāt repozitorijā
				Product p1 = new Product("Abols", 0.99f, "Sarkans un garšīgs", 4);
				Product p2  =new Product("Tomats", 1.99f, "Dzeltens un garšīgs", 2);
				Product p3 = new Product("Gurkis", 2.99f, "Zaļš un garšīgs", 1);
				productRepo.save(p1);
				productRepo.save(p2);
				productRepo.save(p3);
				//izsaukt caur repo count()
				System.out.println("How Many products: " + productRepo.count());
				//izsaukt caur repo findById()
				System.out.println("Get product by id (2):" + productRepo.findById(2).get());

				System.out.println("All products: " + productRepo.findAll());
				//pamegināt dzesānu
				Product productForDeleting = productRepo.findById(2).get();
				productRepo.delete(productForDeleting);
				//iztisīt update caur repo
				Product productForUpdating = productRepo.findById(1).get();
				productForUpdating.setPrice(0.55f);
				productRepo.save(productForUpdating);
				//izveidot servisu
				
				
				MyAuthority a1 = new MyAuthority("ADMIN");
				authRepo.save(a1);
				
				MyAuthority a2 = new MyAuthority("USER");
				authRepo.save(a2);
				
				PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

				MyUser u1 = new MyUser("karina.krinkele", encoder.encode("123"), a2);
				userRepo.save(u1);
				MyUser u2 = new MyUser("janis.berzins", encoder.encode("321"), a1);
				userRepo.save(u2);
				MyUser u3 = new MyUser("liga.jauka", encoder.encode("987"), a2);
				userRepo.save(u3);
				
				
				
				
			}
		};
	}

}
