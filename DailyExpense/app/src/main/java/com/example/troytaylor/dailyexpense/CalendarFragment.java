package com.example.troytaylor.dailyexpense;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.troytaylor.dailyexpense.R;

/**
 * Created by troytaylor on 4/28/16.
 */
public class CalendarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        //inflate the layout
        return inflater.inflate(R.layout.calendar_fragment, container, false);
    }
}
