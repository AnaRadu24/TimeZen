package com.anamradu.timezen;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HolidayItem implements Serializable {
    public String name;
    public String date;

    HolidayItem(String n, String d) {
        name = n;
        date = d;
    }
}


