package rs.ac.ni.pmf.web.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "clients")
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name", length = 64, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 64, nullable = false)
	private String lastName;
	
	@Column(length = 64)
	private String address;
	
	@Column(name = "phone_number", length = 64)
	private String phoneNumber;
	
	@Builder.Default
	@OneToMany(
		mappedBy = "client", 
		fetch = FetchType.LAZY,
		cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST },
		orphanRemoval = false)
	private List<RepairEntity> repairs = new ArrayList<>();
	
	@PreRemove
	private void preRemove() {
		for(RepairEntity repair : repairs) {
			repair.setClient(null);
		}
	}
}

