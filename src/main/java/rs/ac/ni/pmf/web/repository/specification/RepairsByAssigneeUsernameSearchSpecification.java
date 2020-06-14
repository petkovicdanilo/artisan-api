package rs.ac.ni.pmf.web.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import rs.ac.ni.pmf.web.model.RepairsSearchOptions;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity_;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity_;

public class RepairsByAssigneeUsernameSearchSpecification extends RepairsSearchSpecification {
	
	private static final long serialVersionUID = 1L;
	
	private final String assigneeUsername;
	
	public RepairsByAssigneeUsernameSearchSpecification(
		final RepairsSearchOptions searchOptions,
		final String assigneeUsername) {
		
		super(searchOptions);
		
		this.assigneeUsername = assigneeUsername;
	}
	
	@Override
	public Predicate toPredicate(Root<RepairEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate superPredicate = super.toPredicate(root, query, criteriaBuilder);
		
		Join<RepairEntity, WorkerEntity> workersJoin = root.join(RepairEntity_.assignee, JoinType.LEFT);
		
		Path<String> assigneeUsername = workersJoin.get(WorkerEntity_.username);
			
		Predicate predicate = criteriaBuilder.equal(assigneeUsername, this.assigneeUsername);
		
		return criteriaBuilder.and(superPredicate, predicate);
	}
}
