package com.yourorg.pocketexpense.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class BudgetRequest {
    @Min(2000) @Max(3000) // year range example (optional)
    private int year;
    @Min(1) @Max(12)
    private int month;

    @NotNull @DecimalMin("0.01")
    private BigDecimal limitAmount;

    private Integer thresholdPercent = 80;

    // getters and setters
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }
    public BigDecimal getLimitAmount() { return limitAmount; }
    public void setLimitAmount(BigDecimal limitAmount) { this.limitAmount = limitAmount; }
    public Integer getThresholdPercent() { return thresholdPercent; }
    public void setThresholdPercent(Integer thresholdPercent) { this.thresholdPercent = thresholdPercent; }
}
