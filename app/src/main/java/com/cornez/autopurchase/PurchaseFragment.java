package com.cornez.autopurchase;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PurchaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Return the layout for this fragment
        return inflater.inflate(R.layout.car_cost_fragment, container, false);
    }
}
