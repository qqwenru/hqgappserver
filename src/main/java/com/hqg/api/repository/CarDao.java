package com.hqg.api.repository;

import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

public interface CarDao {
    @Select("<script>select cc.*,cb.name as brandname,c.carSource as carSource,ifnull(c.minGuidePrice,0) as minGuidePrice,"
            + "ifnull(c.maxGuidePrice,0) as maxGuidePrice,ifnull(c.carNum,0) as carNum,"
            + "ifnull(cdp.minNetPrice,0) as minNetPrice,ifnull(cdp.maxNetPrice,0) as maxNetPrice,"
            + "ifnull(cdp.minAllPrice,0) as minAllPrice,ifnull(cdp.maxAllPrice,0) as maxAllPrice,"
            + "ifnull(cdp.quotedPriceNum,0) as quotedPriceNum,ifnull(newE.newEnergyNum,0) as newEnergyNum from(select cs.*,ifnull(csc.saleNum,0) as saleNum,"
            + "ifnull(csc.concernNum,0) as concernNum from car_series cs "
            + "left join car_series_count csc on cs.id = csc.carSeriesId where state = 0 order by csc.saleNum ,cs.id) cc left join car_brand cb on "
            + "cb.id = cc.brandid left join (SELECT count(*) AS carNum,carSriesId,carSource ,MAX(guidePrice) AS maxGuidePrice,"
            + "MIN(guidePrice) AS minGuidePrice FROM car GROUP BY carSriesId) c on c.carSriesId = cc.id LEFT JOIN(SELECT carSeriesId,MIN(netPrice) AS minNetPrice,MAX(netPrice) AS maxNetPrice,"
            + "MIN(allPrice) AS minAllPrice,MAX(allPrice) AS maxAllPrice,COUNT(carSeriesId)AS quotedPriceNum FROM quoted_price group by carSeriesId) cdp ON "
            + "cc.id = cdp.carSeriesId left JOIN (select count(*) as newEnergyNum,carSriesId from car  WHERE isNewEnergy = 1 group by carSriesId) AS newE ON  c.carSriesId= newE.carSriesId "
            + "WHERE carNum>0 "
            + "<if test=\"type != null\">AND cc.type = #{type} </if>"
            + "<if test=\"brandId != null\">AND cc.brandId = #{brandId} </if> "
            + "<if test=\"carSource != null\">AND c.carSource = #{carSource} </if> "
            + "<if test=\"minAllPrice != null and maxAllPrice != null \">AND c.minGuidePrice &gt; #{minAllPrice} AND c.maxGuidePrice &lt; #{maxAllPrice} </if> "
            + "<if test=\"isNewEnergy != null\"> AND newEnergyNum > 0 </if>"
            + "limit #{startPos},#{pageSize}</script> ")
    List<Map<String, Object>> findPageByCondition(Map<String, Object> map);

    @Select("<script>select cc.*,cb.name as brandname,c.carSource as carSource,ifnull(c.minGuidePrice,0) as minGuidePrice,"
            + "ifnull(c.maxGuidePrice,0) as maxGuidePrice,ifnull(c.carNum,0) as carNum,"
            + "ifnull(cdp.minNetPrice,0) as minNetPrice,ifnull(cdp.maxNetPrice,0) as maxNetPrice,"
            + "ifnull(cdp.minAllPrice,0) as minAllPrice,ifnull(cdp.maxAllPrice,0) as maxAllPrice,"
            + "ifnull(cdp.quotedPriceNum,0) as quotedPriceNum,ifnull(newE.newEnergyNum,0) as newEnergyNum from(select cs.*,ifnull(csc.saleNum,0) as saleNum,"
            + "ifnull(csc.concernNum,0) as concernNum from car_series cs "
            + "left join car_series_count csc on cs.id = csc.carSeriesId where state = 0 order by csc.saleNum ,cs.id) cc left join car_brand cb on "
            + "cb.id = cc.brandid left join (SELECT count(*) AS carNum,carSriesId,carSource ,MAX(guidePrice) AS maxGuidePrice,"
            + "MIN(guidePrice) AS minGuidePrice FROM car GROUP BY carSriesId) c on c.carSriesId = cc.id LEFT JOIN(SELECT carSeriesId,MIN(netPrice) AS minNetPrice,MAX(netPrice) AS maxNetPrice,"
            + "MIN(allPrice) AS minAllPrice,MAX(allPrice) AS maxAllPrice,COUNT(carSeriesId)AS quotedPriceNum FROM quoted_price group by carSeriesId) cdp ON "
            + "cc.id = cdp.carSeriesId left JOIN (select count(*) as newEnergyNum,carSriesId from car  WHERE isNewEnergy = 1 group by carSriesId) AS newE ON  c.carSriesId= newE.carSriesId "
            + "WHERE carNum>0 "
            + "<if test=\"type != null\">AND cc.type = #{type} </if>"
            + "<if test=\"brandId != null\">AND cc.brandId = #{brandId} </if> "
            + "<if test=\"carSource != null\">AND c.carSource = #{carSource} </if> "
            + "<if test=\"minAllPrice != null and maxAllPrice != null \">AND c.minGuidePrice &gt; #{minAllPrice} AND c.maxGuidePrice &lt; #{maxAllPrice} </if> "
            + "<if test=\"isNewEnergy != null\"> AND newEnergyNum > 0 </if></script> ")
    List<Map<String, Object>> findByCondition(Map<String, Object> map);


}
