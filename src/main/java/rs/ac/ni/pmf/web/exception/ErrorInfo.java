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
		BAD_REQUEST,
		DUPLICATE,
		NOT_FOUND,
	}
	
	public enum ResourceType {
		CHANGED_PART,
		CLIENT,
		PART,
		REPAIR,
		WORKER,
	}
}
