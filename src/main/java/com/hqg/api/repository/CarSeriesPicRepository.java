package com.hqg.api.repository;

import com.hqg.api.bean.CarSeriesPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarSeriesPicRepository extends JpaRepository<CarSeriesPic, Integer>,JpaSpecificationExecutor<CarSeriesPic> {
    List<CarSeriesPic> findByCarSriesId(Integer carSriesId);
}
