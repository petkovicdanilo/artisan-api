package rs.ac.ni.pmf.web.model.mapper;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.RepairDTO;
import rs.ac.ni.pmf.web.model.entity.ClientEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;

@Component
public class RepairsMapper {

	public RepairDTO toDto(RepairEntity repairEntity) {
		String assigneeUsername = repairEntity.getAssignee() == null ?
				null :
				repairEntity.getAssignee().getUsername();
		
		return RepairDTO.builder()
				.id(repairEntity.getId())
				.failureDescription(repairEntity.getFailureDescription())
				.additionalCost(repairEntity.getAdditionalCost())
				.clientId(repairEntity.getId())
				.assigneeUsername(assigneeUsername)
				.reported(repairEntity.getReported())
				.finished(repairEntity.getFinished())
				.build();
	}
	
	public RepairEntity toEntity(
			RepairDTO repairDTO,
			ClientEntity clientEntity,
			WorkerEntity assigneeEntity) {
		
		Date finishedDate = repairDTO.getFinished();
		
		Timestamp finishedTime = finishedDate == null ? 
				null : 
				new Timestamp(finishedDate.getTime());
		
		return RepairEntity.builder()
				.failureDescription(repairDTO.getFailureDescription())
				.additionalCost(repairDTO.getAdditionalCost())
				.client(clientEntity)
				.assignee(assigneeEntity)
				.reported(new Timestamp(repairDTO.getReported().getTime()))
				.finished(finishedTime)
				.build();
	}
	
}
