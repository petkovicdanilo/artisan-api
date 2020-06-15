package rs.ac.ni.pmf.web.model.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

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
@ApiModel(description = "Data object representing repair")
public class PartDTO {
	
	@ApiModelProperty(value = "Unique identifier of part")
	private int id;
	
	@Size(max = 64)
	@NotNull @NotBlank
	@ApiModelProperty(value = "Name of part", example = "part1")
	private String name;
	
	@ApiModelProperty(value = "Is part used or new", example = "false")
	private boolean used;
}
