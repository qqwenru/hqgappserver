package com.hqg.api.repository;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BidCarDao {
    @Select("<script>select b.id as bidId,ct.name as customerName,ct.mobile as mobile,c.name as carName,b.submissionDate as submissionDate " +
            "from  bid_tbl b left join car c on b.carId = c.id left join customer ct on b.customerId = ct.id" +
            "<if test=\"name != null and mobile == null\"> where ct.name LIKE #{name}</if>" +
            "<if test=\"mobile != null and name == null\"> where ct.mobile = #{mobile}</if>" +
            "<if test=\"minTime != null and maxTime != null\">" +
            "<if test=\"name != null and mobile == null\"> where ct.name LIKE #{name} and b.submissionDate between #{minTime} and #{maxTime} </if>" +
            "<if test=\"name == null and mobile != null\"> where ct.mobile = #{mobile} and b.submissionDate between #{minTime} and #{maxTime} </if>" +
            "</if></script>")
    List<Map<String, Object>> findBidCarByCondition(Map<String, Object> map);
}
