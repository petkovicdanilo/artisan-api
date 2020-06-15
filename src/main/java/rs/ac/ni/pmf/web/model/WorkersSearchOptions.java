package rs.ac.ni.pmf.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkersSearchOptions {
	private String username;
	private Boolean isAdmin;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	private Integer minRepairs;
	private Integer maxRepairs;
	
	private Integer page;
	private Integer pageSize;
}
