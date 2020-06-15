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
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.PartsSearchOptions;
import rs.ac.ni.pmf.web.model.api.PartDTO;

@RequestMapping(path = "/parts", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PartsRestController {
	
	@GetMapping(path = "")
	@ApiOperation(value = "Get paged list of all parts")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful")
	})
	Page<PartDTO> getParts(PartsSearchOptions searchOptions);
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Find part with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful"),
		@ApiResponse(code = 404, message = "Part not found")
	})
	PartDTO getPart(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
	
	@PostMapping(path = "")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create new part")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Part is created"),
		@ApiResponse(code = 400, message = "Validation error")
	})
	PartDTO savePart(@RequestBody @Valid PartDTO part);
	
	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Update part with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Part is updated"),
		@ApiResponse(code = 400, message = "Validation error"),
		@ApiResponse(code = 404, message = "Part not found")
	})
	PartDTO updatePart(
		@PathVariable(name = "id", required = true) int id,
		@RequestBody @Valid PartDTO part
	)
		throws ResourceNotFoundException;
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete part with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 204, message = "Part is deleted"),
		@ApiResponse(code = 404, message = "Part not found")
	})
	void deletePart(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
}
