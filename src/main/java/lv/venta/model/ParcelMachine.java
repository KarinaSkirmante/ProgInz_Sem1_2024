package lv.venta.model;

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
@Table(name = "ParcelMachineTable")
@Entity

public class ParcelMachine {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idpm")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//autoincrement
	private int idpm;
	
	
	//TODO pielikt klāt validāciju anotācijas
	@Column(name = "Zip")
	private String zip;
	@Column(name = "Name")
	private String name;
	@Column(name = "Type")
	private int type;
	@Column(name = "Country")
	private String country;
	
	@Column(name = "Address")
	private String address;

	public ParcelMachine(String zip, String name, int type, String country, String address) {
		super();
		this.zip = zip;
		this.name = name;
		this.type = type;
		this.country = country;
		this.address = address;
	}
	
	
	
}
