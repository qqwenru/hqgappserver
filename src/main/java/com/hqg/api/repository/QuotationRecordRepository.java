package com.hqg.api.repository;

import com.hqg.api.bean.QuotationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface QuotationRecordRepository extends JpaRepository<QuotationRecord, Integer>,JpaSpecificationExecutor<QuotationRecord> {
    @Query(value=" SELECT * from (SELECT q.*,c.name as cityName FROM quotation_record q LEFT JOIN city c on q.cityId = c.id  " +
            "WHERE carId = ?1 ORDER BY createTime DESC  )cc GROUP BY cc.dealerId ",nativeQuery = true)
    List<Map<String, Object>> findByCarId(Integer carId);
}
