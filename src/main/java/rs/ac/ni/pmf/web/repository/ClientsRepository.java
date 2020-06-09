package rs.ac.ni.pmf.web.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.ni.pmf.web.model.entity.ClientEntity;

public interface ClientsRepository extends PagingAndSortingRepository<ClientEntity, Integer>{

}
