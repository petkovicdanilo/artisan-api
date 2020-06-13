package rs.ac.ni.pmf.web.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.ClientEntity;

@Repository
public interface ClientsRepository extends 
	PagingAndSortingRepository<ClientEntity, Integer>, 
	JpaSpecificationExecutor<ClientEntity> {

}
