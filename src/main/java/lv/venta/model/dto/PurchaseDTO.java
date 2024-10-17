package lv.venta.model.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseDTO {
	private String customerName;
	private String customerSurname;
	
	private ArrayList<ProductDTOForBuy> products;
	

}
