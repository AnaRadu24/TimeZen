package com.anamradu.timezen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddHolidayActivity extends AppCompatActivity {

    EditText editHolidayName = null;
    EditText editHolidayDate = null;
    Button mAddHolidayBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_holiday);

        editHolidayName = findViewById(R.id.editHolidayName);
        editHolidayDate = findViewById(R.id.editHolidayDate);
        mAddHolidayBtn = findViewById(R.id.button_add_holiday);

        mAddHolidayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()) {
                    HolidayItem item = new HolidayItem(editHolidayName.getText().toString(), editHolidayDate.getText().toString());
                    int listSize = MainActivity.holidayList.size();
                    int cnt = 0;
                    MainActivity.holidayList.add(item);
                    HolidayItem currentItem = MainActivity.holidayList.get(cnt);
                    while(cnt<listSize && item.date.compareTo(currentItem.date)>0) {
                        cnt++;
                        currentItem = MainActivity.holidayList.get(cnt);
                    }
                    if(cnt<listSize) {
                        MainActivity.holidayList.add(cnt, item);
                        MainActivity.holidayList.remove(listSize+1);
                    }
                    try {
                        HolidaysListActivity.RefreshHolidaysList();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(AddHolidayActivity.this, HolidaysListActivity.class);
                    startActivityForResult(intent,1);
                }
                finish();
            }
        });
    }

    private boolean Validate() {
        if (editHolidayName.getText().toString().trim().equals("")) {
            Toast.makeText(AddHolidayActivity.this, "Invalid Holiday Name",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (editHolidayDate.getText().toString().trim().equals("")) {
            Toast.makeText(AddHolidayActivity.this, "Invalid Holiday Date",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date holidayDate = null;
        Date currentDate = new Date();
        try {
            holidayDate = format.parse(editHolidayDate.getText().toString());
        } catch (ParseException e) {
            if (editHolidayDate.getText().toString().trim().equals("")) {
                Toast.makeText(AddHolidayActivity.this, "Invalid Holiday Date",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
                return false;
            }
        }
        if (holidayDate.compareTo(currentDate) <= 0) {
            Toast.makeText(AddHolidayActivity.this, "Date is in the past",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}