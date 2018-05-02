package com.hqg.api.repository;

import com.hqg.api.bean.BidData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BidDataRepository extends JpaRepository<BidData, Integer>,JpaSpecificationExecutor<BidData> {
     void deleteByBidId(Integer bidId);
     List<BidData> findByBidId(Integer bidId);
}
