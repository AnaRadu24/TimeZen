package com.anamradu.timezen;

import java.io.Serializable;

public class HolidayItem implements Serializable {
    public String name;
    public String date;

    HolidayItem(String n, String d) {
        name = n;
        date = d;
    }
}