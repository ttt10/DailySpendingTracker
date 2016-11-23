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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *      SQLiteRepositoryTest
 *          - Unit tests for SQLiteRepository class
 */
@RunWith(AndroidJUnit4.class)
public class SQLiteRepositoryTest{

    private final List<Expense> recordsAddedForTest = Arrays.asList(
            new Expense(1, Calendar.getInstance(), "Components", 1000, Categories.MISCELLANEOUS, "Shit"),
            new Expense(2, Calendar.getInstance(), "GrapeCity", 1000, Categories.MISCELLANEOUS, "Travel Expense")
            //new Expense(2, Calendar.getInstance(), "ComponentOne", 1000, Categories.MISCELLANEOUS, "Travel Expense"),
            );

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
        assertEquals(recordsAddedForTest, list);
    }

    @Test
    public void testGetTotalDayAmount() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        assertEquals(2000, repository.getTotalDayAmount(Calendar.getInstance()), 0.001);
    }

    @Test
    public void testGetTotalMonthAmount() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        Calendar today = Calendar.getInstance();
        assertEquals(2000, repository.getTotalMonthAmount(today), 0.001);
    }

//    @Test
//    public void testSetSelectedDay() throws Exception {
//        SQLiteRepository repository = new SQLiteRepository(context);
//
//    }

    @Test
    public void testGetSelectedDay() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Calendar select = repository.getSelectedDay();
        String selectedDay = format.format(select.getTime());
        Calendar cal = Calendar.getInstance();
        String day = format.format(cal.getTime());
        assertEquals(day, selectedDay);
    }
}
