package com.example.harshit.demoschedule;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Start(View view)
    {
        ComponentName componentName=new ComponentName(this,JobServiceClass.class);
        JobInfo info=new JobInfo.Builder(321,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true).setPeriodic(15*60*1000)
                .build();

        JobScheduler jobScheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode=jobScheduler.schedule(info);
        if (resultCode==JobScheduler.RESULT_SUCCESS)
        {
            Log.d(TAG, "Start: Task Success");
        }
        else
        {
            Log.d(TAG, "Start: Task failed");
        }
    }

    public void Stop(View view) {
        JobScheduler scheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(321);
    }
}
