package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import rs.ac.ni.pmf.web.model.entity.ChangedPartEntity.ChangedPartId;

@StaticMetamodel(ChangedPartEntity.class)
public class ChangedPartEntity_ {
	public static volatile SingularAttribute<ChangedPartEntity, ChangedPartId> id;
	public static volatile SingularAttribute<ChangedPartEntity, PartEntity> part;
	public static volatile SingularAttribute<ChangedPartEntity, RepairEntity> repair;
	public static volatile SingularAttribute<ChangedPartEntity, Integer> count;
	public static volatile SingularAttribute<ChangedPartEntity, Double> price;
}
