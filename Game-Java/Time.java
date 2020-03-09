package com.company;



import java.util.Timer;
import java.util.TimerTask;

public class Time {
    private int seconds = 0;

    Timer timer= new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            seconds ++;
            //System.out.println(seconds);
        }
    };

    public void start()
    {
        timer.scheduleAtFixedRate(task, 1000,1000);
    }

    public int getSeconds() {
        return seconds;
    }

    public void resetSeconds() {
        seconds=0;
    }

    public void setSeconds(int x) {
        seconds=x;
    }



}

