package lv.venta.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentStatusDTO {
	private String paymentIntentId;
	private String paymentStatus;
	
}
