package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.ChangedPartDTO;
import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity;
import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity.ChangedPartId;
import rs.ac.ni.pmf.web.model.entity.PartEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;

@Component
public class ChangedPartsMapper {

	public ChangedPartDTO toDto(ChangedPartEntity changedPartEntity) {
		return ChangedPartDTO.builder()
				.partId(changedPartEntity.getId().getPartId())
				.repairId(changedPartEntity.getId().getRepairId())
				.count(changedPartEntity.getCount())
				.price(changedPartEntity.getPrice())
				.build();
	}
	
	public ChangedPartEntity toEntity(
			ChangedPartDTO changedPartDto,
			PartEntity partEntity,
			RepairEntity repairEntity) {
		
		return ChangedPartEntity.builder()
				.id(new ChangedPartId(partEntity.getId(), repairEntity.getId()))
				.part(partEntity)
				.repair(repairEntity)
				.count(changedPartDto.getCount())
				.price(changedPartDto.getPrice())
				.build();
	}
}
