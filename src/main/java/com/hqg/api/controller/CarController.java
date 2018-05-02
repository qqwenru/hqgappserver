package com.hqg.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hqg.api.bean.*;
import com.hqg.api.exception.UnauthorizedException;
import com.hqg.api.repository.*;
import com.hqg.api.service.CarService;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarBrandRepository carBrandRepository;
    @Autowired
    private CarSourceRepository carSourceRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private CarSeriesRepository carSeriesRepository;
    @Autowired
    private QuotationRecordRepository quotationRecordRepository;
    @Autowired
    private ParameterGroupRepository parameterGroupRepository;
    @Autowired
    private QuotedPriceRepository quotedPriceRepository;
    @Autowired
    private CarSeriesPicRepository carSeriesPicRepository;
    @Autowired
    private ActivityCarRepository activityCarRepository;
    @Autowired
    private AttentionCarRepository attentionCarRepository;
    @Autowired
    private ListCarsRepository listCarsRepository;
    /**
     * 根据首字母（FirstWord）查询汽车品牌列表CarBrand
     * @return
     */
    @PostMapping("/findBrandByFirstWord")
    @RequiresAuthentication
    public ResponseBean findByFirstWord(@RequestBody CarBrand carBrand){
        if(carBrand != null){
            Map<String, Object> map = new HashMap<>();
            List<CarBrand> list = carBrandRepository.findByFirstWord(carBrand.getFirstWord());
            map.put("carBrands",list);
            return new ResponseBean(200, "success", map);
        }else{
            throw new UnauthorizedException();
        }
    }

    /**
     * 根据BrandId查询汽车系列CarSeries
     * @return
     */
    @PostMapping("/findCarSeriesByBrandId")
    public ResponseBean findCarSeriesByBrandId(@RequestBody CarSeries carSeries){
        if(carSeries != null){
            Map<String, Object> map = new HashMap<>();
            List<CarSeries> list = carSeriesRepository.findByBrandId(carSeries.getBrandId());
            map.put("carSeries",list);
            return new ResponseBean(200, "success", map);
        }else{
            throw new UnauthorizedException();
        }
    }

    /**
     * 根据CarSriesId查询汽车系列Car
     * @return
     */
    @PostMapping("/findCarByCarSriesId")
    public ResponseBean findCarByCarSriesId(@RequestBody Car car){
        if(car != null){
            Map<String, Object> map = new HashMap<>();
            List<Car> list = carRepository.findByCarSriesId(car.getCarSriesId());
            map.put("cars",list);
            return new ResponseBean(200, "success", map);
        }else{
            throw new UnauthorizedException();
        }
    }

    /**
     * 根据CarId查询QuotationRecord
     * @return
     */
    @PostMapping("/findQuotationRecordByCarId")
    public ResponseBean findQuotationRecordByCarId(@RequestBody QuotationRecord quotationRecord){
        if(quotationRecord != null){
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> list = quotationRecordRepository.findByCarId(quotationRecord.getCarId());
            map.put("quotationRecords",list);
            return new ResponseBean(200, "success", map);
        }else{
            throw new UnauthorizedException();
        }
    }

    /**
     * 汽车品牌列表
     * @return
     */
    @GetMapping("/listBrand")
    public ResponseBean listBrand() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<CarBrand> listAll = carBrandRepository.findAll();
            JSONArray ja = new JSONArray();
            Map<String, JSONArray> mapBrand = new HashMap<String, JSONArray>();
            for (int i = 65; i <= 90; i++) {
                char firstWord = (char) i;
                mapBrand.put(String.valueOf(firstWord), new JSONArray());
            }
            for (int i = 0; i < listAll.size(); i++) {
                CarBrand carBrand = listAll.get(i);
                String firstWord = carBrand.getFirstWord();
                JSONArray jsonArray = mapBrand.get(firstWord.toUpperCase());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", carBrand.getId());
                jsonObject.put("cName", carBrand.getName());
                jsonObject.put("pic", carBrand.getPic());
                jsonArray.add(jsonObject);
            }
            for (String key : mapBrand.keySet()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("firstWord", key);
                jsonObject.put("carBrands", mapBrand.get(key));
                ja.add(jsonObject);
            }
            map.put("data", ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return new ResponseBean(200, "success", map);
    }

    /**
     * 出产国家列表
     * @return
     */
    @GetMapping("/listCarSource")
    public ResponseBean listCarSource(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<CarSource>  list = carSourceRepository.findAll();
        map.put("carSources", list);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 车辆类型列表
     * @return
     */
    @GetMapping("/listCarType")
    public ResponseBean listCarType(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<CarType>  list = carTypeRepository.findAll() ;
        map.put("carTypes", list);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 车型列表
     * @return
     */
    @GetMapping("/listAll")
    public ResponseBean findAll(){
        Map<String, Object> map = new HashMap<>();
        List<Car> list = carRepository.findAll();
        map.put("cars",list);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 根据（type,brandId,carSource,minAllPrice,maxAllPrice,isNewEnergy）
     * 筛选车信息
     * @param params
     * @return
     */
    @PostMapping("/findByCondition")
    public ResponseBean findByCondition(@RequestBody String params){
        JSONObject object = JSON.parseObject(params);
        Integer type = object.getInteger("type");
        Integer brandId =  object.getInteger("brandId");
        Integer carSource =  object.getInteger("carSource");
        Integer minAllPrice = object.getInteger("minAllPrice");
        Integer maxAllPrice =  object.getInteger("maxAllPrice");
        Integer isNewEnergy = object.getInteger("isNewEnergy");
        Integer pageNow = object.getInteger("pageNow");
        Integer pageSize = object.getInteger("pageSize");
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = carService.findByCondition(type,brandId,carSource,minAllPrice,maxAllPrice,isNewEnergy);
        List<Map<String, Object>> li;
        Page page = null;
        int totalCount = list.size();
        if (pageNow != null) {
            page = new Page(totalCount, pageNow);
            page.setPageSize(pageSize);
            li = carService.findPageByCondition(type,brandId,carSource,minAllPrice,maxAllPrice,isNewEnergy,page.getStartPos(), page.getPageSize());
        }else {
            page = new Page(totalCount, 1);
            page.setPageSize(pageSize);
            li = carService.findPageByCondition(type,brandId,carSource,minAllPrice,maxAllPrice,isNewEnergy,page.getStartPos(), page.getPageSize());
        }
        map.put("carInfo",li);
        map.put("page",page);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 根据SeriesName
     * 模糊查询车信息
     * @param params
     * @return
     */
    @PostMapping("/findLikeSeriesName")
    public ResponseBean findLikeSeriesName(@RequestBody String params) {
        JSONObject object = JSON.parseObject(params);
        String name = object.getString("name");
        name = "%"+name+"%";
        List<Map<String, Object>> list = carRepository.findLikeSeriesName(name);
        Map<String, Object> map = new HashMap<>();
        map.put("carInfos",list);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 详情页(必传carSeriesId,customerId，可传carId)
     * 根据carId和customerId来查询是否关注
     * @param params
     * @return
     */
    @PostMapping("/findParameterAndPrice")
    //@RequiresAuthentication
    public ResponseBean findParameterAndPrice(@RequestBody String params) {
        JSONObject object = JSON.parseObject(params);
        Integer carId = object.getInteger("carId");
        Integer carSriesId = object.getInteger("carSeriesId");
        Integer customerId = object.getInteger("customerId");
        List<Map<String, Object>> ll = new ArrayList<>();
        List<Car> cars = carRepository.findByCarSriesId(carSriesId);
        CarSeries carSeries = carSeriesRepository.findById(carSriesId.intValue());
        List<CarSeriesPic> pics = carSeriesPicRepository.findByCarSriesId(carSriesId);
        Car currentCar = new Car();
        if(carId == null ){
            carId = cars.get(0).getId();
        }
        for(int i = 0 ; i < cars.size() ; i++){
            Car car = cars.get(i);
            if(carId.equals(car.getId())){
                currentCar = car;
            }
        }
        CarSeriesPic pic = new CarSeriesPic();
        pic.setPicPath((String)carSeries.getPic());
        pics.add(0,pic);
        List<Map<String, Object>> price = quotedPriceRepository.findByCarId(carId);
        List<ParameterGroup> groups = parameterGroupRepository.findAll();
        for(int i = 0 ; i < groups.size() ; i++) {
            List<Map<String, Object>> list = carRepository.findParameter(carId,groups.get(i).getId());
            Map<String, Object> mp = new HashMap<>();
            mp.put("groupId",groups.get(i).getId());
            mp.put("groupName",groups.get(i).getName());
            mp.put("parameter",list);
            ll.add(mp);
        }
        Map<String, Object> map = new HashMap<>();
        AttentionCar ac = attentionCarRepository.findByCarIdAndCustomerId(carId,customerId);
        if(ac == null){
            map.put("isAttention",0);
        }else{
            map.put("isAttention",1);
        }
        map.put("cars",cars);
        map.put("pics",pics);
        map.put("currentCar",currentCar);
        map.put("carSeries",carSeries);
        map.put("price",price);
        map.put("parameters",ll);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 活动车型
     * @return
     */
    @GetMapping("/listActivityCar")
    public ResponseBean listActivityCars() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>>  list = activityCarRepository.listActivityCar();
        map.put("activityCars",list);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 查询汽车信息
     * @return
     */
    @GetMapping("/listCars")
    public ResponseBean listCars() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>>  listCars = listCarsRepository.listCars();
        map.put("listCars",listCars);
        return new ResponseBean(200, "success", map);
    }
    
    /**
     * 根据ID删除汽车信息
     * @return
     */
    @GetMapping("/deleteCarById")
    public ResponseBean deleteCarById(@RequestParam Integer id) {
        Map<String, Object> map = new HashMap<>();
        int state = 0;
        try{
        	System.out.println("i=========d======"+id);
        	System.out.println("2018-05-01liwenqiang:============================");
        	listCarsRepository.deleteById(id);
        }catch(Exception e){
        	state = -1;
        	e.getMessage();
        }
        if(state==0){
        	return new ResponseBean(200, "success", id);
        }else{
        	return new ResponseBean(300, "fail", id);
        }
    }
}
