package com.hqg.api.controller;

import com.hqg.api.bean.CarOrder;
import com.hqg.api.bean.ResponseBean;
import com.hqg.api.repository.CarOrderRepository;
import com.hqg.api.service.CarOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarOrderController {
    @Autowired
    private CarOrderService carOrderService;
    @Autowired
    private CarOrderRepository carOrderRepository;

    /**
     * 根据电话号码（tel）查询订单列表
     * @param tel
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    @PostMapping("/listCarOrderByTel")
    public ResponseBean listCarOrderByTel(@RequestParam("tel") String tel, @RequestParam(value = "start", defaultValue = "0") int start,
                                          @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Page<CarOrder> co = carOrderService.findCarOrderByTel(start,size,tel);
        map.put("co",co);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 订单列表
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    @PostMapping("/listCarOrder")
    public ResponseBean listCarOrder(@RequestParam(value = "start", defaultValue = "0") int start,
                                          @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<CarOrder> co = carOrderRepository.findAll(pageable);
        map.put("co",co);
        return new ResponseBean(200, "success", map);
    }

    @GetMapping ("/listAll")
    public ResponseBean listAll() throws Exception{
        Map<String, Object> map = new HashMap<>();

        List<CarOrder> co = carOrderRepository.findAll();
        map.put("co",co);
        return new ResponseBean(200, "success", map);
    }
}
