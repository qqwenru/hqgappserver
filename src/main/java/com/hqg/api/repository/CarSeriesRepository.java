package com.hqg.api.repository;

import com.hqg.api.bean.CarSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CarSeriesRepository extends JpaRepository<CarSeries, Integer>,JpaSpecificationExecutor<CarSeries> {
    List<CarSeries> findByBrandId(Integer brandId);
    CarSeries findById(int id);

}
