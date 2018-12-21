package com.ams.presentacion.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ams.presentacion.common.Messages;
import com.ams.presentacion.common.RequestResponse;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

	// TODO: Implementar loggin
	/*
	 * Logger logger = LoggerFactory.getLogger(LoggingController.class);
	 * 
	 * @RequestMapping("/log") String setLog() { logger.trace("A TRACE Message");
	 * logger.debug("A DEBUG Message"); logger.info("An INFO Message");
	 * logger.warn("A WARN Message"); logger.error("An ERROR Message"); return
	 * "traza de log lanzada"; }
	 */

	@Autowired
	ITechnologyService technologyService;

	@GetMapping("/{id}")
	RequestResponse getTechnologyById(@PathVariable int id) {
		return new RequestResponse(HttpStatus.OK, Messages.CREATED, technologyService.findById(id));
	}

	@GetMapping("")
	RequestResponse getTechnologies() {
		return new RequestResponse(HttpStatus.OK, Messages.GET, technologyService.findAll());
	}

	@PostMapping()
	public RequestResponse createTechnology(@RequestBody TechnologyDto dto) {
		TechnologyDto responseDto = null;
		try {
			responseDto = technologyService.save(dto);
		} catch (Exception e) {
		}
		return new RequestResponse(HttpStatus.CREATED, Messages.CREATED, responseDto);
	}

	@PutMapping()
	public RequestResponse updateUser(@RequestBody TechnologyDto dto) {
		return new RequestResponse(HttpStatus.OK, Messages.UPDATED, technologyService.update(dto));
	}

	@DeleteMapping("/{id}")
	RequestResponse deleteTechnology(@PathVariable int id) {
		technologyService.delete(id);
		return new RequestResponse(HttpStatus.OK, Messages.DELETED, id);
	}

}
