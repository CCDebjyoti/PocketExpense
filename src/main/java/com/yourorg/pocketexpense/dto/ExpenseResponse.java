package com.yourorg.pocketexpense.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseResponse {
    private Long id;
    private BigDecimal amount;
    private String currency;
    private String description;
    private LocalDate date;
    private String categoryName;

    public ExpenseResponse() {}

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
