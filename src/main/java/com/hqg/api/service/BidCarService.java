package com.hqg.api.service;

import com.hqg.api.repository.BidCarDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BidCarService {
    @Resource
    private BidCarDao bidCarDao;

    public List<Map<String, Object>> findBidCarByCondition(String name, String mobile, Date minTime,Date maxTime){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("mobile", mobile);
        map.put("minTime", minTime);
        map.put("maxTime", maxTime);
        return  bidCarDao.findBidCarByCondition(map);
    }
}
