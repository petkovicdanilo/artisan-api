package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.RepairEntity;

@Repository
public interface RepairsRepository extends 
	PagingAndSortingRepository<RepairEntity, Integer>,
	JpaSpecificationExecutor<RepairEntity> {

	@Override
	List<RepairEntity> findAll();
	
	List<RepairEntity> findByAssigneeUsername(String assigneeUsername);
	List<RepairEntity> findByClientId(int clientId);
}
