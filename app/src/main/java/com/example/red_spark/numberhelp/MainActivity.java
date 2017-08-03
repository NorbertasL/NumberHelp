package com.example.red_spark.numberhelp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import com.example.red_spark.numberhelp.tools.ValueConverter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import java.math.BigInteger;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ValueFragment.ValueFragmentListener {
    private FragmentManager mFragmentManager;

    private ValueFragment decFragment;
    private ValueFragment hexFragment;
    private ValueFragment octFragment;
    private ValueFragment binFragment;

    //if i try to use butterkife to bind the view, i get a nullPointException.
    private AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Setting up bottom banner ad
        AdRequest adRequest = new AdRequest.Builder().build();
        adView = (AdView)this.findViewById(R.id.adView);
        adView.loadAd(adRequest);




        mFragmentManager = getSupportFragmentManager();
        //checking for save instance state
        if(savedInstanceState == null) {

            //I know this is a bit messy, ill fix in when I have time
            decFragment = new ValueFragment();
            hexFragment = new ValueFragment();
            octFragment = new ValueFragment();
            binFragment = new ValueFragment();

            decFragment.setName(Constants.VALUE_TYPE.DEC);
            hexFragment.setName(Constants.VALUE_TYPE.HEX);
            octFragment.setName(Constants.VALUE_TYPE.OCT);
            binFragment.setName(Constants.VALUE_TYPE.BIN);

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
        BigInteger dec;
        /*
         * Converst the value into dec value
         * Well use dec value as a base for all the conversions
         */
        if(number.isEmpty()){
            dec =BigInteger.valueOf(0);
        }else if(valueType.equals(Constants.VALUE_TYPE.DEC)){
            dec = new BigInteger(number);
        }else {
            dec = ValueConverter.toDec(number, valueType);
        }

        //Loops trough all the fragments and updates the values
        List<Fragment> fragmentList = mFragmentManager.getFragments();
        for(Fragment fragment: fragmentList){
            ValueFragment myFrag = (ValueFragment) fragment;
            if (!myFrag.getValueType().equals(valueType)){
                myFrag.setTextField(ValueConverter.convertDecTo(dec, myFrag.getValueType()));
            }
        }



    }
}
