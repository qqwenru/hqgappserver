package com.hqg.api.repository;

import com.hqg.api.bean.CarOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarOrderRepository extends JpaRepository<CarOrder, Integer>,JpaSpecificationExecutor<CarOrder> {

    Page<CarOrder> findByTel(String tel, Pageable pageable);
}
