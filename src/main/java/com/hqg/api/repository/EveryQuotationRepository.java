package com.hqg.api.repository;

import com.hqg.api.bean.EveryQuotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface EveryQuotationRepository extends JpaRepository<EveryQuotation,Integer>,JpaSpecificationExecutor<EveryQuotation> {

    //获取每日报价的全部信息




    //根据汽车id 获取报价信息
    @Query(value="select ss.id as everyquotId,ss.carId as evQuCarId,ss.offerId as offerId,ss.quotDate as quotationDate,ss.remark as evQuRemark, " +
            "aa.* from share_bill_every_quotation  ss LEFT JOIN (SELECT sb.id as sbId,sb.carId as carId,sb.color as sbColor,sb.preferential  " +
            "as preferential,sb.remark as sbRemark," +
            "sb.state as shareBillState,sb.submissionDate as submissionDate,sb.bidid as bidId,cb.name as brandName,cb.pic as brandPic,cb.firstWord  " +
            "as firstWord,cb.state as brandState,cs.brandId as brandId,cs.name as seriesName,cs.pic as seriesPic,cs.state as seriesState, " +
            "cs.type as seriesType ,c.name as carName,c.carSriesId as carSriesId,c.guidePrice as guidePrice,c.state as carState,c.carSource " +
            "as carSource, c.appearanceColor as appearanceColor,c.interiorColor as interiorColor,c.isNewEnergy as isNewEnergy FROM share_bill " +
            "sb LEFT JOIN car c on c.id = sb.carId LEFT JOIN car_series cs on cs.id = c.carSriesId LEFT JOIN car_brand cb on  " +
            "cb.id = cs.brandId ORDER BY firstWord ASC ) as aa on ss.carId=aa.carId where ss.carId=?1",nativeQuery = true)
    List<Map<String,Object>> findByCarId(Integer carId);

}
