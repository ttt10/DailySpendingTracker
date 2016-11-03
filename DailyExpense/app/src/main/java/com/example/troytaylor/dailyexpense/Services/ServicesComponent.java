package com.example.troytaylor.dailyexpense.Services;

import android.app.Activity;
import android.content.Context;

import com.example.troytaylor.dailyexpense.MyApp;
import com.example.troytaylor.dailyexpense.Services.Repository.IRepository;
import com.example.troytaylor.dailyexpense.Services.Repository.SQLiteRepository;
import com.example.troytaylor.dailyexpense.Services.Repository.TestRepository;
import com.example.troytaylor.dailyexpense.UI.MainActivity;

/**
 * Created by troytaylor on 5/4/16.
 */
public class ServicesComponent {

    private IRepository repository;

    public ServicesComponent(Context context){
        this.repository = new SQLiteRepository(context);
        //this.repository = new TestRepository();
    }

    public IRepository getRepository() {
        return repository;
    }
}
