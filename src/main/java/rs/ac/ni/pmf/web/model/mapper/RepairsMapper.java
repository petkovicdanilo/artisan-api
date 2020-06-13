package rs.ac.ni.pmf.web.model.mapper;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.RepairDTO;
import rs.ac.ni.pmf.web.model.entity.ClientEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;

@Component
public class RepairsMapper {

	public RepairDTO toDto(RepairEntity repairEntity) {
		return RepairDTO.builder()
				.id(repairEntity.getId())
				.clientId(repairEntity.getClient().getId())
				.assigneeUsername(repairEntity.getAssignee().getUsername())
				.reported(repairEntity.getReported())
				.finished(repairEntity.getFinished())
				.build();
	}
	
	public RepairEntity toEntity(
			RepairDTO repairDTO,
			ClientEntity clientEntity,
			WorkerEntity assigneeEntity) {
		
		return RepairEntity.builder()
				.client(clientEntity)
				.assignee(assigneeEntity)
				.reported(new Timestamp(repairDTO.getReported().getTime()))
				.finished(new Timestamp(repairDTO.getFinished().getTime()))
				.build();
	}
	
}
