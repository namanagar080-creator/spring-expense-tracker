package com.example.demo.service;

import com.example.demo.entity.Expense;
import com.example.demo.exception.ExpenseNotFoundException;
import com.example.demo.repository.ExpenseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // Add a few sample rows only when the database is empty.
    @PostConstruct
    public void loadSampleData() {
        if (expenseRepository.count() == 0) {
            expenseRepository.save(new Expense("Food", 500));
            expenseRepository.save(new Expense("Travel", 1200));
        }
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));

        expenseRepository.delete(expense);
    }
}
