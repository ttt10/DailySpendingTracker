package com.example.troytaylor.dailyexpense;

/**
 * Created by troytaylor on 5/4/16.
 */
public class ServicesComponent {

    private IRepository repository;

    public ServicesComponent(){
        this.repository = new TestRepository();
    }

    public IRepository getRepository() {
        return repository;
    }
}
