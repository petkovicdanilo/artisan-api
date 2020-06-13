package rs.ac.ni.pmf.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.PartsSearchOptions;
import rs.ac.ni.pmf.web.model.api.PartDTO;
import rs.ac.ni.pmf.web.model.entity.PartEntity;
import rs.ac.ni.pmf.web.model.mapper.PartsMapper;
import rs.ac.ni.pmf.web.repository.PartsRepository;

@Service
@RequiredArgsConstructor
public class PartsService {

	private final PartsRepository partsRepository;
	private final PartsMapper partsMapper;
	
	public Page<PartDTO> getAll(final PartsSearchOptions searchOptions) {
		int page = 0;
		int pageSize = 10;
		
		if(searchOptions.getPage() != null) {
			page = searchOptions.getPage();
		}
		
		if(searchOptions.getPageSize() != null) {			
			pageSize = searchOptions.getPageSize();
		}
		
		final PageRequest pageRequest = PageRequest.of(page, pageSize);
		
		return partsRepository
				.findAll(pageRequest)
				.map(partsMapper::toDto);
	}
	
	public PartDTO getOne(final int id) throws ResourceNotFoundException {
		PartEntity partEntity = partsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PART));
		
		return partsMapper.toDto(partEntity);
	}
	
	public PartDTO save(final PartDTO part) {
	
		final PartEntity partEntity = partsMapper.toEntity(part);
		partsRepository.save(partEntity);
		
		return partsMapper.toDto(partEntity);
	}
	
	public PartDTO update(final int id, final PartDTO part) throws ResourceNotFoundException {
		final PartEntity partEntity = partsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PART));
		
		partEntity.setName(part.getName());
		partEntity.setUsed(part.isUsed());
		partsRepository.save(partEntity);
		
		return partsMapper.toDto(partEntity);
	}
	
	public void delete(final int id) throws ResourceNotFoundException {
		if(!partsRepository.existsById(id)) {
			throw new ResourceNotFoundException(ResourceType.PART);
		}
		
		partsRepository.deleteById(id);
	}
}
