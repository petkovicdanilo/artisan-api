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
public class PartsSearchOptions {
	
	@ApiModelProperty(value = "Return onlu used/not used parts")
	private Boolean used;
	
	@ApiModelProperty(value = "Page number")
	private Integer page;
	
	@ApiModelProperty(value = "Page size")
	private Integer pageSize;
}
