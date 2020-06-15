package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.api.WorkerDTO;
import rs.ac.ni.pmf.web.model.api.WorkerSaveDTO;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;

@Component
@RequiredArgsConstructor
public class WorkersMapper {
	
	private final PasswordEncoder passwordEncoder;
	
	public WorkerDTO toDto(WorkerEntity workerEntity) {
		return WorkerDTO.builder()
				.username(workerEntity.getUsername())
				.isAdmin(workerEntity.isAdmin())
				.firstName(workerEntity.getFirstName())
				.lastName(workerEntity.getLastName())
				.email(workerEntity.getEmail())
				.phoneNumber(workerEntity.getPhoneNumber())
				.build();
	}
	
	public WorkerEntity toEntity(WorkerSaveDTO workerDto) {
		return WorkerEntity.builder()
				.username(workerDto.getUsername())
				.password(passwordEncoder.encode(workerDto.getPassword()))
				.isAdmin(workerDto.isAdmin())
				.firstName(workerDto.getFirstName())
				.lastName(workerDto.getLastName())
				.email(workerDto.getEmail())
				.phoneNumber(workerDto.getEmail())
				.build();
	}
}
