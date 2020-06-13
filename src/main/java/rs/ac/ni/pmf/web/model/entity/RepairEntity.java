package rs.ac.ni.pmf.web.model.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_username")
	private WorkerEntity assignee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	private ClientEntity client;
	
	@Column(nullable = false)
	private Timestamp reported;
	
	private Timestamp finished;
	
	@Builder.Default
	@OneToMany(mappedBy = "repair", fetch = FetchType.LAZY)
	private List<ChangedPartEntity> partRepairEntities = new ArrayList<>();
}
