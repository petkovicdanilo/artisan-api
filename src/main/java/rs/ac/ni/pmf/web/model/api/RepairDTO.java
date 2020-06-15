package rs.ac.ni.pmf.web.model.api;


import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class RepairDTO {
	
	private Integer id;
	
	private String failureDescription;
	
	@Min(value = 0)
	private double additionalCost;
	
	private String assigneeUsername;
	
	@NotNull
	private Integer clientId;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd.MM.yyyy. HH:mm:ss")
	@NotNull
	@Past
	private Date reported;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd.MM.yyyy. HH:mm:ss")
	@Past
	private Date finished;
}
