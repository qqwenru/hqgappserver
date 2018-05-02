package com.hqg.api.repository;

import com.hqg.api.bean.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarBrandRepository extends JpaRepository<CarBrand, Integer>,JpaSpecificationExecutor<CarBrand> {
    List<CarBrand> findByFirstWord(String firstWord);
}
