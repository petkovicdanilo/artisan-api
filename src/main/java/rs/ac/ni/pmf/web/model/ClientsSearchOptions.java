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
public class ClientsSearchOptions {
	
	@ApiModelProperty(value = "Filter address")
	private String address;
	
	@ApiModelProperty(value = "Filter first names")
	private String firstName;
	
	@ApiModelProperty(value = "Filter last names")
	private String lastName;
	
	@ApiModelProperty(value = "Filter phone number")
	private String phoneNumber;
	
	
	@ApiModelProperty(
		value = "Return only clients that have at least this many repairs")
	private Integer minRepairs;
	
	@ApiModelProperty(
		value = "Return only clients that are have at least this many repairs")
	private Integer maxRepairs;
	
	
	@ApiModelProperty(value = "Page number")
	private Integer page;
	
	@ApiModelProperty(value = "Page size")
	private Integer pageSize;
}
