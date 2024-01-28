package com.enviro.assessment.grad001.philanimsibi.controller;

import com.enviro.assessment.grad001.philanimsibi.model.Investor;
import com.enviro.assessment.grad001.philanimsibi.model.Product;
import com.enviro.assessment.grad001.philanimsibi.model.WithdrawalNotice;
import com.enviro.assessment.grad001.philanimsibi.dto.WithdrawalNoticeDTO;
import com.enviro.assessment.grad001.philanimsibi.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/investors")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @GetMapping
    public List<Investor> getAllInvestors() {
        return investorService.getAllInvestors();
    }

    @GetMapping("/{investorId}")
    public ResponseEntity<Investor> getInvestorById(@PathVariable Long investorId) {
        Investor investor = investorService.getInvestorById(investorId);
        return ResponseEntity.ok(investor);
    }

    @GetMapping("/{investorId}/products")
    public ResponseEntity<List<Product>> getInvestorProducts(@PathVariable Long investorId) {
        List<Product> products = investorService.getInvestorProducts(investorId);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/{productId}/withdrawal")
    public ResponseEntity<WithdrawalNotice> createWithdrawalNotice(
            @PathVariable Long productId,
            @RequestBody WithdrawalNoticeDTO withdrawalNoticeDTO) {
        WithdrawalNotice withdrawalNotice = investorService.createWithdrawalNotice(productId, withdrawalNoticeDTO);
        return ResponseEntity.ok(withdrawalNotice);
    }

    @GetMapping("/{productId}/withdrawals")
    public ResponseEntity<List<WithdrawalNotice>> getWithdrawalNotices(
            @PathVariable Long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<WithdrawalNotice> withdrawalNotices = investorService.getWithdrawalNotices(productId, startDate, endDate);
        return ResponseEntity.ok(withdrawalNotices);
    }
}
