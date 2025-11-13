package com.yourorg.pocketexpense.controller;

import com.yourorg.pocketexpense.dto.BudgetRequest;
import com.yourorg.pocketexpense.entity.Budget;
import com.yourorg.pocketexpense.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) { this.budgetService = budgetService; }

    @PostMapping
    public ResponseEntity<Budget> createOrUpdate(@Valid @RequestBody BudgetRequest req) {
        Budget b = budgetService.createOrUpdateBudget(req);
        return ResponseEntity.ok(b);
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<?> get(@PathVariable int year, @PathVariable int month) {
        Optional<Budget> b = budgetService.getBudget(year, month);
        return b.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{year}/{month}/alert")
    public ResponseEntity<?> alert(@PathVariable int year, @PathVariable int month) {
        boolean near = budgetService.isNearLimit(year, month);
        return ResponseEntity.ok(Map.of("nearBudget", near));
    }
}
