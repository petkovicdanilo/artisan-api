package rs.ac.ni.pmf.web.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.PartsRestController;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.PartsSearchOptions;
import rs.ac.ni.pmf.web.model.api.PartDTO;
import rs.ac.ni.pmf.web.service.PartsService;

@RestController
@RequiredArgsConstructor
@Api(tags = "Parts")
public class PartsRestControllerImpl implements PartsRestController {

	private final PartsService partsService;
	
	@Override
	public Page<PartDTO> getParts(PartsSearchOptions searchOptions) {
		return partsService.getAll(searchOptions);
	}

	@Override
	public PartDTO getPart(int id) throws ResourceNotFoundException {
		return partsService.getOne(id);
	}

	@Override
	public PartDTO savePart(PartDTO part) {
		return partsService.save(part);
	}

	@Override
	public PartDTO updatePart(int id, PartDTO part) throws ResourceNotFoundException {
		return partsService.update(id, part);
	}

	@Override
	public void deletePart(int id) throws ResourceNotFoundException {
		partsService.delete(id);
	}

}
