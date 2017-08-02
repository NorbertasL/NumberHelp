package com.example.red_spark.numberhelp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ValueFragment.ValueFragmentListener {
    private FragmentManager mFragmentManager;

    private ValueFragment decFragment;
    private ValueFragment hexFragment;
    private ValueFragment octFragment;
    private ValueFragment binFragment;
    private ArrayList<ValueFragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checking for save instance state
        if(savedInstanceState == null) {
            mFragmentList = new ArrayList<>();
            decFragment = new ValueFragment();
            hexFragment = new ValueFragment();
            octFragment = new ValueFragment();
            binFragment = new ValueFragment();

            decFragment.setName(Constants.VALUE_TYPE.DEC);
            hexFragment.setName(Constants.VALUE_TYPE.HEX);
            octFragment.setName(Constants.VALUE_TYPE.OCT);
            binFragment.setName(Constants.VALUE_TYPE.BIN);

            mFragmentList.add(decFragment);
            mFragmentList.add(hexFragment);
            mFragmentList.add(octFragment);
            mFragmentList.add(binFragment);

            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, decFragment)
                    .add(R.id.fragment_container, hexFragment)
                    .add(R.id.fragment_container, octFragment)
                    .add(R.id.fragment_container, binFragment)
                    .commit();
        }


    }

    @Override
    public void sendValue(String number, String valueType) {
        int dec;
        if(valueType.equals(Constants.VALUE_TYPE.DEC)){
            dec = Integer.valueOf(number);
        }else {
            dec = ValueConverter.toDec(number, valueType);
        }
        for(ValueFragment frag: mFragmentList){
            if (!frag.getValueType().equals(valueType)){
                frag.setTextField(ValueConverter.convertDecTo(dec, frag.getValueType()));
            }
        }



    }
}
