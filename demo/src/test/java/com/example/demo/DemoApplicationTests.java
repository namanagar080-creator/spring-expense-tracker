package com.example.demo;

import com.example.demo.entity.Expense;
import com.example.demo.exception.ExpenseNotFoundException;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private ExpenseRepository expenseRepository;

	@BeforeEach
	void setUp() {
		expenseRepository.deleteAll();
		expenseRepository.save(new Expense("Food", 500));
		expenseRepository.save(new Expense("Travel", 1200));
	}

	@Test
	void contextLoads() {
	}

	@Test
	void getExpensesReturnsSavedExpenses() {
		List<Expense> expenses = expenseService.getAllExpenses();
		Set<String> titles = expenses.stream()
				.map(Expense::getTitle)
				.collect(java.util.stream.Collectors.toSet());

		assertEquals(2, expenses.size());
		assertTrue(titles.contains("Food"));
		assertTrue(titles.contains("Travel"));
	}

	@Test
	void createExpenseSavesExpenseWithGeneratedId() {
		Expense savedExpense = expenseService.createExpense(new Expense("Books", 750));

		assertFalse(savedExpense.getId() == null);
		assertEquals("Books", savedExpense.getTitle());
		assertEquals(750.0, savedExpense.getAmount());
	}

	@Test
	void updateExpenseChangesTitleAndAmount() {
		Expense savedExpense = expenseRepository.save(new Expense("Snacks", 150));
		Expense updatedExpense = new Expense("Groceries", 950);

		Expense result = expenseService.updateExpenseById(savedExpense.getId(), updatedExpense);

		assertEquals(savedExpense.getId(), result.getId());
		assertEquals("Groceries", result.getTitle());
		assertEquals(950.0, result.getAmount());
	}

	@Test
	void updateExpenseThrowsWhenExpenseDoesNotExist() {
		Expense updatedExpense = new Expense("Groceries", 950);

		assertThrows(ExpenseNotFoundException.class,
				() -> expenseService.updateExpenseById(999L, updatedExpense));
	}

	@Test
	void deleteExpenseRemovesExpenseById() {
		Expense savedExpense = expenseRepository.save(new Expense("Taxi", 300));

		expenseService.deleteExpenseById(savedExpense.getId());

		assertFalse(expenseRepository.existsById(savedExpense.getId()));
	}

}
