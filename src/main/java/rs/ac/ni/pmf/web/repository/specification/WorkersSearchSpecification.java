package rs.ac.ni.pmf.web.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.WorkersSearchOptions;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity_;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity_;

@RequiredArgsConstructor
public class WorkersSearchSpecification implements Specification<WorkerEntity> {
 
	private static final long serialVersionUID = 1L;
	
	private final WorkersSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<WorkerEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		final List<Predicate> predicates = new ArrayList<>();
		
		final Path<String> username = root.get(WorkerEntity_.username);
		final Path<String> email = root.get(WorkerEntity_.email);
		final Path<String> firstName = root.get(WorkerEntity_.firstName);
		final Path<String> lastName = root.get(WorkerEntity_.lastName);
		final Path<String> phoneNumber = root.get(WorkerEntity_.phoneNumber);
		
		final String usernameFilter = searchOptions.getUsername();
		if(usernameFilter != null && !usernameFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(
				criteriaBuilder.lower(username),
				"%" + usernameFilter.toLowerCase() + "%"
			));
		}
		
		final String emailFilter = searchOptions.getEmail();
		if(emailFilter != null && !emailFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(
				criteriaBuilder.lower(email),
				"%" + emailFilter.toLowerCase() + "%"
			));
		}
		
		final String firstNameFilter = searchOptions.getFirstName();
		if(firstNameFilter != null && !firstNameFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(
				criteriaBuilder.lower(firstName),
				"%" + firstNameFilter.toLowerCase() + "%"
			));
		}
		
		final String lastNameFilter = searchOptions.getLastName();
		if(lastNameFilter != null && !lastNameFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(
				criteriaBuilder.lower(lastName),
				"%" + lastNameFilter.toLowerCase() + "%"
			));
		}
		
		final String phoneNumberFilter = searchOptions.getPhoneNumber();
		if(phoneNumberFilter != null && !phoneNumberFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(
				criteriaBuilder.lower(phoneNumber),
				"%" + phoneNumberFilter.toLowerCase() + "%"
			));
		}
		
		final Integer minRepairsFilter = searchOptions.getMinRepairs();
		final Integer maxRepairsFilter = searchOptions.getMaxRepairs();
		
		Join<WorkerEntity, RepairEntity> repairsJoin = null;
		
		Path<Integer> repairId = null;
		
		if(minRepairsFilter != null || maxRepairsFilter != null) {
			repairsJoin = root.join(WorkerEntity_.assignedRepairs, JoinType.LEFT);
			repairId = repairsJoin.get(RepairEntity_.id);
			query.groupBy(username);
		}
		
		if(minRepairsFilter != null) {
			query.having(
				criteriaBuilder.ge(criteriaBuilder.count(repairId), minRepairsFilter)
			);
		}
		
		if(maxRepairsFilter != null) {
			query.having(
				criteriaBuilder.le(criteriaBuilder.count(repairId), maxRepairsFilter)
			);
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}
	
}
