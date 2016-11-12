package com.example.troytaylor.dailyexpense.Services;

import android.content.Context;

import com.example.troytaylor.dailyexpense.Services.Repository.IRepository;
import com.example.troytaylor.dailyexpense.Services.Repository.SQLiteRepository;
import com.example.troytaylor.dailyexpense.Services.Repository.TestRepository;

/**
 *      ServicesComponent class
 *          - creates a single repository for app
 *          - abstracts data service away from view
 */
public class ServicesComponent {

    private IRepository repository;

    public ServicesComponent(Context context){
        //this.repository = new SQLiteRepository(context);
        this.repository = new TestRepository();
    }

    public IRepository getRepository() {
        return repository;
    }
}
