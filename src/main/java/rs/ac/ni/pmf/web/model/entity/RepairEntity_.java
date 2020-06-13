package rs.ac.ni.pmf.web.model.entity;

import java.sql.Timestamp;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RepairEntity.class)
public class RepairEntity_ {
	public static volatile SingularAttribute<RepairEntity, Integer> id;
	public static volatile SingularAttribute<RepairEntity, WorkerEntity> assignee;
	public static volatile SingularAttribute<RepairEntity, ClientEntity> client;
	public static volatile SingularAttribute<RepairEntity, Timestamp> reported;
	public static volatile SingularAttribute<RepairEntity, Timestamp> finished;
	
	public static volatile ListAttribute<RepairEntity, ChangedPartEntity> changedPartsEntities;
}
