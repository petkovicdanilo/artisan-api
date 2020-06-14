package rs.ac.ni.pmf.web.model.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "repairs")
public class RepairEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "failure_description", length = 64)
	private String failureDescription;
	
	@Column(name = "additional_cost", nullable = false)
	private double additionalCost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_username", nullable = true)
	private WorkerEntity assignee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = true)
	private ClientEntity client;
	
	@Column(nullable = false)
	private Timestamp reported;
	
	private Timestamp finished;
	
	@Builder.Default
	@OneToMany(
		mappedBy = "repair", 
		fetch = FetchType.LAZY,
		cascade = CascadeType.ALL,
		orphanRemoval = true)
	private List<ChangedPartEntity> changedPartsEntities = new ArrayList<>();
}
