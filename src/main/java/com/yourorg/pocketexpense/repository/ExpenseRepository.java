package com.yourorg.pocketexpense.repository;

import com.yourorg.pocketexpense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
    List<Expense> findByCategoryId(Long categoryId);
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.date BETWEEN :start AND :end")
    BigDecimal sumAmountBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
