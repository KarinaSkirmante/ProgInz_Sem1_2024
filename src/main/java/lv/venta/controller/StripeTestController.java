package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import lv.venta.model.dto.ClientSecret;
import lv.venta.model.dto.PaymentInitDTO;
import lv.venta.model.dto.PaymentStatusDTO;
import lv.venta.service.IStripePaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class StripeTestController {
	
	
	@Autowired
	private IStripePaymentService stripeService;
	
	@PostMapping("/create")
	public ResponseEntity<?> postMethodName(@RequestBody PaymentInitDTO paymentInitDTO) {
		System.out.println(paymentInitDTO);
		PaymentIntent pI;
		try {
			pI = stripeService.createPayment(paymentInitDTO);
			System.out.println(pI);
			ClientSecret cs = new ClientSecret(pI.getClientSecret());
			return new ResponseEntity<ClientSecret>(cs, HttpStatus.OK);
		} catch (StripeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	//TODO pabeigt pēc vajadzības
	@PostMapping("/confirm")
	public ResponseEntity<?> postConfirm(@RequestBody PaymentStatusDTO paymentStatusDTO){
		System.out.println(paymentStatusDTO);
		stripeService.savePaymentStatus(paymentStatusDTO);
		return new ResponseEntity<>("Info saved", HttpStatus.OK);
	}
	

}
