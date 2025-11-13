package com.yourorg.pocketexpense.service.impl;

import com.yourorg.pocketexpense.dto.ExpenseRequest;
import com.yourorg.pocketexpense.dto.ExpenseResponse;
import com.yourorg.pocketexpense.entity.Category;
import com.yourorg.pocketexpense.entity.Expense;
import com.yourorg.pocketexpense.entity.Budget;
import com.yourorg.pocketexpense.exception.NotFoundException;
import com.yourorg.pocketexpense.repository.BudgetRepository;
import com.yourorg.pocketexpense.repository.CategoryRepository;
import com.yourorg.pocketexpense.repository.ExpenseRepository;
import com.yourorg.pocketexpense.service.ExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.YearMonth;
import java.math.BigDecimal;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final BudgetRepository budgetRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              CategoryRepository categoryRepository,
                              BudgetRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.budgetRepository = budgetRepository;
    }

    @Override
    public ExpenseResponse createExpense(ExpenseRequest req) {
        Category cat = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        Expense e = new Expense();
        e.setAmount(req.getAmount());
        e.setCurrency(req.getCurrency());
        e.setDescription(req.getDescription());
        e.setDate(req.getDate());
        e.setCategory(cat);
        Expense saved = expenseRepository.save(e);
        return toDto(saved);
    }

    @Override
    public ExpenseResponse getExpense(Long id) {
        Expense e = expenseRepository.findById(id).orElseThrow(() -> new NotFoundException("Expense not found"));
        return toDto(e);
    }

    @Override
    public List<ExpenseResponse> listExpenses() {
        return expenseRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ExpenseResponse updateExpense(Long id, ExpenseRequest req) {
        Expense e = expenseRepository.findById(id).orElseThrow(() -> new NotFoundException("Expense not found"));
        Category cat = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        e.setAmount(req.getAmount());
        e.setCurrency(req.getCurrency());
        e.setDescription(req.getDescription());
        e.setDate(req.getDate());
        e.setCategory(cat);
        e.setUpdatedAt(java.time.LocalDateTime.now());
        Expense saved = expenseRepository.save(e);
        return toDto(saved);
    }

    @Override
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) throw new NotFoundException("Expense not found");
        expenseRepository.deleteById(id);
    }

    @Override
    public BigDecimal getMonthlyTotal(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();
        return expenseRepository.sumAmountBetween(start, end);
    }

    private ExpenseResponse toDto(Expense e) {
        ExpenseResponse r = new ExpenseResponse();
        r.setId(e.getId());
        r.setAmount(e.getAmount());
        r.setCurrency(e.getCurrency());
        r.setDescription(e.getDescription());
        r.setDate(e.getDate());
        r.setCategoryName(e.getCategory() != null ? e.getCategory().getName() : null);
        return r;
    }
}
