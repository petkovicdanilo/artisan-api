package rs.ac.ni.pmf.web.model.api;

import io.swagger.annotations.ApiModel;
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
	private String username;
	private boolean isAdmin;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
}
