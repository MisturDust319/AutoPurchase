package com.cornez.autopurchase;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LoanSummaryFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Return the layout for this fragment
        return inflater.inflate(R.layout.loansummary_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // retrieve data for fragment display
        TextView monthlyPayET = (TextView) getActivity().findViewById(R.id.textView2);
        TextView loanReportET = (TextView) getActivity().findViewById(R.id.textView3);

        String report;
        report = getArguments().getString("LoanReport");

        String monthlyPay;
        monthlyPay = getArguments().getString("MonthlyPayment");
        monthlyPayET.setText(monthlyPay);
        loanReportET.setText(report);

    }
}
