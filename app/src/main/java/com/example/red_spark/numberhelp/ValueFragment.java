package com.example.red_spark.numberhelp;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ValueFragment extends Fragment {
    @BindView(R.id.et_NumEditTextField) EditText mTextField;
    @BindView(R.id.bt_Convert) Button mConvertButton;

    //Used by butterknife to set views to null
    private Unbinder unbinder;
    private ValueFragmentListener mListener;

    public interface ValueFragmentListener{
        public void sendValue(String number);
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
        mListener.sendValue(value);
    }
    public void setTextField(String number){
        mTextField.setText(number);
    }

}