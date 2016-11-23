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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *      SQLiteRepositoryTest
 *          - Unit tests for SQLiteRepository class
 */
@RunWith(AndroidJUnit4.class)
public class SQLiteRepositoryTest{

    // mock data
    private List<Expense> testList;

    private static Context context;

    @Before
    public void setUp() throws Exception {

        context = InstrumentationRegistry.getContext();
        testList = Arrays.asList(
                new Expense(1, Calendar.getInstance(), "GrapeCity", 1000, Categories.MISCELLANEOUS, "Travel Expense"),
                new Expense(2, Calendar.getInstance(), "ComponentOne", 1000, Categories.MISCELLANEOUS, "Travel Expense"),
                new Expense(0, Calendar.getInstance(), "GrapeCity", 1000, Categories.MISCELLANEOUS, "Travel Expense")
        );
    }

    @Test
    public void testAddExpense() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        assertEquals(true, repository.addExpense(Calendar.getInstance(), "ComponentOne", 1000, "Travel Expense", Categories.MISCELLANEOUS));
    }

    @Test
    public void testRemoveExpense() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        assertEquals(true, repository.removeExpense(new Expense(2, Calendar.getInstance(), "ComponentOne", 1000, Categories.MISCELLANEOUS, "Travel Expense")));
    }

    @Test
    public void testGetExpenses() throws Exception {
        SQLiteRepository repository = new SQLiteRepository(context);

        // mock a list to compare to
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date d = sdf.parse("2016.11.23");
        c.setTime(d);
        List <Expense> list = repository.getExpenses(c);
        if(list == null){
            assertEquals(true, false);
        }
        boolean flag = true;
        int size = list.size();
        for (int i=0; i<size; i++){
            Expense test = testList.get(i);
            Expense result = list.get(i);
            if( test.getId() != result.getId() ){
                flag = false;
                break;
            }
            if( !sdf.format( test.getDate().getTime() ).equals( sdf.format( result.getDate().getTime() ) ) ){
                flag = false;
                break;
            }
            if( !test.getMerchant().equals( result.getMerchant() ) ){
                flag = false;
                break;
            }
            if( test.getAmount() != result.getAmount()){
                flag = false;
                break;
            }
            if( !test.getDescription().equals( result.getDescription() ) ){
                flag = false;
                break;
            }
            if( !test.getCategory().toString().equals( result.getCategory().toString() ) ){
                flag = false;
                break;
            }

        }


        assertEquals(true, flag);
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
