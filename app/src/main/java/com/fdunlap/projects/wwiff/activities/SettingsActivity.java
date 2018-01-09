package com.fdunlap.projects.wwiff.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.fdunlap.projects.wwiff.R;

public class SettingsActivity extends AppCompatActivity {

    SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsFragment = settingsFragment.newInstance();

        //Place the fragment into the 'main frame' via a fragment transaction
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.settings_frame, settingsFragment);
        ft.commit();

        //set the content view to actually ~show~ the fragment.
        this.setContentView(R.layout.activity_settings);
    }


}
