package rs.ac.ni.pmf.web.model.api;

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
@ApiModel(description = "Data object representing client")
public class ClientDTO {
	
	@ApiModelProperty(name = "Unique identifier of client")
	private Integer id;
	
	@Size(max = 64)
	@NotNull @NotBlank
	@ApiModelProperty(name = "Client's first name", example = "John")
	private String firstName;
	
	@Size(max = 64)
	@NotNull @NotBlank
	@ApiModelProperty(name = "Client's last name", example = "Doe")
	private String lastName;
	
	@Size(max = 64)
	@ApiModelProperty(name = "Client's address", example = "123 Main St")
	private String address;
	
	@Pattern(regexp = "^\\+?[0-9]+[0-9\\-]*$")
	@Size(max = 64)
	@ApiModelProperty(name = "Client's phone number", example = "+381-18-123-456")
	private String phoneNumber;
}
