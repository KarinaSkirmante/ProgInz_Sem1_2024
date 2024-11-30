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
@Table(name = "GlobalParamsTable")
@Entity
public class GlobalParams {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idgp")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//autoincrement
	@JsonIgnore
	private int idgp;
	
	//TODO papildināt ar validāciju anotācijām
	@Column(name  = "ParamTitle", unique = true)
	private String paramTitle;
	
	//TODO papildināt ar validāciju anotācijām
	@Column(name = "ParamValue")
	private String paramValue;
	
	public GlobalParams(String paramTitle, String paramValue ) {
		setParamTitle(paramTitle);
		setParamValue(paramValue);
	}

}
