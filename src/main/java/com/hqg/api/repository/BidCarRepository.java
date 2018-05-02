package com.hqg.api.repository;

import com.hqg.api.bean.BidCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface BidCarRepository extends JpaRepository<BidCar, Integer>,JpaSpecificationExecutor<BidCar> {
    @Query(value="select bd.*,d.dealerName as dealerName ,c.`name` as cityName from biddata_tbl bd left join bid_tbl b on b.id = bd.bidId " +
           "left join dealer_info d on d.id = bd.dealerId left join city c on c.id = d.cityId " +
           "where bd.bidId = ?1",nativeQuery = true)
    List<Map<String, Object>> findByBidId(Integer bidId);

    @Query(value="SELECT c.id as carId,c.name as carName,cs.pic as pic " +
            "from car c LEFT JOIN car_series cs on c.carSriesId=cs.id " +
            "where c.id in(SELECT DISTINCT b.carId from bid_tbl b)",nativeQuery = true)
    List<Map<String, Object>> listCarInfo();

    @Query(value="SELECT di.id as dealerId,ct.`name` as cityName,di.dealerName as dealerName,r.price as latePrice " +
            "from dealer_info di " +
            "LEFT JOIN city ct on ct.id = di.cityId " +
            "LEFT JOIN dealer_brandid db on di.id = db.dealerId " +
            "LEFT JOIN car_series cs on db.brandId = cs.brandId " +
            "LEFT JOIN car c  on c.carSriesId=cs.id " +
            "LEFT JOIN (SELECT * FROM `quotation_record` ORDER BY createTime DESC limit 1 )AS r on di.id = r.dealerId " +
            "WHERE c.id = ?1",nativeQuery = true)
    List<Map<String, Object>> findByCarId(Integer CarId);


    @Query(value="SELECT b.id,b.submissionDate,c.id as carId,c.name as carName," +
            "cs.pic as pic,ct.`name` as customerName,ct.mobile as mobile " +
            "from bid_tbl b LEFT JOIN car c on b.carId = c.id " +
            "LEFT JOIN car_series cs on c.carSriesId=cs.id " +
            "LEFT JOIN customer ct on b.customerId = ct.id",nativeQuery = true)
    List<Map<String, Object>> listAllCarInfo();

    @Query(value="SELECT di.id as dealerId,ct.`name` as cityName,di.dealerName as dealerName,r.price as latePrice " +
            "from dealer_info di " +
            "LEFT JOIN city ct on ct.id = di.cityId " +
            "LEFT JOIN dealer_brandid db on di.id = db.dealerId " +
            "LEFT JOIN car_series cs on db.brandId = cs.brandId " +
            "LEFT JOIN car c  on c.carSriesId=cs.id " +
            "LEFT JOIN (SELECT * FROM `quotation_record` ORDER BY createTime DESC limit 1 )AS r on di.id = r.dealerId " +
            "WHERE c.id = ?1",nativeQuery = true)
    List<Map<String, Object>> findByAllCarId(Integer CarId);

    @Query(value="SELECT bt.*,c.name as carName,c.guidePrice as guidePrice " +
            "FROM `bid_tbl` bt LEFT JOIN car c on c.id = bt.carId WHERE bt.customerId=?1 and bt.state != 1 and bt.state != 4",nativeQuery = true)
    List<Map<String, Object>> findListByCustomerId(Integer customerId);

    @Modifying
    @Query("update BidCar b set b.state = ?3 WHERE b.customerId = ?1 and b.carId = ?2 ")
    void updateStateByCustomerIdAndCarId(Integer customerId,Integer carId,Integer state);

    BidCar findByCustomerIdAndCarId(Integer customerId,Integer carId);
}
