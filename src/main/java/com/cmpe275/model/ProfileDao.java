package com.cmpe275.model;

//import javax.*;//transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProfileDao extends CrudRepository<Profile, String> {

	public Profile findById(String id);

}
