package com.hqg.api.repository;

import com.hqg.api.bean.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarTypeRepository extends JpaRepository<CarType, Integer>,JpaSpecificationExecutor<CarType> {
}
