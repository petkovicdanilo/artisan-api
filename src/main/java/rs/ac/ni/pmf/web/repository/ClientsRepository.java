package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.ni.pmf.web.model.entity.ClientEntity;

public interface ClientsRepository extends PagingAndSortingRepository<ClientEntity, Integer>{

	@Override
	List<ClientEntity> findAll();
}
