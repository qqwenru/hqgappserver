package com.hqg.api.repository;

import com.hqg.api.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

   Customer findByMobile(String mobile);

   Customer findByMobileAndPassword(String mobile,String password);

   @Query(value = "SELECT id, name, mobile, tel, payType, buyData, level, source, assignType, state,"
           + " assigner, remark, isFirstBuy, idcard, address, registrationTime, sex FROM customer WHERE "+
           " mobile = ?1 and password= ?2", nativeQuery = true)
   Map<String, Object> findByUsernameAndAndPasswordToSql(String mobile, String password);


}
