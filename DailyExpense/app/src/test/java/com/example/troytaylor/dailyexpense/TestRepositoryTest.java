package com.example.troytaylor.dailyexpense;

import org.junit.Test;


import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by troytaylor on 4/29/16.
 */
public class TestRepositoryTest {

    @Test
    public void addExpense() throws Exception {
        TestRepository repo = new TestRepository();

        assertEquals(true, repo.addExpense(new Expense(Calendar.getInstance(), "Test Description", 20.00)));
    }

    @Test
    public void removeExpense() throws Exception {
        TestRepository repo = new TestRepository();
        assertEquals(true, repo.removeExpense(new Expense(Calendar.getInstance(), "Chipotle", 10.92)));
    }

    @Test
    public void getExpenses() throws Exception {
        TestRepository repo = new TestRepository();
        List<Expense> list = repo.getExpenses(Calendar.getInstance());

        assertEquals(3, list.size());
    }

    @Test
    public void getTotalDayAmount() throws Exception {

    }

    @Test
    public void getTotalMonthAmount() throws Exception {

    }

}
