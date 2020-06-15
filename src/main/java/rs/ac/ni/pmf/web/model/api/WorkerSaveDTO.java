package rs.ac.ni.pmf.web.model.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@ApiModel(description = "Data object for saving/updating worker")
public class WorkerSaveDTO {
	
	@Pattern(regexp = "^[a-zA-Z0-9._-]*$")
	@Size(max = 64)
	@NotNull @NotBlank
	@ApiModelProperty(value = "Username of worker. Must be unique.", example = "worker1")
	private String username;
	
	@Size(min = 5, max = 64)
	@NotNull @NotBlank
	@ApiModelProperty(value = "Worker's password. Must not be empty", example = "password")
	private String password;
	
	@ApiModelProperty(value = "Is worker admin", example = "false")
	private boolean isAdmin;
	
	@Email
	@Size(max = 64)
	@NotNull @NotBlank
	@ApiModelProperty(
		value = "Worker's email. Must be unique.", 
		example = "worker1@artisan-api.com")
	private String email;
	
	@Size(max = 64)
	@NotNull @NotBlank
	@ApiModelProperty(value = "Worker's first name", example = "John")
	private String firstName;
	
	@Size(max = 64)
	@NotNull @NotBlank
	@ApiModelProperty(value = "Worker's last name", example = "Doe")
	private String lastName;
	
	@Pattern(regexp = "^\\+?[0-9]+[0-9\\-]*$")
	@Size(max = 64)
	@ApiModelProperty(name = "Worker's phone number", example = "+381-18-123-456")
	private String phoneNumber;
	
}
