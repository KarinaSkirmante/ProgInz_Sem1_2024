package lv.venta.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
//@AllArgsConstructor
@Table(name = "PurchaseTable")
@Entity
public class Purchase {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//autoincrement
	private int id;
	
	@Column(name = "CustomerName")
	private String customerName;
	
	@Column(name = "CustomerSurname")
	private String customerSurname;
	
	@CurrentTimestamp
	@Column(name = "PurchaseDatetime")
	private LocalDateTime purchaseDatetime;
	
	@Column(name = "TotalSum")
	private float totalSum;
	
	public Purchase(String name, String surname, float totalSum) {
		setCustomerName(name);
		setCustomerSurname(surname);
		setTotalSum(totalSum);
	}
	
	
	
	
	
	
	
}
