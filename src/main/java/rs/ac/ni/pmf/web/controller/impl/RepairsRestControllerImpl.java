package rs.ac.ni.pmf.web.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.RepairsRestController;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.RepairsSearchOptions;
import rs.ac.ni.pmf.web.model.api.RepairDTO;
import rs.ac.ni.pmf.web.service.RepairsService;

@RestController
@RequiredArgsConstructor
@Api(tags = "Repairs")
public class RepairsRestControllerImpl implements RepairsRestController {

	private final RepairsService repairsService;
	
	@Override
	public Page<RepairDTO> getRepairs(RepairsSearchOptions searchOptions) {
		return repairsService.getAll(searchOptions);
	}

	@Override
	public RepairDTO getRepair(int id) throws ResourceNotFoundException {
		return repairsService.getOne(id);
	}

	@Override
	public Page<RepairDTO> getRepairsByClientId(
		int clientId,
		RepairsSearchOptions searchOptions
	) 
		throws ResourceNotFoundException {
		
		return repairsService.getAllByClientId(clientId, searchOptions);
	}
	
	@Override
	public Page<RepairDTO> getRepairsByAssigneeUsername(
		String assigneeUsername,
		RepairsSearchOptions searchOptions
	)
		throws ResourceNotFoundException {
		
		return repairsService.getAllByAssigneeUsername(assigneeUsername, searchOptions);
	}

	@Override
	public RepairDTO saveRepair(RepairDTO repair) throws ResourceNotFoundException {
		return repairsService.save(repair);
	}

	@Override
	public RepairDTO updateRepair(int id, RepairDTO repair) throws ResourceNotFoundException {
		return repairsService.update(id, repair);
	}

	@Override
	public void deleteRepair(int id) throws ResourceNotFoundException {
		repairsService.delete(id);
	}

	@Override
	public double getRepairPrice(int id) throws ResourceNotFoundException {
		return repairsService.getPrice(id);
	}

}
