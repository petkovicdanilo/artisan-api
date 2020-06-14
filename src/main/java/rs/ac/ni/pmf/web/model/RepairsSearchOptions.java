package rs.ac.ni.pmf.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairsSearchOptions {
	private Double minAdditionalCost;
	private Double maxAdditionalCost;
	
	private Integer page;
	private Integer pageSize;
}
