package com.example.troytaylor.dailyexpense.Services;

import com.example.troytaylor.dailyexpense.Services.Repository.IRepository;
import com.example.troytaylor.dailyexpense.Services.Repository.TestRepository;

/**
 * Created by troytaylor on 5/4/16.
 */
public class ServicesComponent {

    private IRepository repository;

    public ServicesComponent(){
        // this.repository = new SQLiteRepository;
        this.repository = new TestRepository();
    }

    public IRepository getRepository() {
        return repository;
    }
}
