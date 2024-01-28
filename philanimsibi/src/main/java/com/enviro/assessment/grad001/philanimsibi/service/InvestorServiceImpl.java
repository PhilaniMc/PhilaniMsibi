package com.enviro.assessment.grad001.philanimsibi.service;

import com.enviro.assessment.grad001.philanimsibi.model.Investor;
import com.enviro.assessment.grad001.philanimsibi.model.Product;
import com.enviro.assessment.grad001.philanimsibi.model.WithdrawalNotice;
import com.enviro.assessment.grad001.philanimsibi.repository.InvestorRepository;
import com.enviro.assessment.grad001.philanimsibi.repository.ProductRepository;
import com.enviro.assessment.grad001.philanimsibi.repository.WithdrawalNoticeRepository;
import com.enviro.assessment.grad001.philanimsibi.dto.WithdrawalNoticeDTO;
import com.enviro.assessment.grad001.philanimsibi.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class InvestorServiceImpl implements InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WithdrawalNoticeRepository withdrawalNoticeRepository;

    @Override
    public List<Investor> getAllInvestors() {
        return investorRepository.findAll();
    }

    @Override
    public Investor getInvestorById(Long id) {
        return investorRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Investor not found"));
    }

    @Override
    public List<Product> getInvestorProducts(Long investorId) {
        Investor investor = getInvestorById(investorId);
        return investor.getProducts();
    }

    @Override
    public WithdrawalNotice createWithdrawalNotice(Long productId, WithdrawalNoticeDTO withdrawalNoticeDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ValidationException("Product not found"));

        BigDecimal withdrawalAmount = withdrawalNoticeDTO.getWithdrawalAmount();

        if (product.getType().equals("RETIREMENT")) {
            int investorAge = product.getInvestor().getAge();
            if (investorAge <= 65) {
                throw new ValidationException("Investor must be over 65 years old for RETIREMENT withdrawal");
            }
        }

        BigDecimal currentBalance = product.getBalance();
        BigDecimal allowedWithdrawal = currentBalance.multiply(new BigDecimal("0.9"));

        if (withdrawalAmount.compareTo(allowedWithdrawal) > 0) {
            throw new ValidationException("Withdrawal amount exceeds 90% of the current balance");
        }

        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();
        withdrawalNotice.setWithdrawalAmount(withdrawalAmount);
        withdrawalNotice.setWithdrawalDate(withdrawalNoticeDTO.getWithdrawalDate());
        withdrawalNotice.setProduct(product);

        // Update product balance
        BigDecimal newBalance = currentBalance.subtract(withdrawalAmount);
        product.setBalance(newBalance);
        productRepository.save(product);

        return withdrawalNoticeRepository.save(withdrawalNotice);
    }

    @Override
    public List<WithdrawalNotice> getWithdrawalNotices(Long productId, Date startDate, Date endDate) {
        return withdrawalNoticeRepository.findByProductIdAndWithdrawalDateBetween(productId, startDate, endDate);
    }
}

