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

import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.RepairDTO;

@RequestMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
public interface RepairsRestController {

	@GetMapping(path = "/repairs")
	List<RepairDTO> getRepairs();
	
	@GetMapping(path = "/repairs/{id}")
	RepairDTO getRepair(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
	
	@GetMapping(path = "/clients/{clientId}/repairs")
	List<RepairDTO> getRepairsByClientId(
			@PathVariable(name = "clientId", required = true) int clientId)
		throws ResourceNotFoundException;
	
	@GetMapping(path = "/workers/{assigneeUsername}/repairs")
	List<RepairDTO> getRepairsByAssigneeUsername(
			@PathVariable(name = "assigneeUsername", required = true) String assigneeUsername)
		throws ResourceNotFoundException;
	
	@PostMapping(path = "/repairs")
	@ResponseStatus(code = HttpStatus.CREATED)
	RepairDTO saveRepair(@RequestBody RepairDTO repair) throws ResourceNotFoundException;
	
	@PutMapping(path = "/repairs/{id}")
	RepairDTO updateRepair(
			@PathVariable(name = "id", required = true) int id,
			@RequestBody RepairDTO repair
	)
		throws ResourceNotFoundException;
	
	@DeleteMapping(path = "/repairs/{id}")
	void deleteRepair(@PathVariable(name = "id", required = true) int id)
		throws ResourceNotFoundException;
	
}
