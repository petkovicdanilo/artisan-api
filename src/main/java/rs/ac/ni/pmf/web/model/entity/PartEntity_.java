package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PartEntity.class)
public class PartEntity_ {
	public static volatile SingularAttribute<PartEntity, Integer> id;
	public static volatile SingularAttribute<PartEntity, String> name;
	public static volatile SingularAttribute<PartEntity, Boolean> used;
	
	public static volatile ListAttribute<PartEntity, ChangedPartEntity> changedPartsEntities;
}
