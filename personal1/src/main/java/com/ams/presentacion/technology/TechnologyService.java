package com.ams.presentacion.technology;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
public class TechnologyService implements ITechnologyService {

	@Autowired
	TechnologyDao technologyDao;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public TechnologyDto save(TechnologyDto dto) {
		Technology entity = convertToEntity(dto);
		entity = technologyDao.save(entity);
		return convertToDto(entity);
	}

	@Override
	public List<TechnologyDto> findAll() {
		Iterable<Technology> technologyIterable = technologyDao.findAll();
		List<TechnologyDto> listTechnology = new ArrayList<TechnologyDto>();
		technologyIterable.iterator().forEachRemaining(t -> {
			TechnologyDto dto = convertToDto(t);
			listTechnology.add(dto);
		});
		return listTechnology;
	}

	@Override
	public void delete(int id) {
		technologyDao.deleteById(id);
	}

	@Override
	public TechnologyDto findById(int id) {
		Optional<Technology> optionalDto = technologyDao.findById(id);
		return optionalDto.isPresent() ? convertToDto(optionalDto.get()) : null;

	}

	@Override
	public TechnologyDto update(TechnologyDto dto) {
		Technology entity = convertToEntity(dto);
		technologyDao.save(entity);
		return dto;
	}

	
	//Conversion dto/entity
	private TechnologyDto convertToDto(Technology entity) {
		TechnologyDto dto = modelMapper.map(entity, TechnologyDto.class);
		return dto;
	}

	private Technology convertToEntity(TechnologyDto dto) throws ParseException {
		Technology entity = modelMapper.map(dto, Technology.class);

		return entity;
	}

}
