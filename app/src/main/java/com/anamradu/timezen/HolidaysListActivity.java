package com.anamradu.timezen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.anamradu.timezen.MainActivity.holidayList;

public class HolidaysListActivity extends AppCompatActivity {

    ListView mHolidayList = null;
    List<String> adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays_list);

        try {
            HolidaysListActivity.RefreshHolidaysList();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mHolidayList = findViewById(R.id.listView_holidays);
        adapterList = new ArrayList<>();

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");
        Date holidayDate;
        Date currentDate = new Date();
        String dayDate = "";
        long daysLeft = 0;

        if(MainActivity.holidayList != null) {
            for (HolidayItem item : MainActivity.holidayList) {
                try {
                    holidayDate = sdfDate.parse(item.date);
                    dayDate = sdfDay.format(holidayDate);
                    daysLeft = (holidayDate.getTime() - currentDate.getTime())/(24*60*60*1000);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                adapterList.add(daysLeft + " days until " + item.name + "\n" + dayDate + " " + item.date);
            }
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(HolidaysListActivity.this,
                R.layout.list_item, R.id.textView_holiday, adapterList);
        mHolidayList.setAdapter(adapter);
        //mHolidayList.setOnItemClickListener(listOnItemClickListener);
        mHolidayList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                adapterList.remove(view);
                MainActivity.holidayList.remove(position);
                adapter.notifyDataSetChanged();

                return true;
            }
        });
    }

    public static void RefreshHolidaysList() throws ParseException {
        int cnt = 0;
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Date holidayDate = sdfDate.parse(MainActivity.holidayList.get(cnt).date);
        while(MainActivity.holidayList.size()>1 && currentDate.after(holidayDate)){
            MainActivity.holidayList.remove(cnt);
            holidayDate = sdfDate.parse(MainActivity.holidayList.get(cnt).date);
        }
    }

    public void OpenAddHolidayActivity(View view) {
        Intent intent = new Intent(HolidaysListActivity.this, AddHolidayActivity.class);
        startActivityForResult(intent,1);
    }
    public void OpenHolidayCountdownActivity(View view) {
        Intent intent = new Intent(HolidaysListActivity.this, HolidayCountdownActivity.class);
        startActivityForResult(intent,1);
    }
}
