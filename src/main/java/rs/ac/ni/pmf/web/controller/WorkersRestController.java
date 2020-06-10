package rs.ac.ni.pmf.web.controller;

import java.util.List;

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

import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.WorkerDTO;

@RequestMapping(path = "/workers", produces = MediaType.APPLICATION_JSON_VALUE)
public interface WorkersRestController {

	@GetMapping(path = "")
	List<WorkerDTO> getWorkers();

	@GetMapping(path = "/{username}")
	WorkerDTO getWorker(@PathVariable(name = "username", required = true) String username)
		throws ResourceNotFoundException;

	@PostMapping(path = "")
	@ResponseStatus(code = HttpStatus.CREATED)
	WorkerDTO saveWorker(@RequestBody WorkerDTO worker) 
		throws DuplicateResourceException;
	
	@PutMapping(path = "/{username}")
	WorkerDTO updateWorker(
		@PathVariable(name = "username", required = true) String username,
		@RequestBody WorkerDTO worker
	)
		throws ResourceNotFoundException;
	
	@DeleteMapping(path = "/{username}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deleteWorker(@PathVariable(name = "username", required = true) String username)
		throws ResourceNotFoundException;
}
