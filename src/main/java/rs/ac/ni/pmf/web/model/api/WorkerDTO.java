package rs.ac.ni.pmf.web.model.api;

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
@ApiModel(description = "Data object representing worker")
public class WorkerDTO {
	
	@ApiModelProperty(value = "Username of worker. Must be unique.", example = "worker1")
	private String username;
	
	@ApiModelProperty(value = "Is worker admin", example = "false")
	private boolean isAdmin;
	
	@ApiModelProperty(
		value = "Worker's email. Must be unique.", 
		example = "worker1@artisan-api.com")
	private String email;
	
	@ApiModelProperty(value = "Worker's first name", example = "John")
	private String firstName;
	
	@ApiModelProperty(value = "Worker's last name", example = "Doe")
	private String lastName;
	
	@ApiModelProperty(name = "Worker's phone number", example = "+381-18-123-456")
	private String phoneNumber;
}
