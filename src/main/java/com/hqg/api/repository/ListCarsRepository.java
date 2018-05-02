package com.hqg.api.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hqg.api.bean.Car;

public interface ListCarsRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {
	@Query(value="select * from car",nativeQuery = true)
    List<Map<String,Object>> listCars();
	
	@Modifying
	@Delete(value="delete from car where id=?")
	void deleteById(@Param("id") Integer id);
}
