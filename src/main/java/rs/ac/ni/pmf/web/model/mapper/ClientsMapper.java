package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.ClientDTO;
import rs.ac.ni.pmf.web.model.entity.ClientEntity;

@Component
public class ClientsMapper {
	
	public ClientDTO toDto(final ClientEntity clientEntity) {
		return ClientDTO.builder()
				.id(clientEntity.getId())
				.firstName(clientEntity.getFirstName())
				.lastName(clientEntity.getLastName())
				.address(clientEntity.getAddress())
				.phoneNumber(clientEntity.getPhoneNumber())
				.build();
	}
	
	public ClientEntity toEntity(final ClientDTO clientDto) {
		return ClientEntity.builder()
				.id(clientDto.getId())
				.firstName(clientDto.getFirstName())
				.lastName(clientDto.getLastName())
				.address(clientDto.getAddress())
				.phoneNumber(clientDto.getPhoneNumber())
				.build();
	}
}
