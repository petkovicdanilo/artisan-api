package rs.ac.ni.pmf.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.ClientDTO;
import rs.ac.ni.pmf.web.model.entity.ClientEntity;
import rs.ac.ni.pmf.web.model.mapper.ClientsMapper;
import rs.ac.ni.pmf.web.repository.ClientsRepository;

@Service
@RequiredArgsConstructor
public class ClientsService {

	private final ClientsRepository clientsRepository;
	private final ClientsMapper clientsMapper;
	
	public List<ClientDTO> getAll() {
		return clientsRepository
				.findAll().stream()
				.map(clientsMapper::toDto)
				.collect(Collectors.toList());
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
