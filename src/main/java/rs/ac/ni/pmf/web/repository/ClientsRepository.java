package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.ClientEntity;

@Repository
public interface ClientsRepository extends PagingAndSortingRepository<ClientEntity, Integer>{

	@Override
	List<ClientEntity> findAll();
}
