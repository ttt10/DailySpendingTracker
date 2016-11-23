package com.example.troytaylor.dailyexpense.UI;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import com.example.troytaylor.dailyexpense.*;
import com.example.troytaylor.dailyexpense.R;
import com.example.troytaylor.dailyexpense.Services.Repository.IRepository;
import com.grapecity.xuni.core.IEventHandler;
import com.grapecity.xuni.calendar.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by troytaylor on 4/28/16.
 */
public class CalendarFragment extends Fragment {

    private XuniCalendar calendar;
    private Context context;
    private IRepository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        context = getActivity();
        repository = MyApp.getServicesComponent().getRepository();


        // inflate the layout
        View v = inflater.inflate(com.example.troytaylor.dailyexpense.R.layout.calendar_fragment, container, false);

        // get the calendar view object from the inflated layout
        calendar = (XuniCalendar) v.findViewById(R.id.calendar);

        // manipulate the calendar layout
        calendar.setBackgroundColor(Color.BLACK);
        calendar.setTextColor(Color.WHITE);
        calendar.setHeaderBackgroundColor(Color.BLACK);
        calendar.setHeaderTextColor(Color.WHITE);
        calendar.setDayOfWeekBackgroundColor(Color.BLACK);
        calendar.setDayOfWeekTextColor(Color.WHITE);

        calendar.setOrientation(CalendarOrientation.Vertical);

        calendar.getDaySlotLoading().addHandler(new IEventHandler() {
            @Override
            public void call(Object o, Object o1) {
                CalendarDaySlotLoadingEventArgs args = (CalendarDaySlotLoadingEventArgs) o1;
                Date date = args.getDate();

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                int day = cal.get(Calendar.DAY_OF_MONTH);

                double totalDayAmount = repository.getTotalDayAmount(cal);

                if (totalDayAmount > 0.0) {
                    CalendarDetailDaySlot view = new CalendarDetailDaySlot(context);
                    view.setDayText(String.valueOf(day));
                    view.setDayTextColor(Color.LTGRAY);
                    view.setDetailText("$ "+totalDayAmount);
                    view.setDetailFontSize(12);
                    view.setDetailTextColor(Color.GREEN);
                    args.setDaySlot(view);
                }
                else {
                    CalendarDetailDaySlot view = new CalendarDetailDaySlot(context);
                    view.setDayText(String.valueOf(day));
                    view.setDayTextColor(Color.LTGRAY);
                    args.setDaySlot(view);
                }

            }
        }, this);

        // open the ExpenseListFragment
        calendar.getSelectionChanged().addHandler(new IEventHandler() {
            @Override
            public void call(Object o, Object o1){
                CalendarSelectionChangedEventArgs args = (CalendarSelectionChangedEventArgs) o1;
                List<Date> date = args.getSelectedDates();

                Calendar cal = Calendar.getInstance();
                cal.setTime(date.get(0));

                repository.setSelectedDay(cal);
                double amount = repository.getTotalDayAmount(repository.getSelectedDay());

                if(amount == 0.0){
                    ((MainActivity) getActivity()).loadEditExpenseFragment();
                }
                else {
                    ((MainActivity) getActivity()).loadExpenseListFragment();
                }
            }
        }, this);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstance) {
        super.onActivityCreated(savedInstance);
        long displayDate;
        if(savedInstance != null && savedInstance.containsKey("DisplayDate")){
            displayDate = savedInstance.getLong("DisplayDate");
            calendar.setDisplayDate(new Date(displayDate));
            Log.d("", displayDate+" here");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("DisplayDate", calendar.getDisplayDate().getTime());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        long displayDate;
        if(savedInstanceState != null && savedInstanceState.containsKey("DisplayDate")){
            displayDate = savedInstanceState.getLong("DisplayDate");
            calendar.setDisplayDate(new Date(displayDate));
        }

    }
}
