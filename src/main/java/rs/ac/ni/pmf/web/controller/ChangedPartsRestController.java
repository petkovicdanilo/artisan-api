package rs.ac.ni.pmf.web.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.ChangedPartsSearchOptions;
import rs.ac.ni.pmf.web.model.api.ChangedPartDTO;

@RequestMapping(path = "/repairs/{repairId}/changedParts", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ChangedPartsRestController {

	@GetMapping(path = "")
	@ApiOperation(value = "Get paged list of all changed parts for repair with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful"),
		@ApiResponse(code = 404, message = "Repair not found")
	})
	Page<ChangedPartDTO> getChangedParts(
		@PathVariable(name = "repairId", required = true) int repairId,
		ChangedPartsSearchOptions searchOptions
	)
		throws ResourceNotFoundException;
	
	@GetMapping(path = "/{partId}")
	@ApiOperation(value = "Find changed part with given id used in repair with 'repairId'")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful"),
		@ApiResponse(code = 404, message = "Repair or changed part not found")
	})
	ChangedPartDTO getChangedPart(
		@PathVariable(name = "repairId", required = true) int repairId,
		@PathVariable(name = "partId", required = true) int partId
	)
		throws ResourceNotFoundException;;
	
	@PostMapping(path = "")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Add changed part to repair with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Changed part is created"),
		@ApiResponse(code = 400, message = "Validation error or wrong ids are sent"),
		@ApiResponse(code = 404, message = "Repair not found")
	})
	ChangedPartDTO saveChangedPart(
		@PathVariable(name = "repairId", required = true) int repairId,
		@RequestBody @Valid ChangedPartDTO changedPart
	)
		throws BadRequestException, DuplicateResourceException, ResourceNotFoundException;
	
	@PutMapping(path = "/{partId}")
	@ApiOperation(value = "Update changed part with given id for repair 'repairId'")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Changed part is updated"),
		@ApiResponse(code = 400, message = "Validation error or wrong ids are sent"),
		@ApiResponse(code = 404, message = "Repair or changed part not found")
	})
	ChangedPartDTO updateChangedPart(
		@PathVariable(name = "repairId", required = true) int repairId,
		@RequestBody @Valid ChangedPartDTO changedPart
	)
		throws BadRequestException, DuplicateResourceException, ResourceNotFoundException;
	
	@DeleteMapping(path = "/{partId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove part with given id from repair 'repairId'")
	@ApiResponses(value = {
		@ApiResponse(code = 204, message = "Part is removed from repair"),
		@ApiResponse(code = 404, message = "Repair or changed part not found")
	})
	void deleteChangedPart(
		@PathVariable(name = "repairId", required = true) int repairId,
		@PathVariable(name = "partId", required = true) int partId
	)
		throws ResourceNotFoundException;
}
