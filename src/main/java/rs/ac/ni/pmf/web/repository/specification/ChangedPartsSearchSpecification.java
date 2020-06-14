package rs.ac.ni.pmf.web.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.ChangedPartsSearchOptions;
import rs.ac.ni.pmf.web.model.ChangedPartsSearchOptions.SortByField;
import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity;
import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity_;
import rs.ac.ni.pmf.web.model.entity.RepairEntity;
import rs.ac.ni.pmf.web.model.entity.RepairEntity_;

@RequiredArgsConstructor
public class ChangedPartsSearchSpecification implements Specification<ChangedPartEntity>{

	private static final long serialVersionUID = 1L;
	
	private final int repairId;
	private final ChangedPartsSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<ChangedPartEntity> root, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {
		
		final List<Predicate> predicates = new ArrayList<>();
		
		final Path<Integer> count = root.get(ChangedPartEntity_.count);
		final Path<Double> price = root.get(ChangedPartEntity_.price);
		final Path<RepairEntity> repair = root.get(ChangedPartEntity_.repair);
	
		predicates.add(criteriaBuilder.equal(repair.get(RepairEntity_.id), repairId));
		
		final Integer minCountFilter = searchOptions.getMinCount();
		if(minCountFilter != null) {
			predicates.add(criteriaBuilder.ge(count, minCountFilter));
		}
		
		final Integer maxCountFilter = searchOptions.getMaxCount();
		if(maxCountFilter != null) {
			predicates.add(criteriaBuilder.le(count, maxCountFilter));
		}
		
		final Double minPriceFilter = searchOptions.getMinPrice();
		if(minPriceFilter != null) {
			predicates.add(criteriaBuilder.ge(price, minPriceFilter));
		}
		
		final Double maxPriceFilter = searchOptions.getMaxPrice();
		if(maxPriceFilter != null) {
			predicates.add(criteriaBuilder.le(price, maxPriceFilter));
		}
		
		final SortByField sortBy = searchOptions.getSortBy();
		if(sortBy != null) {
			Path<?> propertyToSortBy = null;
			
			switch (sortBy) {
				case count:
					propertyToSortBy = count;
					break;
				case price:
					propertyToSortBy = price;
					break;
			}
			
			Direction sortDirection = searchOptions.getSortDirection();
			if(sortDirection == null || sortDirection == Direction.ASC) {
				query.orderBy(criteriaBuilder.asc(propertyToSortBy));
			}
			else {
				query.orderBy(criteriaBuilder.desc(propertyToSortBy));
			}
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
