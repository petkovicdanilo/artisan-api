package rs.ac.ni.pmf.web.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.RepairsSearchOptions;
import rs.ac.ni.pmf.web.model.api.RepairDTO;
import rs.ac.ni.pmf.web.model.entity.ClientEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;
import rs.ac.ni.pmf.web.model.mapper.RepairsMapper;
import rs.ac.ni.pmf.web.repository.ClientsRepository;
import rs.ac.ni.pmf.web.repository.RepairsRepository;
import rs.ac.ni.pmf.web.repository.WorkersRepository;

@Service
@RequiredArgsConstructor
public class RepairsService {

	private final RepairsRepository repairsRepository;
	private final RepairsMapper repairsMapper;
	
	private final ClientsRepository clientsRepository;
	private final WorkersRepository workersRepository;
	
	public Page<RepairDTO> getAll(final RepairsSearchOptions searchOptions) {
		int page = 0;
		int pageSize = 10;
		
		if(searchOptions.getPage() != null) {
			page = searchOptions.getPage();
		}
		
		if(searchOptions.getPageSize() != null) {			
			pageSize = searchOptions.getPageSize();
		}
		
		final PageRequest pageRequest = PageRequest.of(page, pageSize);
		
		return repairsRepository
				.findAll(pageRequest)
				.map(repairsMapper::toDto);
	}
	
	public RepairDTO getOne(int id) throws ResourceNotFoundException {
		RepairEntity repairEntity = repairsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.REPAIR));
		
		return repairsMapper.toDto(repairEntity);
	}
	
	public List<RepairDTO> getAllByClientId(int clientId) throws ResourceNotFoundException {
		if(!clientsRepository.existsById(clientId)) {
			throw new ResourceNotFoundException(ResourceType.CLIENT);
		}
		
		return repairsRepository.findByClientId(clientId).stream()
				.map(repairsMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public List<RepairDTO> getAllByAssigneeUsername(String assigneeUsername) throws ResourceNotFoundException {
		if(!workersRepository.existsById(assigneeUsername)) {
			throw new ResourceNotFoundException(ResourceType.WORKER);
		}
		
		return repairsRepository.findByAssigneeUsername(assigneeUsername).stream()
				.map(repairsMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public RepairDTO save(RepairDTO repair) throws ResourceNotFoundException {
		
		ClientEntity clientEntity = clientsRepository.findById(repair.getClientId())
				.orElseThrow(()-> new ResourceNotFoundException(ResourceType.CLIENT));
		
		WorkerEntity assigneeEntity = workersRepository.findById(repair.getAssigneeUsername())
				.orElseThrow(()-> new ResourceNotFoundException(ResourceType.WORKER));
		
		RepairEntity repairEntity = repairsMapper.toEntity(repair, clientEntity, assigneeEntity);
		repairsRepository.save(repairEntity);
		
		return repairsMapper.toDto(repairEntity);
	}
	
	public RepairDTO update(int id, RepairDTO repair) throws ResourceNotFoundException {
		RepairEntity repairEntity = repairsRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(ResourceType.REPAIR));
		
		repairEntity.setReported(new Timestamp(repair.getReported().getTime()));
		repairEntity.setFinished(new Timestamp(repair.getFinished().getTime()));
		
		if(repairEntity.getAssignee().getUsername() != repair.getAssigneeUsername()) {
			WorkerEntity assignee = workersRepository.findById(repair.getAssigneeUsername())
					.orElseThrow(() -> new ResourceNotFoundException(ResourceType.WORKER));
			
			repairEntity.setAssignee(assignee);
		}
		
		if(repairEntity.getClient().getId() != repair.getClientId()) {
			ClientEntity client = clientsRepository.findById(repair.getClientId())
					.orElseThrow(() -> new ResourceNotFoundException(ResourceType.CLIENT));
			
			repairEntity.setClient(client);
		}
		
		repairsRepository.save(repairEntity);
		
		return repairsMapper.toDto(repairEntity);
	}
	
	public void delete(int id) throws ResourceNotFoundException {
		if(!repairsRepository.existsById(id)) {
			throw new ResourceNotFoundException(ResourceType.REPAIR);
		}
		
		repairsRepository.deleteById(id);
	}
	
}
