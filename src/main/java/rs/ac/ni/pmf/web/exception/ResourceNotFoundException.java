package rs.ac.ni.pmf.web.exception;

import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;

public class ResourceNotFoundException extends ResourceException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(ResourceType resourceType, String message) {
		super(resourceType, message);
	}
	
	public ResourceNotFoundException(ResourceType resourceType) {
		this(resourceType, "Resource '" + resourceType.toString() + "' not found");
	}
}
