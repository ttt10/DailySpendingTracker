package com.example.troytaylor.dailyexpense.Constants;

/**
 * Created by troytaylor on 10/13/16.
 */
public enum Categories {
    NONE ("None"),
    FOOD ("Food"),
    GROCERY ("Grocery"),
    BILL ("Bill"),
    MISCELLANEOUS ("Miscellaneous"),
    TRANSPORTATION ("Transportation"),
    GAS ("Gas"),
    SAVINGS ("Savings"),
    INVESTMENT ("Investments");

    private final String category;

    private Categories (String c) {
        category = c;
    }

}
