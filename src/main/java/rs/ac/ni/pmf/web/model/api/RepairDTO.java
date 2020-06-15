package rs.ac.ni.pmf.web.model.api;


import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
public class RepairDTO {
	
	@ApiModelProperty(value = "Unique identifier of repair")
	private Integer id;
	
	@ApiModelProperty(value = "Short description of failure", example = "Short description")
	private String failureDescription;
	
	@Min(value = 0)
	@ApiModelProperty(
		value = "Additional cost not associated with replacing parts",
		example = "1000.0")
	private double additionalCost;
	
	@ApiModelProperty(
		value = "Worker whot is assigned for this repair", 
		example = "worker1")
	private String assigneeUsername;
	
	@NotNull
	@ApiModelProperty(value = "Id of client with failing system")
	private Integer clientId;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd.MM.yyyy. HH:mm:ss")
	@NotNull
	@Past
	@ApiModelProperty(
		value = "Date and time when failure is reported. Must be in past",
		example = "15.06.2020. 18:45:00"
	)
	private Date reported;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd.MM.yyyy. HH:mm:ss")
	@Past
	@ApiModelProperty(
		value = "Date and time when failure is reported. Must be in past",
		example = "15.06.2020. 18:45:00"
	)
	private Date finished;
}
