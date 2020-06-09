package rs.ac.ni.pmf.web.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(name = "part_repair")
public class PartRepairEntity {

	@EmbeddedId
	private PartRepairId partRepairId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "part_id")
	@MapsId("partId")
	private PartEntity part;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "repair_id")
	@MapsId("repairId")
	private RepairEntity repair;
	
	private int count;
	
	private double price;
	
	@Embeddable
	@Data
	@AllArgsConstructor
	public class PartRepairId implements Serializable {
		private static final long serialVersionUID = 5284038231748169558L;
		
		@Column(name = "part_id")
		private int partId;
		
		@Column(name = "repair_id")
		private int repairId;
	}
}
