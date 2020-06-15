package rs.ac.ni.pmf.web.model.api;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@ApiModel(description = "Data object representing information about part changed in repair")
public class ChangedPartDTO {

	@NotNull
	@ApiModelProperty(value = "Unique identifier of part", required = true)
	private Integer partId;
	
	@NotNull
	@ApiModelProperty(value = "Unique identifier of repair", required = true)
	private Integer repairId;
	
	@Min(value = 1)
	@ApiModelProperty(value = "How many of parts were used", example = "1")
	private int count;
	
	@Min(value = 0)
	@ApiModelProperty(value = "Cost of replacing 'count' parts in repair", example = "100.0")
	private double price;
}
