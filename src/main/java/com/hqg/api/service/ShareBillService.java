package com.hqg.api.service;

import com.hqg.api.bean.CarOrder;
import com.hqg.api.bean.Customer;
import com.hqg.api.bean.ShareBill;
import com.hqg.api.repository.CarOrderRepository;
import com.hqg.api.repository.CarRepository;
import com.hqg.api.repository.CustomerRepository;
import com.hqg.api.repository.ShareBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Service
public class ShareBillService {
    @Autowired
    private ShareBillRepository shareBillRepository;
    @Autowired
    private CarOrderRepository carOrderRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;

    //完成拼单转订单

    @Transactional
    public void updateShareBillDate(Integer carId, Integer customerId,String saleConsultantName) {
        ShareBill sb=shareBillRepository.findByBothId(carId,customerId);
        sb.setState(1);
        shareBillRepository.save(sb);

        Map<String,Object> car=carRepository.findByIdCar(sb.getCarId());
       // String seriesName=car.get("seriesName").toString();
        CarOrder co=new CarOrder();
       // co.setSeriesName(car.get("seriesName").toString());
        co.setCarName(car.get("name").toString());
        co.setBrandName(car.get("brandName").toString());
        co.setTel(customerRepository.findById(customerId).get().getMobile());
        co.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        co.setSalesConsultantName(saleConsultantName);
        co.setIdCard("412726190002050606");
        co.setAppearanceColor(sb.getColor());
        co.setInteriorColor(sb.getColor());
        co.setCustomerName(customerRepository.findById(customerId).get().getName());
        co.setAddress(customerRepository.findById(customerId).get().getAddress());
        co.setPayType("0");
        carOrderRepository.save(co);
    }




}
