package com.yourorg.pocketexpense.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseRequest {
    @NotNull @DecimalMin("0.01")
    private BigDecimal amount;

    @NotBlank
    private String currency = "INR";

    private String description;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long categoryId;

    // getters and setters
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}
