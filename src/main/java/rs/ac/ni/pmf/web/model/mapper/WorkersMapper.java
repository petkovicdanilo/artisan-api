package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.WorkerDTO;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;

@Component
public class WorkersMapper {
	
	public WorkerDTO toDto(WorkerEntity workerEntity) {
		return WorkerDTO.builder()
				.username(workerEntity.getUsername())
				.firstName(workerEntity.getFirstName())
				.lastName(workerEntity.getLastName())
				.email(workerEntity.getEmail())
				.phoneNumber(workerEntity.getPhoneNumber())
				.build();
	}
	
	public WorkerEntity toEntity(WorkerDTO workerDto) {
		return WorkerEntity.builder()
				.username(workerDto.getUsername())
				.firstName(workerDto.getFirstName())
				.lastName(workerDto.getLastName())
				.email(workerDto.getEmail())
				.phoneNumber(workerDto.getEmail())
				.build();
	}
}
