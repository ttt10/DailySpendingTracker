package com.example.troytaylor.dailyexpense;

import com.example.troytaylor.dailyexpense.Constants.Categories;
import com.example.troytaylor.dailyexpense.Services.Repository.Data.Entities.Expense;
import com.example.troytaylor.dailyexpense.Services.Repository.TestRepository;

import org.junit.Test;


import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
/**
 *      TestRepositoryTest
 *          - Unit tests for methods from TestRepository class
 *
 */
public class TestRepositoryTest {

    @Test
    public void addExpense() throws Exception {
        TestRepository repo = new TestRepository();

        assertEquals(true, repo.addExpense(new Expense(-1, Calendar.getInstance(), "Test Merchant", 20.00, Categories.NONE, "Test Description")));
    }

    @Test
    public void removeExpense() throws Exception {
        TestRepository repo = new TestRepository();
        assertEquals(true, repo.removeExpense(new Expense(-1, Calendar.getInstance(), "Chipotle", 10.92, Categories.FOOD, "Burrito")));
    }

    @Test
    public void getExpenses() throws Exception {
        TestRepository repo = new TestRepository();
        List<Expense> list = repo.getExpenses(Calendar.getInstance());

        assertEquals(3, list.size());
    }

    @Test
    public void getTotalDayAmount() throws Exception {
        TestRepository repo = new TestRepository();

        assertEquals(43.66,repo.getTotalDayAmount(Calendar.getInstance()), .01);
    }

    @Test
    public void getTotalMonthAmount() throws Exception {
        TestRepository repo = new TestRepository();
        // by default returns true. need to change assertion below
        assertEquals(repo.getTotalMonthAmount(Calendar.getInstance()) , repo.getTotalMonthAmount(Calendar.getInstance()), .01);
    }

    //TODO: getTotalWeekAmount(), get total amount for certain categories?

}
