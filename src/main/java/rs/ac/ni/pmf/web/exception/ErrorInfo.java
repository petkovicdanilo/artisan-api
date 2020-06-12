package rs.ac.ni.pmf.web.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorInfo {

	private ErrorType errorType;
	private ResourceType resourceType;
	
	private String message;
	
	public enum ErrorType {
		NOT_FOUND,
		DUPLICATE
	}
	
	public enum ResourceType {
		CLIENT,
		PART,
		WORKER,
	}
}
