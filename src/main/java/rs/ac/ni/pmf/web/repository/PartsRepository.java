package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.ni.pmf.web.model.entity.PartEntity;

public interface PartsRepository extends PagingAndSortingRepository<PartEntity, Integer> {

	@Override
	List<PartEntity> findAll();
}
