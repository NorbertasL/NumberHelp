package com.example.red_spark.numberhelp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ValueFragment.ValueFragmentListener {
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checking for save instance state
        if(savedInstanceState == null) {
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new ValueFragment(), "TestFrag1")
                    .add(R.id.fragment_container, new ValueFragment(), "TestFrag2")
                    .commit();
        }


    }

    @Override
    public void sendValue(String number) {
        ValueFragment valueFagment = (ValueFragment) getSupportFragmentManager()
                                        .findFragmentByTag("TestFrag2");

        valueFagment.setTextField(number);

    }
}
