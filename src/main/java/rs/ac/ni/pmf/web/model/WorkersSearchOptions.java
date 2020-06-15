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
public class WorkersSearchOptions {
	
	@ApiModelProperty(value = "Filter usernames")
	private String username;
	
	@ApiModelProperty(value = "Return only workers that are/aren't admins")
	private Boolean isAdmin;
	
	@ApiModelProperty(value = "Filter emails")
	private String email;
	
	@ApiModelProperty(value = "Filter first names")
	private String firstName;
	
	@ApiModelProperty(value = "Filter last names")
	private String lastName;
	
	@ApiModelProperty(value = "Filter phone numbers")
	private String phoneNumber;
	
	
	@ApiModelProperty(
		value = "Return only workers that are assigned to at least this many repairs")
	private Integer minRepairs;
	
	@ApiModelProperty(
		value = "Return only workers that are assigned to at most this many repairs")
	private Integer maxRepairs;
	
	
	@ApiModelProperty(value = "Page number")
	private Integer page;
	
	@ApiModelProperty(value = "Page size")
	private Integer pageSize;
}
