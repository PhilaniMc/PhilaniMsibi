package com.enviro.assessment.grad001.philanimsibi.dto;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawalNoticeDTO {
    private BigDecimal withdrawalAmount;
    private Date withdrawalDate;

    // getters and setters
    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
}
