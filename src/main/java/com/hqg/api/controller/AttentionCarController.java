package com.hqg.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hqg.api.bean.AttentionCar;
import com.hqg.api.bean.ResponseBean;
import com.hqg.api.exception.UnauthorizedException;
import com.hqg.api.repository.AttentionCarRepository;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attentionCar")
public class AttentionCarController {
    @Autowired
    private AttentionCarRepository attentionCarRepository;

    /**
     * 根据carId和customerId来查询，
     * 取消/增加关注
     */
    @PostMapping("findByCarIdAndCustomerId")
    @RequiresAuthentication
    public ResponseBean findByCarIdAndCustomerId(@RequestBody AttentionCar attentionCar) {
       if(attentionCar.getCarId() != null && attentionCar.getCustomerId()!=null){
           Map<String, Object> map = new HashMap<>();
           AttentionCar ac = attentionCarRepository.findByCarIdAndCustomerId(attentionCar.getCarId(), attentionCar.getCustomerId());
           if (ac == null) {
               attentionCar.setAttentionTime(new Date());
               attentionCarRepository.save(attentionCar);
               map.put("value",1);
               map.put("result","成功关注");
           } else {
               attentionCarRepository.delete(ac);
               map.put("value",0);
               map.put("result","取消关注");
           }
           return new ResponseBean(200, "success", map);
       }else{
           throw new UnauthorizedException();
       }
    }

    /**
     * 根据customerId来查询当前用户的关注列表
     */
    @PostMapping("/findListByCustomerId")
    @RequiresAuthentication
    public ResponseBean findListByCustomerId(@RequestBody String params) {
        JSONObject object = JSON.parseObject(params);
        Integer customerId = object.getInteger("customerId");
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = attentionCarRepository.findListByCustomerId(customerId);
        map.put("attentionCars",list);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 根据customerId,carId来批量删除
     */
    @PostMapping("/removeListByCustomerIdAndCarId")
    public ResponseBean removeListByCustomerIdAndCarId(@RequestBody String params) {
        JSONObject object = JSON.parseObject(params);
        Integer customerId = object.getInteger("customerId");
        JSONArray array = object.getJSONArray("carIds");
        for(int i=0;i<array.size();i++){
            Integer carId = array.getInteger(i);
            attentionCarRepository.deleteByCarIdAndCustomerId(carId,customerId);
        }
        return new ResponseBean(200, "success", "");
    }
}
