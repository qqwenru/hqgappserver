package com.hqg.api.service;

import com.hqg.api.repository.CarDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarService {
    @Resource
    private CarDao carDao;

    /**
     * 分页
     * @param type
     * @param brandId
     * @param carSource
     * @param minAllPrice
     * @param maxAllPrice
     * @param isNewEnergy
     * @return
     */
    public List<Map<String, Object>> findByCondition(Integer type,Integer brandId,Integer carSource ,Integer minAllPrice,Integer maxAllPrice,Integer isNewEnergy){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("brandId", brandId);
        map.put("carSource", carSource);
        map.put("minAllPrice", minAllPrice);
        map.put("maxAllPrice", maxAllPrice);
        map.put("isNewEnergy", isNewEnergy);
        List<Map<String, Object>> list = carDao.findByCondition(map);
        return list;
    }

    public List<Map<String, Object>> findPageByCondition(Integer type,Integer brandId,Integer carSource ,Integer minAllPrice,Integer maxAllPrice,Integer isNewEnergy,Integer startPos,Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("brandId", brandId);
        map.put("carSource", carSource);
        map.put("minAllPrice", minAllPrice);
        map.put("maxAllPrice", maxAllPrice);
        map.put("isNewEnergy", isNewEnergy);
        map.put("startPos", startPos);
        map.put("pageSize", pageSize);
        List<Map<String, Object>> list = carDao.findPageByCondition(map);
        return list;
    }




}



