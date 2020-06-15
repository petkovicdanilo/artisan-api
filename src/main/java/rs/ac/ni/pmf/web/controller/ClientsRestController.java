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
import rs.ac.ni.pmf.web.model.ClientsSearchOptions;
import rs.ac.ni.pmf.web.model.api.ClientDTO;

@RequestMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ClientsRestController {

	@GetMapping(path = "")
	@ApiOperation(value = "Get paged list of all clients")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful")
	})
	Page<ClientDTO> getClients(ClientsSearchOptions searchOptions);
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Find client with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful"),
		@ApiResponse(code = 404, message = "Client not found")
	})
	ClientDTO getClient(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
	
	@PostMapping(path = "")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create new client")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Client is created"),
		@ApiResponse(code = 400, message = "Validation error")
	})
	ClientDTO saveClient(@RequestBody @Valid ClientDTO client);
	
	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Update client with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Client is updated"),
		@ApiResponse(code = 404, message = "Client not found")
	})
	ClientDTO updateClient(
		@PathVariable(name = "id", required = true) int id,
		@RequestBody @Valid ClientDTO client
	)
		throws ResourceNotFoundException;
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete client with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 204, message = "Client is deleted"),
		@ApiResponse(code = 404, message = "Client not found")
	})
	void deleteClient(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
}
