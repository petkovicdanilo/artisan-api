package rs.ac.ni.pmf.web.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import rs.ac.ni.pmf.web.model.RepairsSearchOptions;
import rs.ac.ni.pmf.web.model.entity.ClientEntity;
import rs.ac.ni.pmf.web.model.entity.ClientEntity_;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity_;

public class RepairsByClientSearchSpecification extends RepairsSearchSpecification {

	private static final long serialVersionUID = 1L;
	
	private final int clientId;
	
	public RepairsByClientSearchSpecification(
		final RepairsSearchOptions searchOptions,
		final int clientId) {
		
		super(searchOptions);
		
		this.clientId = clientId;
	}
	
	@Override
	public Predicate toPredicate(Root<RepairEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate superPredicate = super.toPredicate(root, query, criteriaBuilder);
		
		Join<RepairEntity, ClientEntity> clientsJoin = root.join(RepairEntity_.client, JoinType.LEFT);
		
		Path<Integer> clientId = clientsJoin.get(ClientEntity_.id);
			
		Predicate predicate = criteriaBuilder.equal(clientId, this.clientId);
		
		return criteriaBuilder.and(superPredicate, predicate);
	}

}
