package com.fdunlap.projects.wwiff.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.fdunlap.projects.wwiff.R;
import com.fdunlap.projects.wwiff.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        mainFragment = MainFragment.newInstance();

        //Place the fragment into the 'main frame' via a fragment transaction
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_frame, mainFragment);
        ft.commit();

        //set the content view to actually ~show~ the fragment.
        this.setContentView(R.layout.activity_main);
    }


}
