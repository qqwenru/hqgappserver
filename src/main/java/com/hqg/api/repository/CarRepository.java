package com.hqg.api.repository;

import com.hqg.api.bean.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CarRepository extends JpaRepository<Car, Integer>,JpaSpecificationExecutor<Car> {
    List<Car> findByCarSriesId(Integer carSriesId );

    @Query(value="select cc.*,cb.name as brandname,c.carSource as carSource,ifnull(c.minGuidePrice,0) as minGuidePrice," +
            "           ifnull(c.maxGuidePrice,0) as maxGuidePrice,ifnull(c.carNum,0) as carNum," +
            "           ifnull(cdp.minNetPrice,0) as minNetPrice,ifnull(cdp.maxNetPrice,0) as maxNetPrice," +
            "           ifnull(cdp.minAllPrice,0) as minAllPrice,ifnull(cdp.maxAllPrice,0) as maxAllPrice," +
            "           ifnull(cdp.quotedPriceNum,0) as quotedPriceNum,ifnull(newE.newEnergyNum,0) as newEnergyNum from(select cs.*,ifnull(csc.saleNum,0) as saleNum," +
            "           ifnull(csc.concernNum,0) as concernNum from car_series cs " +
            "           left join car_series_count csc on cs.id = csc.carSeriesId where state = 0 order by csc.saleNum ,cs.id) cc left join car_brand cb on " +
            "           cb.id = cc.brandid left join (SELECT count(*) AS carNum,carSriesId,carSource ,MAX(guidePrice) AS maxGuidePrice," +
            "           MIN(guidePrice) AS minGuidePrice FROM car GROUP BY carSriesId) c on c.carSriesId = cc.id LEFT JOIN(SELECT carSeriesId,MIN(netPrice) AS minNetPrice,MAX(netPrice) AS maxNetPrice," +
            "           MIN(allPrice) AS minAllPrice,MAX(allPrice) AS maxAllPrice,COUNT(carSeriesId)AS quotedPriceNum FROM quoted_price group by carSeriesId) cdp ON " +
            "           cc.id = cdp.carSeriesId left JOIN (select count(*) as newEnergyNum,carSriesId from car  WHERE isNewEnergy = 1 group by carSriesId) AS newE ON  c.carSriesId= newE.carSriesId " +
            "           WHERE cc.name like ?1 and carNum > 0",nativeQuery = true)
    List<Map<String,Object>> findLikeSeriesName(String SeriesName);

    @Query(value="select cid.carId,cid.parameterId,cip.`name`,cid.content from car_info_data cid LEFT JOIN car_info_parameter cip on cid.parameterId = cip.id \n" +
            "where cid.carId = ?1 and cip.groupId=?2",nativeQuery = true)
    List<Map<String,Object>> findParameter(Integer carId,Integer groupId);

    @Query(value="select car.*,ifnull(qp.minNetPrice,0)as minNetPrice,ifnull(qp.minAllPrice,0)as minAllPrice from car " +
            "LEFT JOIN (select carId,carSeriesId,min(netPrice) as minNetPrice,min(allPrice) as minAllPrice from quoted_price " +
            "group by carId)qp ON car.id = qp.carId where carSriesId = ?1" +
            "and state = 0 ORDER BY qp.minNetPrice DESC",nativeQuery = true)
    List<Map<String,Object>> findCarsByCarSriesId(Integer carSriesId );//暂时没有用

    @Query(value = "select c.*,cs.name as carSeries,cs.pic as seriesPic,cb.id as brandId,cb.`name` as brandName ,cb.pic as brandPic from car c " +
            "LEFT JOIN car_series cs on c.carSriesId= cs.id LEFT JOIN car_brand cb on cs.brandId=cb.id where c.id= ?1",nativeQuery = true)
    Map<String,Object> findByIdCar(Integer carId);
}


