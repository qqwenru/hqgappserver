package com.hqg.api.repository;

import com.hqg.api.bean.CarColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarColorRepository extends JpaRepository<CarColor, Integer>,JpaSpecificationExecutor<CarColor> {
    CarColor findByCarSriesId(Integer carSriesId);
}
