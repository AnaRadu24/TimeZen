package com.anamradu.timezen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<HolidayItem> holidayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HolidayItem item;
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        Date holidayDate;
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        item = new HolidayItem("Saint Maria", Integer.toString(year)+"-08-15");
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = Integer.toString(year+1)+"-08-15";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
        item = new HolidayItem("Saint Andrew", Integer.toString(year)+"-11-30");
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = Integer.toString(year+1)+"-11-30";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
        item = new HolidayItem("National Day", Integer.toString(year)+"-12-01");
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = Integer.toString(year+1)+"-12-01";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
        item = new HolidayItem("Christmas", Integer.toString(year)+"-12-25");
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = Integer.toString(year+1)+"-12-25";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
        item = new HolidayItem("2nd Day of Christmas", Integer.toString(year)+"-12-26");
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = Integer.toString(year+1)+"-12-26";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
        item = new HolidayItem("New Year", Integer.toString(year)+"-01-01");
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = Integer.toString(year+1)+"-01-01";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
        item = new HolidayItem("Unification Day", Integer.toString(year)+"-01-24");
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = Integer.toString(year+1)+"-01-24";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
        item = new HolidayItem("Easter",getEasterDate(year));
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = getEasterDate(year+1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
        item = new HolidayItem("Work Day", Integer.toString(year)+"-05-01");
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = Integer.toString(year+1)+"-05-01";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
        item = new HolidayItem("Child's Day", Integer.toString(year)+"-06-01");
        try {
            holidayDate = sdf.parse(item.date);
            if(currentDate.after(holidayDate)) {
                item.date = Integer.toString(year+1)+"-06-01";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayList.add(item);
    }
    public void OpenHolidayCountdownActivity(View v){
        Intent intent = new Intent(MainActivity.this, HolidayCountdownActivity.class);
        startActivity(intent);
    }
    public static String getEasterDate(int year) {
        int a = year % 19,
            b = year / 100,
            c = year % 100,
            d = b / 4,
            e = b % 4,
            g = (8 * b + 13) / 25,
            h = (19 * a + b - d - g + 15) % 30,
            j = c / 4,
            k = c % 4,
            m = (a + 11 * h) / 319,
            r = (2 * e + 2 * j - k - h + m + 32) % 7,
            n = (h - m + r + 90) / 25,
            p = (h - m + r + n + 19) % 32;
        String result = Integer.toString(year) + "-";
        if(n<10)
            result = result + "0" + n + "-";
        else
            result = result + n + "-";
        if(p<10)
            result = result + "0" + p;
        else
            result = result + p;
        return result;
    }
}
