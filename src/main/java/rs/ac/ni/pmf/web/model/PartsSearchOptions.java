package rs.ac.ni.pmf.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartsSearchOptions {
	private Boolean used;
	
	private Integer page;
	private Integer pageSize;
}
