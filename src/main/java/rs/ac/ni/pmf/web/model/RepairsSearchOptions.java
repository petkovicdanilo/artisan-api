package rs.ac.ni.pmf.web.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairsSearchOptions {
	
	@ApiModelProperty(
		value = "Return only repairs that have at least this much additional cost")
	private Double minAdditionalCost;
	
	@ApiModelProperty(
		value = "Return only repairs that have at most this much additional cost")
	private Double maxAdditionalCost;
	
	
	@ApiModelProperty(value = "Page number")
	private Integer page;
	
	@ApiModelProperty(value = "Page size")
	private Integer pageSize;
}
