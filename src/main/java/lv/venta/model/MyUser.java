package lv.venta.model;

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
@Entity
@Table(name = "MyUser")
public class MyUser {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "UserId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//autoincrement
	private int userId;
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;

	public MyUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	

}
