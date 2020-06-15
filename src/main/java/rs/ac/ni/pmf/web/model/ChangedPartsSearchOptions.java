package rs.ac.ni.pmf.web.model;

import org.springframework.data.domain.Sort.Direction;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangedPartsSearchOptions {
	
	@ApiModelProperty(
		value = "Return only changed parts that have at least this many parts included")
	private Integer minCount;
	
	@ApiModelProperty(
		value = "Return only changed parts that have at most this many parts included")
	private Integer maxCount;
	
	
	@ApiModelProperty(
		value = "Return only changed parts that cost at least this much")
	private Double minPrice;
	
	@ApiModelProperty(
		value = "Return only changed parts that cost at most this much")
	private Double maxPrice;
	
	
	@ApiModelProperty(value = "Sort results by property")
	private SortByField sortBy;
	
	@ApiModelProperty(value = "Sort results in this direction")
	private Direction sortDirection;

	
	@ApiModelProperty(value = "Page number")
	private Integer page;
	
	@ApiModelProperty(value = "Page size")
	private Integer pageSize;
	
	
	public enum SortByField {
		count,
		price
	}
}

