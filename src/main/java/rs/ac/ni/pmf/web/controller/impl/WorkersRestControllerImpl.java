package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.WorkersRestController;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.WorkerDTO;
import rs.ac.ni.pmf.web.service.WorkersService;

@RestController
@RequiredArgsConstructor
@Api(tags = "Workers")
public class WorkersRestControllerImpl implements WorkersRestController {

	private final WorkersService workersService;

	@Override
	public List<WorkerDTO> getWorkers() {
		return workersService.getAll();
	}

	@Override
	public WorkerDTO getWorker(String username) throws ResourceNotFoundException {
		return workersService.getOne(username);
	}

	@Override
	public WorkerDTO saveWorker(WorkerDTO worker) throws DuplicateResourceException {
		return workersService.save(worker);
	}

	@Override
	public WorkerDTO updateWorker(String username, WorkerDTO worker) throws ResourceNotFoundException {
		return workersService.update(username, worker);
	}

	@Override
	public void deleteWorker(String username) throws ResourceNotFoundException {
		workersService.delete(username);
	}
}
