package rs.ac.ni.pmf.web.model.api;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class ChangedPartDTO {

	@NotNull
	private Integer partId;
	
	@NotNull
	private Integer repairId;
	
	@Min(value = 1)
	private int count;
	
	@Min(value = 0)
	private double price;
}
