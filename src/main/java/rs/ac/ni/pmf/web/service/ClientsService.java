package rs.ac.ni.pmf.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.ClientsSearchOptions;
import rs.ac.ni.pmf.web.model.api.ClientDTO;
import rs.ac.ni.pmf.web.model.entity.ClientEntity;
import rs.ac.ni.pmf.web.model.mapper.ClientsMapper;
import rs.ac.ni.pmf.web.repository.ClientsRepository;
import rs.ac.ni.pmf.web.repository.specification.ClientsSearchSpecification;

@Service
@RequiredArgsConstructor
public class ClientsService {

	private final ClientsRepository clientsRepository;
	private final ClientsMapper clientsMapper;
	
	public Page<ClientDTO> getAll(final ClientsSearchOptions searchOptions) {
		
		int page = 0;
		int pageSize = 10;
		
		if(searchOptions.getPage() != null) {
			page = searchOptions.getPage();
		}
		
		if(searchOptions.getPageSize() != null) {			
			pageSize = searchOptions.getPageSize();
		}
		
		final PageRequest pageRequest = PageRequest.of(page, pageSize);
		
		return clientsRepository
				.findAll(new ClientsSearchSpecification(searchOptions), pageRequest)
				.map(clientsMapper::toDto);
	}
	
	public ClientDTO getOne(final int id) throws ResourceNotFoundException {
		ClientEntity clientEntity = 
				clientsRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(ResourceType.CLIENT));
		
		return clientsMapper.toDto(clientEntity);
	}
	
	public ClientDTO save(final ClientDTO client) {
		
		final ClientEntity clientEntity = clientsMapper.toEntity(client);
		clientsRepository.save(clientEntity);
		
		return clientsMapper.toDto(clientEntity);
	}
	
	public ClientDTO update(final int id, final ClientDTO client) throws ResourceNotFoundException {
		final ClientEntity clientEntity = clientsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.CLIENT));
		
		clientEntity.setFirstName(client.getFirstName());
		clientEntity.setLastName(client.getLastName());
		clientEntity.setAddress(client.getAddress());
		clientEntity.setPhoneNumber(client.getPhoneNumber());
		
		final ClientEntity savedEntity = clientsRepository.save(clientEntity);
		
		return clientsMapper.toDto(savedEntity);
	}
	
	public void delete(final int id) throws ResourceNotFoundException {
		if(!clientsRepository.existsById(id)) {
			throw new ResourceNotFoundException(ResourceType.CLIENT);
		}
		
		clientsRepository.deleteById(id);
	}
	
}
