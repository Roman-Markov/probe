package com.example.mentalmath;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import io.karim.MaterialTabs;

public class MainActivity extends AbstractPermissionActivity {


    @Override
    public String[] getDesiredPermissions() {
        return new String[] {
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    public void onReady(Bundle state) {
        setContentView(R.layout.main);

//        StrictMode.ThreadPolicy.Builder b = new StrictMode.ThreadPolicy.Builder();
//        if (BuildConfig.DEBUG) {
//            b.detectAll().penaltyDeath();
//        } else {
//            b.detectAll().penaltyLog();
//        }
//        StrictMode.setThreadPolicy(b.build());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(new SampleAdapter(this, getFragmentManager()));

        MaterialTabs tabs = (MaterialTabs) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }

    @Override
    public void onDenied() {
        Toast.makeText(this, "you are not granted permission yet",
                Toast.LENGTH_LONG).show();
        finish();
    }
}
