package com.example.harshit.demoschedule;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class JobServiceClass extends JobService {
    public static final String TAG="JobServiceClass";
    private boolean jobCanceled=false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: ");
        //inside this method we perform the operation which we have to do in background when we minimize our application then also it can be executed
        onBackground(params);
        //We return true when task is incomplete and written false when task is completed
        return true;
    }
    void onBackground(final JobParameters params)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //This is the method inside which we perform the task which we have to complete

                for (int i=0;i<10;i++)
                {
                    Log.d(TAG, "run: "+i);
                    if (jobCanceled)
                    {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "run: Job Finished");
                jobFinished(params,false);

            }
        }).start();

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: Job Stop before completing");
        jobCanceled=true;
        return false;
    }
}
