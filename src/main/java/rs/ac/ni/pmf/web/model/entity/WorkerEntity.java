package rs.ac.ni.pmf.web.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "workers")
public class WorkerEntity {

	@Id
	@Column(length = 16)
	private String username;
	
	@Column(unique = true, length = 32, nullable = false)
	private String email;
	
	@Column(name = "first_name", length = 32, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 32, nullable = false)
	private String lastName;
	
	@Column(name = "phone_number", length = 15)
	private String phoneNumber;
	
	@Builder.Default
	@OneToMany(mappedBy = "assignee", fetch = FetchType.LAZY)
	private List<RepairEntity> assignedRepairs = new ArrayList<>();
}
