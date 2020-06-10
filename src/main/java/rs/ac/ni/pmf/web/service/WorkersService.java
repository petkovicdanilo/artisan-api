package rs.ac.ni.pmf.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.model.api.WorkerDTO;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;
import rs.ac.ni.pmf.web.model.mapper.WorkersMapper;
import rs.ac.ni.pmf.web.repository.WorkersRepository;

@Service
@RequiredArgsConstructor
public class WorkersService {

	private final WorkersRepository workersRepository;
	private final WorkersMapper workersMapper;
	
	public List<WorkerDTO> getAll() {
		return workersRepository
				.findAll().stream()
				.map(workersMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public WorkerDTO getOne(final String username) throws ResourceNotFoundException {
		WorkerEntity workerEntity = 
				workersRepository.findById(username)
					.orElseThrow(() -> 
						new ResourceNotFoundException(
								ResourceType.WORKER, 
								"Worker with username " + username + " not found"
						));
		
		return workersMapper.toDto(workerEntity);
	}
	
	public WorkerDTO save(final WorkerDTO worker) throws DuplicateResourceException {
		
		if(workersRepository.existsById(worker.getUsername())) {
			throw new DuplicateResourceException(ResourceType.WORKER);
		}
		
		final WorkerEntity workerEntity = workersMapper.toEntity(worker);
		workersRepository.save(workerEntity);
		
		return workersMapper.toDto(workerEntity);
	}
	
	public WorkerDTO update(final String username, final WorkerDTO worker) throws ResourceNotFoundException {
		final WorkerEntity workerEntity = workersRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.WORKER));
		
		workerEntity.setFirstName(worker.getFirstName());
		workerEntity.setLastName(worker.getLastName());
		workerEntity.setPhoneNumber(worker.getPhoneNumber());
		workerEntity.setEmail(worker.getEmail());
		
		final WorkerEntity savedEntity = workersRepository.save(workerEntity);
		
		return workersMapper.toDto(savedEntity);
	}
	
	public void delete(final String username) throws ResourceNotFoundException {
		if(!workersRepository.existsById(username)) {
			throw new ResourceNotFoundException(ResourceType.WORKER);
		}
		
		workersRepository.deleteById(username);
	}
}
