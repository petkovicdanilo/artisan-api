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

import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.ChangedPartsSearchOptions;
import rs.ac.ni.pmf.web.model.api.ChangedPartDTO;

@RequestMapping(path = "/repairs/{repairId}/changedParts", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ChangedPartsRestController {

	@GetMapping(path = "")
	Page<ChangedPartDTO> getChangedParts(
		@PathVariable(name = "repairId", required = true) int repairId,
		ChangedPartsSearchOptions searchOptions
	)
		throws ResourceNotFoundException;
	
	@GetMapping(path = "/{partId}")
	ChangedPartDTO getChangedPart(
		@PathVariable(name = "repairId", required = true) int repairId,
		@PathVariable(name = "partId", required = true) int partId
	)
		throws ResourceNotFoundException;;
	
	@PostMapping(path = "")
	@ResponseStatus(code = HttpStatus.CREATED)
	ChangedPartDTO saveChangedPart(
		@PathVariable(name = "repairId", required = true) int repairId,
		@RequestBody @Valid ChangedPartDTO changedPart
	)
		throws BadRequestException, DuplicateResourceException, ResourceNotFoundException;
	
	@PutMapping(path = "/{partId}")
	ChangedPartDTO updateChangedPart(
		@PathVariable(name = "repairId", required = true) int repairId,
		@RequestBody @Valid ChangedPartDTO changedPart
	)
		throws BadRequestException, DuplicateResourceException, ResourceNotFoundException;
	
	@DeleteMapping(path = "/{partId}")
	void deleteChangedPart(
		@PathVariable(name = "repairId", required = true) int repairId,
		@PathVariable(name = "partId", required = true) int partId
	)
		throws ResourceNotFoundException;
}
