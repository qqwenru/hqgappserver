package com.hqg.api.repository;

import com.hqg.api.bean.ActivityCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ActivityCarRepository extends JpaRepository<ActivityCar, Integer>,JpaSpecificationExecutor<ActivityCar> {
    @Query(value="SELECT ac.*,car.name AS name,car.carSriesId AS carSriesId,car.guidePrice AS guidePrice,cc.pic AS pic," +
            "cc.brandId AS brandId,ifnull(cc.concernNum,0) AS concernNum,cc.name AS carSriesName,ifnull(cc.saleNum,0) AS saleNum," +
            "ifnull(cdp.minNetPrice,0) as minNetPrice,ifnull(cdp.maxNetPrice,0) as maxNetPrice, " +
            "ifnull(cdp.minAllPrice,0) as minAllPrice,ifnull(cdp.maxAllPrice,0) as maxAllPrice,ifnull(cdp.quotedPriceNum,0) as quotedPriceNum " +
            "FROM activity_car ac LEFT JOIN car ON ac.carId = car.id AND car.state = 0  " +
            "LEFT JOIN(select cs.*,ifnull(csc.saleNum,0) as saleNum,ifnull(csc.concernNum,0) as concernNum from car_series cs " +
            "left join car_series_count csc on cs.id = csc.carSeriesId where state = 0 order by csc.saleNum ,cs.id)cc  " +
            "ON cc.id = car.carSriesId LEFT JOIN (SELECT carId,MIN(netPrice) AS minNetPrice,MAX(netPrice) AS maxNetPrice, " +
            "MIN(allPrice) AS minAllPrice,MAX(allPrice) AS maxAllPrice,COUNT(carId)AS quotedPriceNum " +
            "FROM quoted_price group by carId) cdp ON  ac.carId= cdp.carId",nativeQuery = true)
    List<Map<String,Object>> listActivityCar();
}
