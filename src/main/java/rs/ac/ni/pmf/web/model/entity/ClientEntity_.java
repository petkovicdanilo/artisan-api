package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ClientEntity.class)
public class ClientEntity_ {
	public static volatile SingularAttribute<ClientEntity, Integer> id;
	public static volatile SingularAttribute<ClientEntity, String> firstName;
	public static volatile SingularAttribute<ClientEntity, String> lastName;
	public static volatile SingularAttribute<ClientEntity, String> address;
	public static volatile SingularAttribute<ClientEntity, String> phoneNumber;
	
	public static volatile ListAttribute<ClientEntity, RepairEntity> repairs;
}
