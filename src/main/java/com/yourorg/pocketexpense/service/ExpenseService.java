package com.yourorg.pocketexpense.service;

import com.yourorg.pocketexpense.dto.ExpenseRequest;
import com.yourorg.pocketexpense.dto.ExpenseResponse;

import java.util.List;

public interface ExpenseService {
    ExpenseResponse createExpense(ExpenseRequest req);
    ExpenseResponse getExpense(Long id);
    List<ExpenseResponse> listExpenses();
    ExpenseResponse updateExpense(Long id, ExpenseRequest req);
    void deleteExpense(Long id);
    java.math.BigDecimal getMonthlyTotal(int year, int month);
}
