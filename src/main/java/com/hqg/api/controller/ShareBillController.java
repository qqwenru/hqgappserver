package com.hqg.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqg.api.bean.CarOrder;
import com.hqg.api.bean.ResponseBean;
import com.hqg.api.repository.ShareBillRepository;
import com.hqg.api.service.ShareBillService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/shareBill")
public class ShareBillController {

    @Autowired
    private ShareBillRepository shareBillRepository;
    @Autowired
    private ShareBillService shareBillService;


    //通过carId  customerId 查询拼单信息
    @PostMapping(value = "/findByCarIdAndCustomerId")
    public ResponseBean findByCarIdAndCustomerId(@RequestBody String params){
        JSONObject obj = JSON.parseObject(params);
        Integer  carId = obj.getInteger("carId");
        Integer  customerId = obj.getInteger("customerId");
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> aa = shareBillRepository.findByCarIdAndCustomerId(carId,customerId);
        map.put("shareBills",aa);
        return new ResponseBean(200, "success", map);
    }

    // 根据用户customerId  查询列表
    @PostMapping("/findByCustomerId")
    public ResponseBean findByCustomerId(@RequestBody String params){
        JSONObject obj = JSON.parseObject(params);
        Integer  customerId = obj.getInteger("customerId");
        Map<String,Object> map=new HashMap<>();
        List<Map<String,Object>> cs=shareBillRepository.findByCustomerId(customerId);
        map.put("everyQuotationMessage",cs);
        return new ResponseBean(200,"success",map);
    }

    //完成拼单转订单
    @PostMapping("/updateShareBillState")
    public ResponseBean updateShareBillState(@RequestBody String params){
        JSONObject object=JSON.parseObject(params);
        Integer carId=object.getInteger("carId");
        Integer customerId=object.getInteger("customerId");
        String saleConsultantName=object.getString("saleConsultantName");
        Map<String,Object> map=new HashedMap();
        shareBillService.updateShareBillDate(carId,customerId,saleConsultantName);
        return new ResponseBean(200,"success",map);
    }


}
