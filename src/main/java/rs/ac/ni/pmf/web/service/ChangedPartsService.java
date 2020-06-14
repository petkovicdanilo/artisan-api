package rs.ac.ni.pmf.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.ChangedPartsSearchOptions;
import rs.ac.ni.pmf.web.model.api.ChangedPartDTO;
import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity;
import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity.ChangedPartId;
import rs.ac.ni.pmf.web.model.entity.PartEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.mapper.ChangedPartsMapper;
import rs.ac.ni.pmf.web.repository.ChangedPartsRepository;
import rs.ac.ni.pmf.web.repository.PartsRepository;
import rs.ac.ni.pmf.web.repository.RepairsRepository;
import rs.ac.ni.pmf.web.repository.specification.ChangedPartsSearchSpecification;

@Service
@RequiredArgsConstructor
public class ChangedPartsService {

	private final ChangedPartsRepository changedPartsRepository;
	private final ChangedPartsMapper changedPartsMapper;
	
	private final RepairsRepository repairsRepository;
	private final PartsRepository partsRepository;
	
	public Page<ChangedPartDTO> getAll(
			final int repairId, 
			final ChangedPartsSearchOptions searchOptions
	)
			throws ResourceNotFoundException {
		
		ensureRepairExists(repairId);
		
		int page = 0;
		int pageSize = 10;
		
		if(searchOptions.getPage() != null) {
			page = searchOptions.getPage();
		}
		
		if(searchOptions.getPageSize() != null) {			
			pageSize = searchOptions.getPageSize();
		}
		
		final PageRequest pageRequest = PageRequest.of(page, pageSize);
		
		return changedPartsRepository
				.findAll(
					new ChangedPartsSearchSpecification(repairId, searchOptions),
					pageRequest
				)
				.map(changedPartsMapper::toDto);
	}
	
	public ChangedPartDTO getOne(final int repairId, final int partId) throws ResourceNotFoundException {
		ensureRepairExists(repairId);
		
		ChangedPartEntity changedPartEntity = changedPartsRepository
				.findByRepairIdAndPartId(repairId, partId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.CHANGED_PART));
		
		return changedPartsMapper.toDto(changedPartEntity);
	}
	
	public ChangedPartDTO save(final int repairId, final ChangedPartDTO changedPart) throws BadRequestException, DuplicateResourceException, ResourceNotFoundException {
		if(repairId != changedPart.getRepairId()) {
			throw new BadRequestException("Different repair ids sent");
		}
		
		ensureRepairExists(repairId);
		
		if(changedPartsRepository.existsById(new ChangedPartId(changedPart.getPartId(), repairId))) {
			throw new DuplicateResourceException(ResourceType.CHANGED_PART);
		}
		
		PartEntity partEntity = partsRepository.findById(changedPart.getPartId())
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PART));
		
		RepairEntity repairEntity = repairsRepository.findById(repairId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.REPAIR));
		
		ChangedPartEntity changedPartEntity = changedPartsMapper.toEntity(changedPart, partEntity, repairEntity);
		changedPartsRepository.save(changedPartEntity);
		
		return changedPartsMapper.toDto(changedPartEntity);
	}
	
	public ChangedPartDTO update(final int repairId, final ChangedPartDTO changedPart) throws BadRequestException, ResourceNotFoundException {
		if(repairId != changedPart.getRepairId()) {
			throw new BadRequestException("Different repair ids sent");
		}
		
		PartEntity partEntity = partsRepository.findById(changedPart.getPartId())
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PART));
		
		RepairEntity repairEntity = repairsRepository.findById(repairId)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.REPAIR));
		
		ChangedPartEntity changedPartEntity = changedPartsRepository.findById(
				new ChangedPartId(changedPart.getPartId(), changedPart.getRepairId()))
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.CHANGED_PART));
		
		if(changedPartEntity.getPart().getId() != changedPart.getPartId()) {
			changedPartEntity.setPart(partEntity);
		}
		
		if(changedPartEntity.getRepair().getId() != changedPart.getRepairId()) {
			changedPartEntity.setRepair(repairEntity);
		}
		
		changedPartEntity.setCount(changedPart.getCount());
		changedPartEntity.setPrice(changedPart.getPrice());
		
		changedPartsRepository.save(changedPartEntity);
		
		return changedPartsMapper.toDto(changedPartEntity);
	}
	
	public void delete(int repairId, int partId) throws ResourceNotFoundException {
		ChangedPartId id = new ChangedPartId(partId, repairId);
		
		if(!changedPartsRepository.existsById(id)) {
			throw new ResourceNotFoundException(ResourceType.CHANGED_PART);
		}
		
		changedPartsRepository.deleteById(id);
	}
	
	private void ensureRepairExists(final int repairId) throws ResourceNotFoundException {
		if(!repairsRepository.existsById(repairId)) {
			throw new ResourceNotFoundException(ResourceType.REPAIR);
		}
	}
}
