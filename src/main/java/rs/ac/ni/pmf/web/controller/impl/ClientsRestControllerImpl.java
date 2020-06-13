package rs.ac.ni.pmf.web.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.ClientsRestController;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.ClientsSearchOptions;
import rs.ac.ni.pmf.web.model.api.ClientDTO;
import rs.ac.ni.pmf.web.service.ClientsService;

@RestController
@RequiredArgsConstructor
@Api(tags = "Clients")
public class ClientsRestControllerImpl implements ClientsRestController {

	private final ClientsService clientsService;
	
	@Override
	public Page<ClientDTO> getClients(ClientsSearchOptions searchOptions) {
		return clientsService.getAll(searchOptions);
	}

	@Override
	public ClientDTO getClient(int id) throws ResourceNotFoundException {
		return clientsService.getOne(id);
	}

	@Override
	public ClientDTO saveClient(ClientDTO client) {
		return clientsService.save(client);
	}

	@Override
	public ClientDTO updateClient(int id, ClientDTO client) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClient(int id) throws ResourceNotFoundException {
		clientsService.delete(id);
	}
}
