package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(WorkerEntity.class)
public class WorkerEntity_ {
	public static volatile SingularAttribute<WorkerEntity, String> username;
	public static volatile SingularAttribute<WorkerEntity, String> password;
	public static volatile SingularAttribute<WorkerEntity, Boolean> isAdmin;
	public static volatile SingularAttribute<WorkerEntity, String> firstName;
	public static volatile SingularAttribute<WorkerEntity, String> lastName;
	public static volatile SingularAttribute<WorkerEntity, String> email;
	public static volatile SingularAttribute<WorkerEntity, String> phoneNumber;
	
	public static volatile ListAttribute<WorkerEntity, RepairEntity> assignedRepairs;
}
