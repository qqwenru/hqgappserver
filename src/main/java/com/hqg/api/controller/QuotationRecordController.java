package com.hqg.api.controller;

import com.hqg.api.bean.QuotationRecord;
import com.hqg.api.bean.ResponseBean;
import com.hqg.api.repository.BidCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.hqg.api.repository.QuotationRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/quotationRecord")
public class QuotationRecordController {
    @Autowired
    private BidCarRepository bidCarRepository;
    @Autowired
    private QuotationRecordRepository quotationRecordRepository;

    /**
     * 最近报价
     *
     * @return
     */
    @GetMapping("listDealerQuotation")
    public ResponseBean listDealerQuotation() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> ll = new ArrayList<>();
        List<Map<String, Object>> list = bidCarRepository.listCarInfo();
        for (Map<String, Object> m : list) {
            Integer carId = Integer.parseInt(m.get("carId").toString());
            List<Map<String, Object>> li = bidCarRepository.findByAllCarId(carId);
            Map<String, Object> mp = new HashMap<>();
            for (String k : m.keySet()) {
                mp.put(k, m.get(k));
            }
            mp.put("dealerInfo", li);
            ll.add(mp);
        }
        map.put("dealerQuotation", ll);
        return new ResponseBean(200, "success", map);
    }

    /**
     * 保存单条quotationRecord
     * @param quotationRecord
     * @return
     */
    @PostMapping("saveQuotationRecord")
    public ResponseBean saveQuotationRecord(@RequestBody QuotationRecord quotationRecord) {
        quotationRecord.setCreateTime(new Date());
        quotationRecordRepository.save(quotationRecord);
        return new ResponseBean(200, "success", null);
    }

    /**
     * 保存List<QuotationRecord>
     * @param quotationRecords
     * @return
     */
    @PostMapping("saveQuotationRecords")
    public ResponseBean saveQuotationRecords(@RequestBody List<QuotationRecord> quotationRecords) {
        for(int i = 0 ; i < quotationRecords.size() ; i++) {
            quotationRecords.get(i).setCreateTime(new Date());
        }
        quotationRecordRepository.saveAll(quotationRecords);
        return new ResponseBean(200, "success", null);
    }

}
