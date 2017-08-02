package com.example.red_spark.numberhelp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ValueFragment.ValueFragmentListener {
    private FragmentManager mFragmentManager;

    private ValueFragment decFragment;
    private ValueFragment hexFragment;
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

            decFragment.setName(Constants.VALUE_TYPE.DEC);
            hexFragment.setName(Constants.VALUE_TYPE.HEX);

            mFragmentList.add(decFragment);
            mFragmentList.add(hexFragment);

            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, decFragment)
                    .add(R.id.fragment_container, hexFragment)
                    .commit();
        }


    }

    @Override
    public void sendValue(String number, String valueType) {
        for(ValueFragment frag: mFragmentList){
            if (!frag.getValueType().equals(valueType)){
                frag.setTextField(convertValue(number, valueType, frag.getValueType()));
            }
        }



    }
    private static String convertValue(String number, String valueType, String valueConvertType){
        switch (valueConvertType){
            case Constants.VALUE_TYPE.DEC:
                return ValueConverter.hex2Decimal(number)+"";
            case Constants.VALUE_TYPE.HEX:
                return ValueConverter.decimal2Hex(Integer.valueOf(number));
            default:
                return "No Value";

        }



    }
}
