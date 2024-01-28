package com.enviro.assessment.grad001.philanimsibi.repository;

import com.enviro.assessment.grad001.philanimsibi.model.WithdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long> {
    List<WithdrawalNotice> findByProductIdAndWithdrawalDateBetween(Long productId, Date startDate, Date endDate);
}
