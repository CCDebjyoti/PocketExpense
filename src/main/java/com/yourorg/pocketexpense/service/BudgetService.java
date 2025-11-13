package com.yourorg.pocketexpense.service;

import com.yourorg.pocketexpense.dto.BudgetRequest;
import com.yourorg.pocketexpense.entity.Budget;

import java.util.Optional;

public interface BudgetService {
    Budget createOrUpdateBudget(BudgetRequest req);
    Optional<Budget> getBudget(int year, int month);
    boolean isNearLimit(int year, int month);
}
