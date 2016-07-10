package com.wht.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wht.system.model.Function;


public interface FunctionRepository extends JpaRepository<Function, Long> {

	
	@Query("from Function a where a.parentID=:parentID and a.type=:type order by a.orderNo ")
	public List<Function> findByParentIdOrderByOrderNo(@Param("parentID") Long parentID,@Param("type")Integer type);
	

	
}
