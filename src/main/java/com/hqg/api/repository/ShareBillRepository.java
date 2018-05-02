package com.hqg.api.repository;

import com.hqg.api.bean.ShareBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ShareBillRepository  extends JpaRepository<ShareBill,Integer>,JpaSpecificationExecutor <ShareBill>{

    //获取拼单信息--用户id  和  carId
    @Query(value="select sb.*,cb.name as carBrandName,cb.pic as carBrandPic,css.brandId as brandId,css.name as carSericeName,css.pic as carSericePic, c.name as carName  from share_bill sb LEFT JOIN car c on sb.carId=c.id LEFT JOIN car_series css on c.carSriesId=css.id " +
            "LEFT JOIN  car_brand cb on css.brandId=cb.id  where carId = ?1 and customerId= ?2",nativeQuery = true)
    List<Map<String,Object>> findByCarIdAndCustomerId(Integer carId,Integer customerId);

    //报价列表  用户id
    @Query(value="select sb.*,cc.name as customerName,cb.name as carBrandName,cb.pic as carBrandPic,css.brandId as brandId,css.name as carSericeName,css.pic as carSericePic, c.name as carName  from share_bill sb LEFT JOIN car c on sb.carId=c.id  " +
            "LEFT JOIN car_series css on c.carSriesId=css.id  LEFT JOIN  car_brand cb on css.brandId=cb.id LEFT JOIN customer cc on sb.customerId=cc.id  where sb.customerId= ?1 and  sb.state= 0 ",nativeQuery = true)
    List<Map<String,Object>> findByCustomerId(Integer customerId);


    //修改状态  通过用户的customerId、 和汽车caiId
    /*@Modifying
    @Query(value="update  share_bill set state=1 where carId=?1 and customerId=?2",nativeQuery = true)
     ShareBill updateShareBillState(Integer carId,Integer customerId);*/

    //通过id获取sharebill 信息
    @Query(value="select * from share_bill where carId=?1 and customerId=?2",nativeQuery = true)
    ShareBill findByBothId(Integer carId,Integer customerId);



}
