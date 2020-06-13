package rs.ac.ni.pmf.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity;
import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity.ChangedPartId;

@Repository
public interface ChangedPartsRepository extends 
	PagingAndSortingRepository<ChangedPartEntity, ChangedPartId>,
	JpaSpecificationExecutor<ChangedPartEntity> {
	
	@Override
	List<ChangedPartEntity> findAll();
	
	List<ChangedPartEntity> findAllByRepairId(final int repairId);
	Optional<ChangedPartEntity> findByRepairIdAndPartId(final int repairId, final int partId);
}
