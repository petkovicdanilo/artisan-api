package rs.ac.ni.pmf.web.model.api;


import java.util.Date;

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
	private String assigneeUsername;
	private Integer clientId;
	@JsonFormat(shape = Shape.STRING, pattern = "dd.MM.yyyy. HH:mm:ss")
	private Date reported;
	@JsonFormat(shape = Shape.STRING, pattern = "dd.MM.yyyy. HH:mm:ss")
	private Date finished;
}
