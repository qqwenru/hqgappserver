package com.hqg.api.repository;

import com.hqg.api.bean.CarSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarSourceRepository extends JpaRepository<CarSource, Integer>,JpaSpecificationExecutor<CarSource> {
}
