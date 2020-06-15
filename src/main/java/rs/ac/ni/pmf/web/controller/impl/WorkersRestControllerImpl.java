package rs.ac.ni.pmf.web.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.WorkersRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.WorkersSearchOptions;
import rs.ac.ni.pmf.web.model.api.WorkerDTO;
import rs.ac.ni.pmf.web.model.api.WorkerSaveDTO;
import rs.ac.ni.pmf.web.service.WorkersService;

@RestController
@RequiredArgsConstructor
@Api(tags = "Workers")
public class WorkersRestControllerImpl implements WorkersRestController {

	private final WorkersService workersService;

	@Override
	public Page<WorkerDTO> getWorkers(WorkersSearchOptions searchOptions) {
		return workersService.getAll(searchOptions);
	}

	@Override
	public WorkerDTO getWorker(String username) throws ResourceNotFoundException {
		return workersService.getOne(username);
	}

	@Override
	public WorkerDTO saveWorker(WorkerSaveDTO worker) throws BadRequestException, DuplicateResourceException {
		return workersService.save(worker);
	}

	@Override
	public WorkerDTO updateWorker(String username, WorkerSaveDTO worker) throws ResourceNotFoundException {
		return workersService.update(username, worker);
	}

	@Override
	public void deleteWorker(String username) throws ResourceNotFoundException {
		workersService.delete(username);
	}
}
