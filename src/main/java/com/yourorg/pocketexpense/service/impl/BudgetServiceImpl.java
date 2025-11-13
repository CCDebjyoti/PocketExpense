package com.yourorg.pocketexpense.service.impl;

import com.yourorg.pocketexpense.dto.BudgetRequest;
import com.yourorg.pocketexpense.entity.Budget;
import com.yourorg.pocketexpense.repository.BudgetRepository;
import com.yourorg.pocketexpense.service.BudgetService;
import com.yourorg.pocketexpense.service.ExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.math.BigDecimal;

@Service
@Transactional
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final ExpenseService expenseService;

    public BudgetServiceImpl(BudgetRepository budgetRepository, ExpenseService expenseService) {
        this.budgetRepository = budgetRepository;
        this.expenseService = expenseService;
    }

    @Override
    public Budget createOrUpdateBudget(BudgetRequest req) {
        return budgetRepository.findByYearAndMonth(req.getYear(), req.getMonth())
                .map(b -> {
                    b.setLimitAmount(req.getLimitAmount());
                    if (req.getThresholdPercent() != null) b.setThresholdPercent(req.getThresholdPercent());
                    return budgetRepository.save(b);
                }).orElseGet(() -> {
                    Budget b = new Budget();
                    b.setYear(req.getYear());
                    b.setMonth(req.getMonth());
                    b.setLimitAmount(req.getLimitAmount());
                    if (req.getThresholdPercent() != null) b.setThresholdPercent(req.getThresholdPercent());
                    return budgetRepository.save(b);
                });
    }

    @Override
    public Optional<Budget> getBudget(int year, int month) {
        return budgetRepository.findByYearAndMonth(year, month);
    }

    @Override
    public boolean isNearLimit(int year, int month) {
        Optional<Budget> ob = budgetRepository.findByYearAndMonth(year, month);
        if (!ob.isPresent()) return false;
        Budget b = ob.get();
        BigDecimal spent = expenseService.getMonthlyTotal(year, month);
        if (spent == null) spent = BigDecimal.ZERO;
        BigDecimal percent = spent.multiply(new BigDecimal(100)).divide(b.getLimitAmount(), 2, java.math.RoundingMode.HALF_UP);
        return percent.compareTo(new BigDecimal(b.getThresholdPercent())) >= 0;
    }
}
