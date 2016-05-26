package com.example.troytaylor.dailyexpense.Services;

/**
 * Created by troytaylor on 5/25/16.
 */
public interface OnTaskCompleted<T> {
    void onSuccess(T result);
}
