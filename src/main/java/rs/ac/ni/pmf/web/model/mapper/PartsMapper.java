package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.PartDTO;
import rs.ac.ni.pmf.web.model.entity.PartEntity;

@Component
public class PartsMapper {
	public PartDTO toDto(PartEntity partEntity) {
		return PartDTO.builder()
				.id(partEntity.getId())
				.name(partEntity.getName())
				.used(partEntity.isUsed())
				.build();
	}
	
	public PartEntity toEntity(PartDTO partDto) {
		return PartEntity.builder()
				.name(partDto.getName())
				.used(partDto.isUsed())
				.build();
	}
}
