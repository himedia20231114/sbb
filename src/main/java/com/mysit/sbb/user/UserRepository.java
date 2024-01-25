package com.mysit.sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	/*
	 	select : findAll(), findById()
	 	insert : save() 
	 	update : save() 
	 	delete : delete()  
	 	
	 */
	
}