package com.example.yx304e.timerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 120000;

    private TextView mTextViewCountDown;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonReset = findViewById(R.id.button_reset);

        startTimer();

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.cancel();
                resetTimer();
                startTimer();
            }
        });

        updateCountDownText();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mButtonReset.setVisibility(View.INVISIBLE);
                mTextViewCountDown.setText("Done");
            }
        }.start();

        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        long newTimeLeft = mTimeLeftInMillis + 10000;
        if(newTimeLeft < START_TIME_IN_MILLIS) {
            mTimeLeftInMillis = newTimeLeft;
        } else {
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
        }
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        int milliseconds = (int) (mTimeLeftInMillis / 100) % 10;

        System.out.println(minutes + " " + seconds + " " + milliseconds);

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%01d", minutes, seconds, milliseconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}
