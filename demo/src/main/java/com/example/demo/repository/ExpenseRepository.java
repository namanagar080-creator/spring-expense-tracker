package com.example.demo.repository;

import com.example.demo.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository already gives us common database operations like findAll, findById, save, and delete.
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
