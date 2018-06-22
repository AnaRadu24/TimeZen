package com.anamradu.timezen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HolidayCountdownActivity extends AppCompatActivity {

    private TextView textDaysLeft, textHoursLeft, textMinutesLeft, textSecondsLeft;
    private TextView textUpcomingHoliday;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday_countdown);

        textDaysLeft = (TextView) findViewById(R.id.textView_daysLeft);
        textHoursLeft = (TextView) findViewById(R.id.textHoursLeft);
        textMinutesLeft = (TextView) findViewById(R.id.textMinutesLeft);
        textSecondsLeft = (TextView) findViewById(R.id.textSecondsLeft);
        textUpcomingHoliday = (TextView) findViewById(R.id.textUpcomingHoliday);

        countDownStart();
    }

    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    HolidayItem item = MainActivity.holidayList.get(0);
                    Date futureDate = dateFormat.parse(item.date);
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        textDaysLeft.setText("" + String.format("%02d", days));
                        textHoursLeft.setText("" + String.format("%02d", hours));
                        textMinutesLeft.setText("" + String.format("%02d", minutes));
                        textSecondsLeft.setText("" + String.format("%02d", seconds));
                        textUpcomingHoliday.setText(item.name);
                    } else {
                        textUpcomingHoliday.setVisibility(View.VISIBLE);
                        textUpcomingHoliday.setText("The event started!");
                        textViewGone();
                        HolidaysListActivity.RefreshHolidaysList();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

    public void textViewGone() {
        findViewById(R.id.LinearLayout1).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout2).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout3).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout4).setVisibility(View.GONE);
    }
    public void OpenHolidaysListActivity(View v){
        Intent intent = new Intent(HolidayCountdownActivity.this, HolidaysListActivity.class);
        startActivityForResult(intent,1);
    }
}
