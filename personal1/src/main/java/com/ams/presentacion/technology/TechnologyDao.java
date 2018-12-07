package com.ams.presentacion.technology;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TechnologyDao extends CrudRepository<Technology,Integer>{

}
