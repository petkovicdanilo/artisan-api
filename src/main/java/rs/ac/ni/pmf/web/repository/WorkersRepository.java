package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.WorkerEntity;

@Repository
public interface WorkersRepository extends 
	PagingAndSortingRepository<WorkerEntity, String>,
	JpaSpecificationExecutor<WorkerEntity> {

	@Override
	List<WorkerEntity> findAll();
}
