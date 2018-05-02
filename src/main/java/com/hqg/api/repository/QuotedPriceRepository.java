package com.hqg.api.repository;

import com.hqg.api.bean.QuotedPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface QuotedPriceRepository extends JpaRepository<QuotedPrice, Integer>,JpaSpecificationExecutor<QuotedPrice> {
    @Query(value="SELECT qp.* ,city.`name` AS cityName,d.dealerName as dealerName FROM  quoted_price  qp " +
            "LEFT JOIN city ON city.id = qp.cityId  " +
            "LEFT JOIN dealer_info d on d.id = qp.dealerId " +
            "WHERE carId=?1",nativeQuery = true)
    List<Map<String,Object>> findByCarId(Integer carID);
}
