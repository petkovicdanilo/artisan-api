package rs.ac.ni.pmf.web.model.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class ClientDTO {
	
	private Integer id;
	
	@Size(max = 64)
	@NotNull @NotBlank
	private String firstName;
	
	@Size(max = 64)
	@NotNull @NotBlank
	private String lastName;
	
	@Size(max = 64)
	private String address;
	
	@Size(max = 64)
	private String phoneNumber;
}
