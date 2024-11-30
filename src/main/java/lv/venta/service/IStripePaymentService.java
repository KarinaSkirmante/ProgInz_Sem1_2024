package lv.venta.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import lv.venta.model.dto.PaymentInitDTO;
import lv.venta.model.dto.PaymentStatusDTO;

public interface IStripePaymentService {
	
	PaymentIntent createPayment(PaymentInitDTO paymentInitDTO) throws StripeException;
	void savePaymentStatus(PaymentStatusDTO status);

}
