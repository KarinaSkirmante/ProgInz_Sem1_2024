package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsManager createDemoUsers() {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		
		UserDetails details1 = User.builder()
				.username("karina.krinkele")
				.password(encoder.encode("123"))
				.authorities("USER")
				.build();
		
		
		UserDetails details2 = User.builder()
				.username("janis.berzins")
				.password(encoder.encode("321"))
				.authorities("ADMIN")
				.build();
		
		UserDetails details3 = User.builder()
				.username("liga.jauka")
				.password(encoder.encode("987"))
				.authorities("USER")
				.build();
		
		return new InMemoryUserDetailsManager(details1, details2, details3);
		
		
	}
	

	@Bean
	public SecurityFilterChain configurePermissionToEndpoints(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/hello/**").permitAll() //hello un ari uz hello/msg
				.requestMatchers("/product/test/**").hasAuthority("ADMIN")
				.requestMatchers("/product/crud/all").permitAll()
				.requestMatchers("/product/crud/one?id=**").permitAll()
				.requestMatchers("/product/crud/all/**").permitAll()
				.requestMatchers("/product/crud/insert").hasAuthority("ADMIN")
				.requestMatchers("/product/crud/update/**").hasAuthority("ADMIN")
				.requestMatchers("/product/crud/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/product/filter/**").hasAnyAuthority("USER", "ADMIN")
				);
		
		http.formLogin(auth-> auth.permitAll());
		
		//http.formLogin().permitAll(); <- sādi stradās vecākajas Spring security versijās
		
		return http.build();
		
	}
	
}
