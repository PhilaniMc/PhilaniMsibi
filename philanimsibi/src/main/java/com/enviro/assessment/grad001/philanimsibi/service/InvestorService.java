package com.enviro.assessment.grad001.philanimsibi.service;

import com.enviro.assessment.grad001.philanimsibi.model.Investor;
import java.util.List;

public interface InvestorService {
    List<Investor> getAllInvestors();
    Investor getInvestorById(Long id);
    List<Product> getInvestorProducts(Long investorId);
    WithdrawalNotice createWithdrawalNotice(Long productId, WithdrawalNoticeDTO withdrawalNoticeDTO);
    List<WithdrawalNotice> getWithdrawalNotices(Long productId, Date startDate, Date endDate);
}
