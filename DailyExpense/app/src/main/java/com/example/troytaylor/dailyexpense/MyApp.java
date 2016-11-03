package com.example.troytaylor.dailyexpense;

import android.app.Application;

import com.example.troytaylor.dailyexpense.Services.ServicesComponent;

/**
 * Created by troytaylor on 5/4/16.
 */
public class MyApp extends Application {

    private static ServicesComponent servicesComponent;

    public void onCreate(){
        super.onCreate();
        servicesComponent = new ServicesComponent(this);
    }

    public static ServicesComponent getServicesComponent(){
        return servicesComponent;
    }

}
