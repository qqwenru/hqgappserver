package com.hqg.api.repository;

import com.hqg.api.bean.AttentionCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface AttentionCarRepository extends JpaRepository<AttentionCar, Integer>,JpaSpecificationExecutor<AttentionCar> {

    AttentionCar findByCarIdAndCustomerId(Integer carId,Integer customerId);

    void deleteByCarIdAndCustomerId(Integer carId,Integer customerId);

    @Query(value="select att.*,IFNULL(c.minAllPrice,0) as minAllPrice,IFNULL(c.quotedPriceNum,0) as quotedPriceNum ,cc.name from attentioncar_tbl att LEFT JOIN  " +
            "(SELECT carId,MIN(allPrice) AS minAllPrice,COUNT(carSeriesId)AS quotedPriceNum FROM quoted_price group by carId )as c on att.carId = c.carId LEFT JOIN " +
            "car cc on cc.id =att.carId where customerId=${id}",nativeQuery = true)
    List<Map<String,Object>> findListByCustomerId(Integer customerId);
}
