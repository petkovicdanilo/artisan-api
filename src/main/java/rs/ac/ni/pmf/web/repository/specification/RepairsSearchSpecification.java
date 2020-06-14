package rs.ac.ni.pmf.web.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.RepairsSearchOptions;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity_;

@RequiredArgsConstructor
public class RepairsSearchSpecification implements Specification<RepairEntity> {

	private static final long serialVersionUID = 1L;
	
	protected final RepairsSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<RepairEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		final List<Predicate> predicates = new ArrayList<>();
		
		final Path<Double> additionalCost = root.get(RepairEntity_.additionalCost);
		
		Double minAdditionalCost = searchOptions.getMinAdditionalCost();
		if(minAdditionalCost != null) {
			predicates.add(criteriaBuilder.ge(additionalCost, minAdditionalCost));
		}
		
		Double maxAdditionalCost = searchOptions.getMaxAdditionalCost();
		if(maxAdditionalCost != null) {
			predicates.add(criteriaBuilder.ge(additionalCost, maxAdditionalCost));
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
