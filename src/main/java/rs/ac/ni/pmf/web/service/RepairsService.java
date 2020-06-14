package rs.ac.ni.pmf.web.service;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.RepairsSearchOptions;
import rs.ac.ni.pmf.web.model.api.RepairDTO;
import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity;
import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity_;
import rs.ac.ni.pmf.web.model.entity.ClientEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity_;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;
import rs.ac.ni.pmf.web.model.mapper.RepairsMapper;
import rs.ac.ni.pmf.web.repository.ClientsRepository;
import rs.ac.ni.pmf.web.repository.RepairsRepository;
import rs.ac.ni.pmf.web.repository.WorkersRepository;
import rs.ac.ni.pmf.web.repository.specification.RepairsByAssigneeUsernameSearchSpecification;
import rs.ac.ni.pmf.web.repository.specification.RepairsByClientSearchSpecification;
import rs.ac.ni.pmf.web.repository.specification.RepairsSearchSpecification;

@Service
@RequiredArgsConstructor
public class RepairsService {

	private final RepairsRepository repairsRepository;
	private final RepairsMapper repairsMapper;
	
	private final ClientsRepository clientsRepository;
	private final WorkersRepository workersRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<RepairDTO> getAll(final RepairsSearchOptions searchOptions) {
		int page = 0;
		int pageSize = 10;
		
		if(searchOptions.getPage() != null) {
			page = searchOptions.getPage();
		}
		
		if(searchOptions.getPageSize() != null) {			
			pageSize = searchOptions.getPageSize();
		}
		
		final PageRequest pageRequest = PageRequest.of(
			page, 
			pageSize, 
			Sort.by(Sort.Direction.DESC, "reported")
		);
		
		return repairsRepository
				.findAll(new RepairsSearchSpecification(searchOptions), pageRequest)
				.map(repairsMapper::toDto);
	}
	
	public RepairDTO getOne(int id) throws ResourceNotFoundException {
		RepairEntity repairEntity = repairsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.REPAIR));
		
		return repairsMapper.toDto(repairEntity);
	}
	
	public Page<RepairDTO> getAllByClientId(
		int clientId,
		RepairsSearchOptions searchOptions
	) 
		throws ResourceNotFoundException {
		
		if(!clientsRepository.existsById(clientId)) {
			throw new ResourceNotFoundException(ResourceType.CLIENT);
		}
		
		int page = 0;
		int pageSize = 10;
		
		if(searchOptions.getPage() != null) {
			page = searchOptions.getPage();
		}
		
		if(searchOptions.getPageSize() != null) {			
			pageSize = searchOptions.getPageSize();
		}
		
		final PageRequest pageRequest = PageRequest.of(
			page, 
			pageSize, 
			Sort.by(Sort.Direction.DESC, "reported")
		);
		
		return repairsRepository
				.findAll(
					new RepairsByClientSearchSpecification(searchOptions, clientId),
					pageRequest
				)
				.map(repairsMapper::toDto);
	}
	
	public Page<RepairDTO> getAllByAssigneeUsername(
		String assigneeUsername,
		RepairsSearchOptions searchOptions
	) 
		throws ResourceNotFoundException {
		
		if(!workersRepository.existsById(assigneeUsername)) {
			throw new ResourceNotFoundException(ResourceType.WORKER);
		}

		int page = 0;
		int pageSize = 10;
		
		if(searchOptions.getPage() != null) {
			page = searchOptions.getPage();
		}
		
		if(searchOptions.getPageSize() != null) {			
			pageSize = searchOptions.getPageSize();
		}
		
		final PageRequest pageRequest = PageRequest.of(
			page, 
			pageSize, 
			Sort.by(Sort.Direction.DESC, "reported")
		);
		
		return repairsRepository
				.findAll(
					new RepairsByAssigneeUsernameSearchSpecification(searchOptions, assigneeUsername),
					pageRequest
				)
				.map(repairsMapper::toDto);
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
		
		repairEntity.setFailureDescription(repair.getFailureDescription());
		repairEntity.setAdditionalCost(repair.getAdditionalCost());
		
		repairEntity.setReported(new Timestamp(repair.getReported().getTime()));
		if(repair.getFinished() != null) {
			repairEntity.setFinished(new Timestamp(repair.getFinished().getTime()));
		}
		
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
	
	public double getPrice(int id) throws ResourceNotFoundException {
		RepairEntity repair = repairsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.REPAIR));
		
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Double> cq = cb.createQuery(Double.class);

		final Root<ChangedPartEntity> root = cq.from(ChangedPartEntity.class);
		final Path<Double> price = root.get(ChangedPartEntity_.price);
		
		final Join<ChangedPartEntity, RepairEntity> changedPartsJoin = 
				root.join(ChangedPartEntity_.repair, JoinType.INNER);

		final Path<Integer> repairId = changedPartsJoin.get(RepairEntity_.id);

		cq.select(cb.sum(price));
		cq.where(cb.equal(repairId, id));
		
		double totalPrice = 0;
		
		Double partsPrice = entityManager.createQuery(cq).getSingleResult();
		if(partsPrice != null) {
			totalPrice += partsPrice;
		}
		
		totalPrice += repair.getAdditionalCost();
		
		return totalPrice;
	}
	
}
