package com.hqg.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqg.api.bean.EveryQuotation;
import com.hqg.api.bean.ResponseBean;
import com.hqg.api.repository.EveryQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/everyQuotation")
public class EveryQuotationController {
    @Autowired
    private EveryQuotationRepository everyQuotationRepository;
    // 获取全部的每日报价信息
    @GetMapping(value="/findAllEveryQuote")
    public ResponseBean findAllEveryQuote(){
        Map<String,Object> map=new HashMap<>();
        List<EveryQuotation> eqs=everyQuotationRepository.findAll();
        map.put("everyQuotations",eqs);
        return new ResponseBean(200,"success",map);
    }

    // 根据carId获取报价信息
    @PostMapping(value="/findByCarId")
    public ResponseBean findByCarId(@RequestBody String params) {
        JSONObject object = JSON.parseObject(params);
        Integer carId=object.getInteger("carId");
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> eqs = everyQuotationRepository.findByCarId(carId);
        map.put("everyQuotations", eqs);
        return new ResponseBean(200, "success", map);
    }
}
