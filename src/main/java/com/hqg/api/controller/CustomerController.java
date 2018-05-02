package com.hqg.api.controller;

import com.hqg.api.bean.Customer;
import com.hqg.api.bean.ResponseBean;
import com.hqg.api.exception.UnauthorizedException;
import com.hqg.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // 通过用户 手机号 更新customer信息(补充信息)
    @PostMapping("/updateCustomerMessage")
    public ResponseBean updateCustomerMessage(@RequestBody Customer customer){
        if(customer.getMobile() != null && customer.getName() != null && customer.getAddress() != null && customer.getIdCard()!= null ){
            Customer c = customerRepository.findByMobile(customer.getMobile());
            c.setName(customer.getName());
            c.setAddress(customer.getAddress());
            c.setIdCard(customer.getIdCard());
            customerRepository.save(c);
            return new ResponseBean(200,"success","");
        }else{
            throw new UnauthorizedException();
        }
    }
}
