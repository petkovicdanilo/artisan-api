package rs.ac.ni.pmf.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.PartDTO;
import rs.ac.ni.pmf.web.model.entity.PartEntity;
import rs.ac.ni.pmf.web.model.mapper.PartsMapper;
import rs.ac.ni.pmf.web.repository.PartsRepository;

@Service
@RequiredArgsConstructor
public class PartsService {

	private final PartsRepository partsRepository;
	private final PartsMapper partsMapper;
	
	public List<PartDTO> getAll() {
		return partsRepository.findAll().stream()
				.map(partsMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public PartDTO getOne(final int id) throws ResourceNotFoundException {
		PartEntity partEntity = partsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PART));
		
		return partsMapper.toDto(partEntity);
	}
	
	public PartDTO save(final PartDTO part) throws DuplicateResourceException {
		if(partsRepository.existsById(part.getId())) {
			throw new DuplicateResourceException(ResourceType.PART);
		}
		
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
