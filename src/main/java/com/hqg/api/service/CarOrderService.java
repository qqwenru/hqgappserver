package com.hqg.api.service;

import com.hqg.api.bean.CarOrder;
import com.hqg.api.repository.CarOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CarOrderService {
    @Autowired
    private CarOrderRepository carOrderRepository;

    public Page<CarOrder> findCarOrderByTel(Integer page, Integer size, String tel) throws Exception {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return carOrderRepository.findByTel(tel,pageable);
    }

}
