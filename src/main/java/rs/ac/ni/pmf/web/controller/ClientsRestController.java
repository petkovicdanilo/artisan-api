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
import rs.ac.ni.pmf.web.model.ClientsSearchOptions;
import rs.ac.ni.pmf.web.model.api.ClientDTO;

@RequestMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ClientsRestController {

	@GetMapping(path = "")
	Page<ClientDTO> getClients(ClientsSearchOptions searchOptions);
	
	@GetMapping(path = "/{id}")
	ClientDTO getClient(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
	
	@PostMapping(path = "")
	@ResponseStatus(code = HttpStatus.CREATED)
	ClientDTO saveClient(@RequestBody @Valid ClientDTO client);
	
	@PutMapping(path = "/{id}")
	ClientDTO updateClient(
		@PathVariable(name = "id", required = true) int id,
		@RequestBody @Valid ClientDTO client
	)
		throws ResourceNotFoundException;
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deleteClient(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
}
