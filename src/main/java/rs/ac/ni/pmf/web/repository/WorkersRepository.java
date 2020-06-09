package rs.ac.ni.pmf.web.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.ni.pmf.web.model.entity.WorkerEntity;

public interface WorkersRepository extends PagingAndSortingRepository<WorkerEntity, String> {

}
