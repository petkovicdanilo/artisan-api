package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "clients")
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "first_name", length = 32, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 32, nullable = false)
	private String lastName;
	
	@Column(length = 64)
	private String address;
	
	@Column(name = "phone_number", length = 12)
	private String phoneNumber;
}

