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

import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.PartsSearchOptions;
import rs.ac.ni.pmf.web.model.api.PartDTO;

@RequestMapping(path = "/parts", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PartsRestController {
	
	@GetMapping(path = "")
	Page<PartDTO> getParts(PartsSearchOptions searchOptions);
	
	@GetMapping(path = "/{id}")
	PartDTO getPart(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
	
	@PostMapping(path = "")
	@ResponseStatus(code = HttpStatus.CREATED)
	PartDTO savePart(@RequestBody @Valid PartDTO part);
	
	@PutMapping(path = "/{id}")
	PartDTO updatePart(
		@PathVariable(name = "id", required = true) int id,
		@RequestBody @Valid PartDTO part
	)
		throws ResourceNotFoundException;
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletePart(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
}
