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
import rs.ac.ni.pmf.web.model.PartsSearchOptions;
import rs.ac.ni.pmf.web.model.entity.PartEntity;
import rs.ac.ni.pmf.web.model.entity.PartEntity_;

@RequiredArgsConstructor
public class PartsSearchSpecification implements Specification<PartEntity> {

	private static final long serialVersionUID = 1L;
	
	private final PartsSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<PartEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		final List<Predicate> predicates = new ArrayList<>();
		
		final Path<Boolean> used = root.get(PartEntity_.used);
		
		if(searchOptions.getUsed() != null) {
			predicates.add(criteriaBuilder.equal(used, searchOptions.getUsed()));
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}
	
}
