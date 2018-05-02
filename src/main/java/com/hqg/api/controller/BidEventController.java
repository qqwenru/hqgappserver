package com.hqg.api.controller;

import com.hqg.api.bean.BidCar;
import com.hqg.api.bean.CarColor;
import com.hqg.api.bean.ResponseBean;
import com.hqg.api.bean.ShareBill;
import com.hqg.api.exception.UnauthorizedException;
import com.hqg.api.repository.BidCarRepository;
import com.hqg.api.repository.CarColorRepository;
import com.hqg.api.repository.ShareBillRepository;
import com.hqg.api.service.BidEventService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP竞价事件
 */
@RestController
@RequestMapping("/bidEvent")
public class BidEventController {
    @Autowired
    private CarColorRepository carColorRepository;
    @Autowired
    private BidEventService bidEventService;
    @Autowired
    private BidCarRepository bidCarRepository;
    @Autowired
    private ShareBillRepository shareBillRepository;

    /**
     * 根据系列CarSriesId获取外观颜色组
     * @param carColor
     * @return
     */
    @PostMapping("findByCarSriesId")
    @RequiresAuthentication
    public ResponseBean findByCarSriesId(@RequestBody CarColor carColor) {
        Map<String, Object> map = new HashMap<>();
        CarColor cc = carColorRepository.findByCarSriesId(carColor.getCarSriesId());
        String [] outColor = cc.getAppearanceColor().split("\\|");
        map.put("outColor", outColor);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 保存竞价事件和支付记录
     * @param
     * @return
     */
    @PostMapping("saveAll")
    @RequiresAuthentication
    public ResponseBean saveAll(@RequestBody BidCar bidCar) {
        bidEventService.saveBidCarAndPaymentRecord(bidCar.getCustomerId(),bidCar.getCarId(),bidCar.getColor(),
                bidCar.getRemark(),bidCar.getSalerId(),bidCar.getPrice());
        return new ResponseBean(200, "success", "");
    }

    /**
     * 根据CustomerId来查询竞价事件列表
     * @param bidCar
     * @return
     */
    @PostMapping("findListByCustomerId")
    @RequiresAuthentication
    public ResponseBean findListByCustomerId(@RequestBody BidCar bidCar) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> list = bidCarRepository.findListByCustomerId(bidCar.getCustomerId());
        map.put("bidCars",list);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 申请退订金
     * @param bidCar
     * @return
     */
    @Transactional
    @PostMapping("editBidCar")
    @RequiresAuthentication
    public ResponseBean editBidCar(@RequestBody BidCar bidCar) {
        if(bidCar.getCarId()!= null && bidCar.getCustomerId() !=null){
            bidCarRepository.updateStateByCustomerIdAndCarId(bidCar.getCustomerId(),bidCar.getCarId(),2);
            return new ResponseBean(200, "success","");
        }else{
            throw new UnauthorizedException();
        }
    }

    /**
     * 竞价事件转成拼单事件
     * @param bidCar
     * @return
     */
    @Transactional
    @PostMapping("editBidCarAndShareBill")
    @RequiresAuthentication
    public ResponseBean editBidCarAndShareBill(@RequestBody BidCar bidCar) {
        if(bidCar.getCarId()!= null && bidCar.getCustomerId() !=null && bidCar.getPreferential() != null){
            BidCar b = bidCarRepository.findByCustomerIdAndCarId(bidCar.getCustomerId(),bidCar.getCarId());
            if(b != null){
                bidCarRepository.updateStateByCustomerIdAndCarId(bidCar.getCustomerId(),bidCar.getCarId(),1);
                ShareBill sb = new ShareBill();
                sb.setCarId(b.getCarId());
                sb.setBidId(b.getId());
                sb.setCustomerId(b.getCustomerId());
                sb.setColor(b.getColor());
                sb.setPreferential(bidCar.getPreferential());
                sb.setState(0);
                sb.setSubmissionDate(new Date());
                shareBillRepository.save(sb);
                return new ResponseBean(200, "success","");
            }else{
                return new ResponseBean(400, "数据错误","");
            }
        }else{
            throw new UnauthorizedException();
        }
    }
}
