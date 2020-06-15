package rs.ac.ni.pmf.web.model.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class PartDTO {
	
	private int id;
	
	@Size(max = 64)
	@NotNull @NotBlank
	private String name;
	
	private boolean used;
}
