package com.example.red_spark.numberhelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ValueFragment extends Fragment {
    @BindView(R.id.et_NumEditTextField) EditText mTextField;
    @BindView(R.id.bt_Convert) Button mConvertButton;
    @BindView(R.id.tv_ValueName)TextView mName;

    //Used by butterknife to set views to null
    private Unbinder unbinder;
    private ValueFragmentListener mListener;
    private String valueType = "";

    public interface ValueFragmentListener{
        public void sendValue(String number, String valueType);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (ValueFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public ValueFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_value, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mName.setText(valueType);

        mTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
             public void afterTextChanged(Editable s) {
                if(mTextField.hasFocus()) {
                    if(!inputCheck(mTextField.getText().toString())){
                        String temp = mTextField.getText().toString();
                        temp = temp.substring(0, temp.length()-1);
                        mTextField.setText(temp);

                        //setSelection moves the cursor to the end of the text field
                        mTextField.setSelection(temp.length());

                    }else {
                        sendData();
                    }
                }
             }
        });

        mConvertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sendData();
            }
        });


        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //setting the view to null, we have to do it for fragments.
        unbinder.unbind();

    }

    //send the data to the host activity
    private void sendData(){
        String value = mTextField.getText().toString();
        mListener.sendValue(value, valueType);
    }

    //method to set number value
    public void setTextField(String number){
        mTextField.setText(number);
    }
    public void setName(String name){
        valueType =  name;
    }

    public String getValueType(){
        return valueType;
    }

    //check if the user inputs is valid
    public boolean inputCheck(String s){
        //TODO implement negative numbers too

        if(s.isEmpty()){
            return true;
        }
        switch (valueType){
            //tries to cast the string into a long.If it fails that means the string contains
            //non-numeric values
            case Constants.VALUE_TYPE.DEC:
                try{
                    Long.valueOf(s);
                    return true;
                }catch (NumberFormatException e){
                    return false;
                }
            case Constants.VALUE_TYPE.OCT:
                //tries to cast the string into a long.If it fails that means the string contains
                //non-numeric values
                try{
                    Long.valueOf(s);
                    for(int i = 0; i< s.length(); i++){
                        //have to use Character.getNumericValue or we get wrong value
                        int digit = Integer.valueOf(Character.getNumericValue(s.charAt(i)));
                        //check if any of the digits are > or = to 8(Oct is a base of 8)
                        //if it is its not a valid number
                        if(digit >= 8) {
                            return false;
                        }
                    }

                    return true;
                }catch (NumberFormatException e){
                    return false;
                }

        }

        return false;
    }

}