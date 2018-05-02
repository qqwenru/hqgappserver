package com.hqg.api.repository;

import com.hqg.api.bean.ParameterGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParameterGroupRepository extends JpaRepository<ParameterGroup, Integer>,JpaSpecificationExecutor<ParameterGroup> {

}
