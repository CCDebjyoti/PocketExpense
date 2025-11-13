package com.yourorg.pocketexpense.controller;

import com.yourorg.pocketexpense.dto.ExpenseRequest;
import com.yourorg.pocketexpense.dto.ExpenseResponse;
import com.yourorg.pocketexpense.service.ExpenseService;
import com.yourorg.pocketexpense.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final BudgetService budgetService;

    public ExpenseController(ExpenseService expenseService, BudgetService budgetService) {
        this.expenseService = expenseService;
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> create(@Valid @RequestBody ExpenseRequest req) {
        ExpenseResponse r = expenseService.createExpense(req);
        // check monthly alert
        int y = req.getDate().getYear();
        int m = req.getDate().getMonthValue();
        boolean near = budgetService.isNearLimit(y,m);
        return ResponseEntity.status(201).body(Map.of("expense", r, "nearBudget", near));
    }

    @GetMapping
    public List<ExpenseResponse> list() { return expenseService.listExpenses(); }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpense(id));
    }

    @PutMapping("/{id}")
    public ExpenseResponse update(@PathVariable Long id, @Valid @RequestBody ExpenseRequest req) {
        return expenseService.updateExpense(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary/{year}/{month}")
    public ResponseEntity<Map<String,Object>> monthlySummary(@PathVariable int year, @PathVariable int month) {
        BigDecimal total = expenseService.getMonthlyTotal(year, month);
        boolean near = budgetService.isNearLimit(year, month);
        return ResponseEntity.ok(Map.of("year", year, "month", month, "totalSpent", total, "nearBudget", near));
    }
}
