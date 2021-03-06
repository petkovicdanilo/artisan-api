package rs.ac.ni.pmf.web.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "workers")
public class WorkerEntity {

	@Id
	@Column(length = 64)
	private String username;
	
	@Column(name = "password", columnDefinition = "varchar(255)", nullable = false)
	private String password;
	
	@Column(name = "is_admin", nullable = false)
	private boolean isAdmin;
	
	@Column(unique = true, length = 64, nullable = false)
	private String email;
	
	@Column(name = "first_name", length = 64, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 64, nullable = false)
	private String lastName;
	
	@Column(name = "phone_number", length = 64)
	private String phoneNumber;
	
	@Builder.Default
	@OneToMany(
		mappedBy = "assignee",
		fetch = FetchType.LAZY,
		cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST },
		orphanRemoval = false)
	private List<RepairEntity> assignedRepairs = new ArrayList<>();
	
	@PreRemove
	private void preRemove() {
		for(RepairEntity repair : assignedRepairs) {
			repair.setAssignee(null);
		}
	}
}
