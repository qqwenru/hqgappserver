package com.hqg.api.service;

import com.hqg.api.bean.BidCar;
import com.hqg.api.bean.PaymentRecord;
import com.hqg.api.repository.BidCarRepository;
import com.hqg.api.repository.PaymentRecordRepository;

import com.hqg.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BidEventService {
    @Autowired
    private BidCarRepository bidCarRepository;
    @Autowired
    private PaymentRecordRepository paymentRecordRepository;

    @Transactional
    public void saveBidCarAndPaymentRecord (Integer customerId,Integer carId,String color,String remark,
                                            Integer salerId,Integer price){
        BidCar bc = new BidCar();
        bc.setCustomerId(customerId);
        bc.setCarId(carId);
        bc.setColor(color);
        bc.setRemark(remark);
        bc.setSalerId(salerId);
        bc.setSubmissionDate(new Date());
        bc.setState(0);
        bidCarRepository.save(bc);
        PaymentRecord p = new PaymentRecord();
        p.setBidId(bc.getId());
        p.setPayType("订金");
        p.setPrice(price);
        p.setState(0);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String times[] = dateFormat.format(now).split("-");
        String time = times[0]+times[1]+times[2];
        String orderSn =time + StringUtil.getClient_Sn(6);
        p.setPaymentNumber(orderSn);
        paymentRecordRepository.save(p);
    }

}
