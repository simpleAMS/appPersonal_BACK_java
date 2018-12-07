package com.ams.presentacion.technology;

import java.util.List;


public interface ITechnologyService {
	
	TechnologyDto save(TechnologyDto dto);

	List<TechnologyDto> findAll();

	void delete(int id);

	TechnologyDto findById(int id);

	TechnologyDto update(TechnologyDto dto);
}
