package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.ni.pmf.web.model.entity.WorkerEntity;

public interface WorkersRepository extends PagingAndSortingRepository<WorkerEntity, String> {

	@Override
	List<WorkerEntity> findAll();
}
