package com.hqg.api.repository;

import com.hqg.api.bean.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Integer>,JpaSpecificationExecutor<PaymentRecord> {
}
