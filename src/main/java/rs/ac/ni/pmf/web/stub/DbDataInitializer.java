//package rs.ac.ni.pmf.web.stub;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//
//import lombok.RequiredArgsConstructor;
//import rs.ac.ni.pmf.web.model.api.ChangedPartDTO;
//import rs.ac.ni.pmf.web.model.api.ClientDTO;
//import rs.ac.ni.pmf.web.model.api.PartDTO;
//import rs.ac.ni.pmf.web.model.api.RepairDTO;
//import rs.ac.ni.pmf.web.model.api.WorkerDTO;
//import rs.ac.ni.pmf.web.service.ChangedPartsService;
//import rs.ac.ni.pmf.web.service.ClientsService;
//import rs.ac.ni.pmf.web.service.PartsService;
//import rs.ac.ni.pmf.web.service.RepairsService;
//import rs.ac.ni.pmf.web.service.WorkersService;
//
//@RequiredArgsConstructor
//@Component
//public class DbDataInitializer implements InitializingBean {
//	
//	private final ClientsService clientsService;
//	private final WorkersService workersService;
//	private final PartsService partsService;
//	private final RepairsService repairsService;
//	private final ChangedPartsService changedPartsService;
//	
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		ClientDTO client1 = ClientDTO.builder()
//				.firstName("firstName1")
//				.lastName("lastName1")
//				.address("address1")
//				.phoneNumber("111111")
//				.build();
//		
//		ClientDTO client2 = ClientDTO.builder()
//				.firstName("firstName2")
//				.lastName("lastName2")
//				.address("address2")
//				.phoneNumber("222222")
//				.build();
//		
//		ClientDTO client3 = ClientDTO.builder()
//				.firstName("firstName3")
//				.lastName("lastName3")
//				.address("address3")
//				.phoneNumber("333333")
//				.build();
//		
//		client1 = clientsService.save(client1);
//		client2 = clientsService.save(client2);
//		client3 = clientsService.save(client3);
//		
//		WorkerDTO worker1 = WorkerDTO.builder()
//				.username("worker1")
//				.firstName("workerFirstName1")
//				.lastName("workerLastName1")
//				.email("worker1@artisan-api.com")
//				.phoneNumber("111-222")
//				.build();
//		
//		WorkerDTO worker2 = WorkerDTO.builder()
//				.username("worker2")
//				.firstName("workerFirstName2")
//				.lastName("workerLastName2")
//				.email("worker2@artisan-api.com")
//				.phoneNumber("222-333")
//				.build();
//		
//		WorkerDTO worker3 = WorkerDTO.builder()
//				.username("worker3")
//				.firstName("workerFirstName3")
//				.lastName("workerLastName3")
//				.email("worker3@artisan-api.com")
//				.phoneNumber("333-444")
//				.build();
//		
//		worker1 = workersService.save(worker1);
//		worker2 = workersService.save(worker2);
//		worker3 = workersService.save(worker3);
//		
//		PartDTO part1 = PartDTO.builder()
//				.name("part1")
//				.used(false)
//				.build();
//		
//		PartDTO part2 = PartDTO.builder()
//				.name("part2")
//				.used(true)
//				.build();
//		
//		PartDTO part3 = PartDTO.builder()
//				.name("part3")
//				.used(false)
//				.build();
//		
//		part1 = partsService.save(part1);
//		part2 = partsService.save(part2);
//		part3 = partsService.save(part3);
//		
//		RepairDTO repair1 = RepairDTO.builder()
//				.failureDescription("Failure description 1")
//				.clientId(client1.getId())
//				.assigneeUsername(worker1.getUsername())
//				.reported(new Date())
//				.build();
//		
//		RepairDTO repair2 = RepairDTO.builder()
//				.failureDescription("Failure description 2")
//				.clientId(client1.getId())
//				.assigneeUsername(worker2.getUsername())
//				.reported(new Date())
//				.finished(new Date())
//				.build();
//		
//		RepairDTO repair3 = RepairDTO.builder()
//				.failureDescription("Failure description 3")
//				.clientId(client2.getId())
//				.assigneeUsername(worker1.getUsername())
//				.reported(new Date())
//				.build();
//		
//		repair1 = repairsService.save(repair1);
//		repair2 = repairsService.save(repair2);
//		repair3 = repairsService.save(repair3);
//		
//		ChangedPartDTO changedPart1 = ChangedPartDTO.builder()
//				.partId(part1.getId())
//				.repairId(repair1.getId())
//				.count(2)
//				.price(1200.5)
//				.build();
//		
//		ChangedPartDTO changedPart2 = ChangedPartDTO.builder()
//				.partId(part2.getId())
//				.repairId(repair1.getId())
//				.count(1)
//				.price(200.5)
//				.build();
//		
//		ChangedPartDTO changedPart3 = ChangedPartDTO.builder()
//				.partId(part3.getId())
//				.repairId(repair2.getId())
//				.count(10)
//				.price(12)
//				.build();
//		
//		ChangedPartDTO changedPart4 = ChangedPartDTO.builder()
//				.partId(part1.getId())
//				.repairId(repair3.getId())
//				.count(1)
//				.price(1200.5)
//				.build();
//		
//		ChangedPartDTO changedPart5 = ChangedPartDTO.builder()
//				.partId(part2.getId())
//				.repairId(repair3.getId())
//				.count(2)
//				.price(120)
//				.build();
//		
//		changedPartsService.save(repair1.getId(), changedPart1);
//		changedPartsService.save(repair1.getId(), changedPart2);
//		changedPartsService.save(repair2.getId(), changedPart3);
//		changedPartsService.save(repair3.getId(), changedPart4);
//		changedPartsService.save(repair3.getId(), changedPart5);
//	}
//	
//	
//}
