package com.hqg.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqg.api.bean.BidData;
import com.hqg.api.bean.ResponseBean;
import com.hqg.api.repository.BidCarRepository;
import com.hqg.api.repository.BidDataRepository;
import com.hqg.api.service.BidCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 后台竞价车型
 */
@RestController
@RequestMapping("/bidCar")
public class BidCarController {
    @Autowired
    private BidCarRepository bidCarRepository;
    @Autowired
    private BidCarService bidCarService;
    @Autowired
    private BidDataRepository bidDataRepository;
    /**
     * 历史竞价
     *
     * @param params
     * @return
     */
    @PostMapping("listBidCarAndCarDataRecord")
    public ResponseBean listBidCarAndCarDataRecord(@RequestBody String params) {
        JSONObject object = JSON.parseObject(params);
        String name = object.getString("name");
        name = "%" + name + "%";
        String mobile = object.getString("mobile");
        Date minTime = object.getDate("minTime");
        Date maxTime = object.getDate("maxTime");
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> ll = new ArrayList<>();
        List<Map<String, Object>> list = bidCarService.findBidCarByCondition(name, mobile, minTime, maxTime);
        for (Map<String, Object> m : list) {
            Integer bidId = Integer.parseInt(m.get("bidId").toString());
            List<Map<String, Object>> li = bidCarRepository.findByBidId(bidId);
            Map<String, Object> mp = new HashMap<>();
            for (String k : m.keySet()) {
                mp.put(k, m.get(k));
            }
            mp.put("datas", li);
            ll.add(mp);
        }
        map.put("bidCars", ll);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 最近报价
     *
     * @return
     */
    @GetMapping("listBidData")
    public ResponseBean listBidData() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> ll = new ArrayList<>();
        List<Map<String, Object>> list = bidCarRepository.listAllCarInfo();
        for (Map<String, Object> m : list) {
            Integer carId = Integer.parseInt(m.get("carId").toString());
            List<Map<String, Object>> li = bidCarRepository.findByCarId(carId);
            Map<String, Object> mp = new HashMap<>();
            for (String k : m.keySet()) {
                mp.put(k, m.get(k));
            }
            mp.put("dealerInfo", li);
            ll.add(mp);
        }
        map.put("BidData", ll);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 保存List<QuotationRecord>
     * @param BidDatas
     * @return
     */
    @PostMapping("saveBidDatas")
    public ResponseBean saveBidDatas(@RequestBody List<BidData> BidDatas) {
        for(int i = 0 ; i < BidDatas.size() ; i++) {
            if(bidDataRepository.findByBidId(BidDatas.get(i).getBidId()) != null ){
                bidDataRepository.deleteByBidId(BidDatas.get(i).getBidId());
            }
            BidDatas.get(i).setSubmissionDate(new Date());
        }
        bidDataRepository.saveAll(BidDatas);
        return new ResponseBean(200, "success", null);
    }


}
