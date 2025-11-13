package com.yourorg.pocketexpense.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "budget", uniqueConstraints = {@UniqueConstraint(columnNames = {"year", "month"})})
public class Budget {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private int year;

    @Column(nullable=false)
    private int month;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal limitAmount;

    private int thresholdPercent = 80;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Budget() {}

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }
    public BigDecimal getLimitAmount() { return limitAmount; }
    public void setLimitAmount(BigDecimal limitAmount) { this.limitAmount = limitAmount; }
    public int getThresholdPercent() { return thresholdPercent; }
    public void setThresholdPercent(int thresholdPercent) { this.thresholdPercent = thresholdPercent; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
