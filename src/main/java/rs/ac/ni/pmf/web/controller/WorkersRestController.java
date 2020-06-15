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
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.WorkersSearchOptions;
import rs.ac.ni.pmf.web.model.api.WorkerDTO;
import rs.ac.ni.pmf.web.model.api.WorkerSaveDTO;

@RequestMapping(path = "/workers", produces = MediaType.APPLICATION_JSON_VALUE)
public interface WorkersRestController {

	@GetMapping(path = "")
	@ApiOperation(value = "Get paged list of all workers")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful")
	})
	Page<WorkerDTO> getWorkers(WorkersSearchOptions searchOptions);

	@GetMapping(path = "/{username}")
	@ApiOperation(value = "Find worker with given username")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful"),
		@ApiResponse(code = 404, message = "Worker not found")
	})
	WorkerDTO getWorker(@PathVariable(name = "username", required = true) String username)
		throws ResourceNotFoundException;

	@PostMapping(path = "")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create new worker")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Worker is created"),
		@ApiResponse(code = 400, message = "Validation error or username is taken"),
		@ApiResponse(code = 403, message = "Access forbidden")
	})
	WorkerDTO saveWorker(@RequestBody @Valid WorkerSaveDTO worker) 
		throws DuplicateResourceException;
	
	@PutMapping(path = "/{username}")
	@ApiOperation(value = "Update worker with given username")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Worker is updated"),
		@ApiResponse(code = 403, message = "Access forbidden"),
		@ApiResponse(code = 404, message = "Worker not found")
	})
	WorkerDTO updateWorker(
		@PathVariable(name = "username", required = true) String username,
		@RequestBody @Valid WorkerSaveDTO worker
	)
		throws ResourceNotFoundException;
	
	@DeleteMapping(path = "/{username}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete worker with given username")
	@ApiResponses(value = {
		@ApiResponse(code = 204, message = "Worker is deleted"),
		@ApiResponse(code = 403, message = "Access forbidden"),
		@ApiResponse(code = 404, message = "Worker not found")
	})
	void deleteWorker(@PathVariable(name = "username", required = true) String username)
		throws ResourceNotFoundException;
}
