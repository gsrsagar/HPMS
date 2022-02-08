package com.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.pojo.Files;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<Files, Long> {
   // commit() , rollback() , savepoint() -> save()-> insert -> commit() , savePoint() ,  exception , save() ->revert back
  // amazon -> order checkout -> payment -> opt -> order success ->  

	Files findById(long id);
	
	boolean deleteById(long id);

}
