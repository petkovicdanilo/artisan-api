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
import rs.ac.ni.pmf.web.model.ClientsSearchOptions;
import rs.ac.ni.pmf.web.model.entity.ClientEntity;
import rs.ac.ni.pmf.web.model.entity.ClientEntity_;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity_;

@RequiredArgsConstructor
public class ClientsSearchSpecification implements Specification<ClientEntity> {

	private static final long serialVersionUID = 1L;
	
	private final ClientsSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<ClientEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		final List<Predicate> predicates = new ArrayList<>();
		
		final Path<Integer> id = root.get(ClientEntity_.id);
		final Path<String> firstName = root.get(ClientEntity_.firstName);
		final Path<String> lastName = root.get(ClientEntity_.lastName);
		final Path<String> address = root.get(ClientEntity_.address);
		final Path<String> phoneNumber = root.get(ClientEntity_.phoneNumber);
		
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
		
		final String addressFilter = searchOptions.getAddress();
		if(addressFilter != null && !addressFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(
				criteriaBuilder.lower(address),
				"%" + addressFilter.toLowerCase() + "%"
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
		
		Join<ClientEntity, RepairEntity> repairsJoin = null;
		
		Path<Integer> repairId = null;
		
		if(minRepairsFilter != null || maxRepairsFilter != null) {
			repairsJoin = root.join(ClientEntity_.repairs, JoinType.LEFT);
			repairId = repairsJoin.get(RepairEntity_.id);
			query.groupBy(id);
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
