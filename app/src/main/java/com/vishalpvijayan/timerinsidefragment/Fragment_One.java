package com.vishalpvijayan.timerinsidefragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_One extends Fragment {

    TextView countSecond;
    TextView countDays;
    TextView countHour;
    TextView countMinute;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;
    long millisInput;
    private String input = "18100",days,hour,minutes,seconds;


//    @Override
//    public void onResume() {
//        super.onResume();
//        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putLong("startTimeInMillis", mStartTimeInMillis);
//        editor.putLong("millisLeft", mTimeLeftInMillis);
//        editor.putBoolean("timerRunning", mTimerRunning);
//        editor.putLong("endTime", mEndTime);
//        editor.apply();
//        if (mCountDownTimer != null) {
//            mCountDownTimer.cancel();
//        }
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
//        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
//        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
//        mTimerRunning = prefs.getBoolean("timerRunning", false);
//        updateCountDownText();
//        updateWatchInterface();
//        if (mTimerRunning) {
//            mEndTime = prefs.getLong("endTime", 0);
//            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
//            if (mTimeLeftInMillis < 0) {
//                mTimeLeftInMillis = 0;
//                mTimerRunning = false;
//                updateCountDownText();
//                updateWatchInterface();
//            } else {
//                startTimer();
//            }
//        }
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__one, container, false);
        countSecond = view.findViewById(R.id.countSecond);
        countDays = view.findViewById(R.id.countDays);
        countHour = view.findViewById(R.id.countHour);
        countMinute = view.findViewById(R.id.countMinute);

//        timer();
        millisInput = Long.parseLong(input) * 60000;
        setTime(millisInput);
        if(mTimerRunning){

        }else {
            startTimer();
        }


        return view;
    }



    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//        new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateWatchInterface();
            }
        }.start();
        mTimerRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer() {
//        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }

    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }

    private void updateCountDownText() {
        final long dy = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis);
        final long hr = TimeUnit.MILLISECONDS.toHours(mTimeLeftInMillis)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis));
        final long min = TimeUnit.MILLISECONDS.toMinutes(mTimeLeftInMillis)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mTimeLeftInMillis));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(mTimeLeftInMillis)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mTimeLeftInMillis));
        final long ms = TimeUnit.MILLISECONDS.toMillis(mTimeLeftInMillis)
                - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(mTimeLeftInMillis));

        String timeLeftFormatted;
        if (hr > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d %d %02d %02d", dy, hr, min, sec);
            days = String.format(Locale.getDefault(),"%d",dy);
            hour = String.format(Locale.getDefault(),"%d",hr);
            minutes = String.format(Locale.getDefault(),"%d",min);
            seconds = String.format(Locale.getDefault(),"%d",sec);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d %d %02d %02d", dy, hr, min, sec);
             days = String.format(Locale.getDefault(),"%d",dy);
             hour = String.format(Locale.getDefault(),"%d",hr);
             minutes = String.format(Locale.getDefault(),"%d",min);
             seconds = String.format(Locale.getDefault(),"%d",sec);
        }
        countSecond.setText(seconds);
        countMinute.setText(minutes);
        countHour.setText(hour);
        countDays.setText(days);
    }

    private void updateWatchInterface() {
        if (mTimerRunning) {

        }


    }


}


