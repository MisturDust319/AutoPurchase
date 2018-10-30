package com.cornez.autopurchase;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PurchaseFragment extends Fragment {

    // LAYOUT INPUT REFERENCES
    private EditText carPriceET;
    private EditText downPayET;
    private RadioGroup loanTermRG;

    //
    double carPrice;
    double downPayment;
    String loanTerm;
    int currentLoanTerm; // radio button id for loan term

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Return the layout for this fragment
        return inflater.inflate(R.layout.car_cost_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // get button data from fragment layout
        //ESTABLISH REFERENCES TO EDITABLE TEXT FIELDS AND RADIO BUTTON
        carPriceET = (EditText) view.findViewById(R.id.editText1);
        downPayET = (EditText) view.findViewById(R.id.editText2);
        loanTermRG = (RadioGroup) view.findViewById(R.id.radioGroup1);


        // init program side field values
        ((PurchaseActivity) getActivity()).initFields();

        // set event listeners
        // radio buttons
        loanTermRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                setLoanTerm(i);
            }
        });

        carPriceET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                setCarPrice();
            }
        });

        downPayET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                setDownPayment();
            }
        });

    }

    public void setCarPrice() {
        String valStr = carPriceET.getText().toString();
        if(valStr.equals("")) {
            valStr = "0";
        }
        carPrice = ((double) Integer.valueOf(valStr));

        ((PurchaseActivity) getActivity()).setCarPrice(carPrice);
    }

    public void setDownPayment() {
        String valStr = downPayET.getText().toString();
        if(valStr.equals("")) {
            valStr = "0";
        }

        downPayment = ((double) Integer.valueOf(valStr));
        ((PurchaseActivity) getActivity()).setDownPayment(downPayment);
    }

    public void setLoanTerm(int i) {
        RadioButton radioButton = (RadioButton) getActivity().findViewById(i);
        String term = radioButton.getText().toString();

        ((PurchaseActivity) getActivity()).setLoanTerm(term);
    }
}
