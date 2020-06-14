package rs.ac.ni.pmf.web.model;

import org.springframework.data.domain.Sort.Direction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangedPartsSearchOptions {
	private Integer minCount;
	private Integer maxCount;
	
	private Double minPrice;
	private Double maxPrice;
	
	private SortByField sortBy;
	private Direction sortDirection;

	private Integer page;
	private Integer pageSize;
	
	public enum SortByField {
		count,
		price
	}
}

