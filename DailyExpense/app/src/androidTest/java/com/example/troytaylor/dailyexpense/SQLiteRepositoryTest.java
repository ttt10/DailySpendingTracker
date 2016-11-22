package com.example.troytaylor.dailyexpense;

import com.example.troytaylor.dailyexpense.Constants.Categories;
import com.example.troytaylor.dailyexpense.Services.Repository.Data.Entities.Expense;
import com.example.troytaylor.dailyexpense.Services.Repository.SQLiteRepository;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *      SQLiteRepositoryTest
 *          - Unit tests for SQLiteRepository class
 */
@RunWith(AndroidJUnit4.class)
public class SQLiteRepositoryTest{

    private static Context context;

    @Before
    public void setUp() throws Exception {

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void testAddExpense() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        assertEquals(true, repository.addExpense(Calendar.getInstance(), "GrapeCity", 1000, "Travel Expense", Categories.MISCELLANEOUS));
    }

    @Test
    public void testRemoveExpense() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        assertEquals(false, repository.removeExpense(new Expense(-1, Calendar.getInstance(), "GrapeCity", 1000, Categories.MISCELLANEOUS, "Travel Expense")));
    }

    @Test
    public void testGetExpenses() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        // mock a list to compare to
        List<Expense> list = repository.getExpenses(Calendar.getInstance());
        assertEquals(null, list);
    }

    @Test
    public void testGetTotalDayAmount() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        assertEquals(0.0, repository.getTotalDayAmount(Calendar.getInstance()), 0.001);
    }

    @Test
    public void testGetTotalMonthAmount() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        assertEquals(0.0, repository.getTotalMonthAmount(Calendar.getInstance()), 0.001);
    }

//    @Test
//    public void testSetSelectedDay() throws Exception {
//        SQLiteRepository repository = new SQLiteRepository(context);
//
//    }

    @Test
    public void testGetSelectedDay() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        Calendar today = Calendar.getInstance();
        int s = today.get(Calendar.DATE);
        assertEquals(s, repository.getSelectedDay());
    }
}
