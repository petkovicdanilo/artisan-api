package rs.ac.ni.pmf.web.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorInfo {

	private ErrorType errorType;
	private ResourceType resourceType;
	
	private String message;
	
	@Builder.Default
	private Map<String, String> errorDetails = new HashMap<>();
	
	public enum ErrorType {
		AUTHENTICATION_ERROR,
		BAD_REQUEST,
		DUPLICATE,
		NOT_FOUND,
		UNAUTHORIZED,
		VALIDATION_ERROR,
	}
	
	public enum ResourceType {
		ACCESS,
		CHANGED_PART,
		CLIENT,
		PART,
		REPAIR,
		SENT_RESOURCE,
		WORKER,
	}
}
