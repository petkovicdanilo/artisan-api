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
import rs.ac.ni.pmf.web.model.RepairsSearchOptions;
import rs.ac.ni.pmf.web.model.api.RepairDTO;

@RequestMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
public interface RepairsRestController {

	@GetMapping(path = "/repairs")
	@ApiOperation(value = "Get paged list of all repairs")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful")
	})
	Page<RepairDTO> getRepairs(RepairsSearchOptions searchOptions);
	
	@GetMapping(path = "/repairs/{id}")
	@ApiOperation(value = "Find repair with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful"),
		@ApiResponse(code = 404, message = "Repair not found")
	})
	RepairDTO getRepair(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
	
	@GetMapping(path = "/clients/{clientId}/repairs")
	@ApiOperation(value = "Get paged list of all repairs for client with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful"),
		@ApiResponse(code = 404, message = "Client not found")
	})
	Page<RepairDTO> getRepairsByClientId(
			@PathVariable(name = "clientId", required = true) int clientId,
			RepairsSearchOptions searchOptions)
		throws ResourceNotFoundException;
	
	@GetMapping(path = "/workers/{assigneeUsername}/repairs")
	@ApiOperation(
		value = "Get paged list of all repairs that are assigned to worker with given username")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Request is successful"),
		@ApiResponse(code = 404, message = "Worker not found")
	})
	Page<RepairDTO> getRepairsByAssigneeUsername(
			@PathVariable(name = "assigneeUsername", required = true) String assigneeUsername,
			RepairsSearchOptions searchOptions)
		throws ResourceNotFoundException;
	
	@PostMapping(path = "/repairs")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create new repair")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Repair is created"),
		@ApiResponse(code = 400, message = "Validation error or client/worker not found")
	})
	RepairDTO saveRepair(@RequestBody @Valid RepairDTO repair) throws ResourceNotFoundException;
	
	@PutMapping(path = "/repairs/{id}")
	@ApiOperation(value = "Update repair with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Repair is updated"),
		@ApiResponse(code = 400, message = "Validation error or client/worker not found"),
		@ApiResponse(code = 404, message = "Repair not found")
	})
	RepairDTO updateRepair(
			@PathVariable(name = "id", required = true) int id,
			@RequestBody @Valid RepairDTO repair
	)
		throws ResourceNotFoundException;
	
	@DeleteMapping(path = "/repairs/{id}")
	@ApiOperation(value = "Delete repair with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 204, message = "Repair is deleted"),
		@ApiResponse(code = 404, message = "Repair not found")
	})
	void deleteRepair(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
	
	@GetMapping(path = "/repairs/{id}/price")
	@ApiOperation(value = "Get total price for repair with given id")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Request is successful"),
		@ApiResponse(code = 404, message = "Repair not found")
	})
	double getRepairPrice(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
	
}
