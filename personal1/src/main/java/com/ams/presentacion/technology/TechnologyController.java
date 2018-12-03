package com.ams.presentacion.technology;

import org.springframework.web.bind.annotation.*;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/")
public class TechnologyController {
	
	//TODO: Implementar loggin
	/*
	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	@RequestMapping("/log")
	String setLog() {
		logger.trace("A TRACE Message");
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");
		return "traza de log lanzada";
	}
*/
	
	@RequestMapping("/")
	String prueba() {
		return "Llamada correcta";
	}

	@RequestMapping("/technology/{id}")
	Technology getTechnology(int id) {

		return new Technology();
	}

	@RequestMapping("/technology")
	int newTechnology(Technology tech) {

		return 1;
	}

	@RequestMapping("/technology/technologies")
	void getTechnologies(Technology tech) {
	}

}
