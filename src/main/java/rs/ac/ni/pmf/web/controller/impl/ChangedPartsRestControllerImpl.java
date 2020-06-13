package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.ChangedPartsRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.ChangedPartDTO;
import rs.ac.ni.pmf.web.service.ChangedPartsService;

@RestController
@RequiredArgsConstructor
@Api(tags = "Changed parts")
public class ChangedPartsRestControllerImpl implements ChangedPartsRestController {

	private final ChangedPartsService changedPartsService;
	
	@Override
	public List<ChangedPartDTO> getChangedParts(int repairId) throws ResourceNotFoundException {
		return changedPartsService.getAll(repairId);
	}

	@Override
	public ChangedPartDTO getChangedPart(int repairId, int partId) throws ResourceNotFoundException {
		return changedPartsService.getOne(repairId, partId);
	}

	@Override
	public ChangedPartDTO saveChangedPart(int repairId, ChangedPartDTO changedPart) throws BadRequestException, DuplicateResourceException, ResourceNotFoundException {
		return changedPartsService.save(repairId, changedPart);
	}

	@Override
	public ChangedPartDTO updateChangedPart(int repairId, ChangedPartDTO changedPart)
			throws BadRequestException, DuplicateResourceException, ResourceNotFoundException {
		return changedPartsService.update(repairId, changedPart);
	}

	@Override
	public void deleteChangedPart(int repairId, int partId) throws ResourceNotFoundException {
		changedPartsService.delete(repairId, partId);
	}

}
