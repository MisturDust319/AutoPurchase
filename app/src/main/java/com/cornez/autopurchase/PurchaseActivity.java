package com.cornez.autopurchase;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PurchaseActivity extends AppCompatActivity {
    // THE AUTO OBJECT CONTAINS THE INFORMATION ABOUT THE VEHICLE BEING PURCHASED
    Auto mAuto;

    // THE DATA TO BE PASSED TO THE LOAN ACTIVITY
    String loanReport;
    String monthlyPayment;

//    // LAYOUT INPUT REFERENCES
//    private EditText carPriceET;
//    private EditText downPayET;
//    private RadioGroup loanTermRG;
    // INPUT FIELD DATA
    private double carPrice;
    private double downPayment;
    private String loanTerm;

    // FRAGMENT OBJECTS
    private Fragment carCostFragment;

    private Fragment loanSummaryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);

        // set up toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(null);

        // CREATE AND BIND FRAGMENTS
        carCostFragment = new PurchaseFragment();
        loanSummaryFragment = new LoanSummaryFragment();
        attachFragment(carCostFragment);

//        //ESTABLISH REFERENCES TO EDITABLE TEXT FIELDS AND RADIO BUTTON
//        carPriceET = (EditText) findViewById(R.id.editText1);
//        downPayET = (EditText) findViewById(R.id.editText2);
//        loanTermRG = (RadioGroup) findViewById(R.id.radioGroup1);

        //CREATE AN AUTOMOBILE OBJECT TO STORE AUTO DATA
        mAuto = new Auto();
    }

    private void collectAutoInputData() {
        // TASK 1: SET THE CAR PRICE
//        mAuto.setPrice ((double) Integer.1valueOf(carPriceET.getText()
//                .toString()));
        mAuto.setPrice(carPrice);

        //TASK 2: SET THE DOWN PAYMENT
//        mAuto.setDownPayment((double)
//                Integer.valueOf(downPayET.getText()
//                        .toString()));
        mAuto.setDownPayment(downPayment);

        //TASK 3 SET THE LOAN TERM
//        Integer radioId = loanTermRG.getCheckedRadioButtonId();
//        RadioButton term = (RadioButton) findViewById(radioId);
//        mAuto.setLoanTerm(term.getText().toString());
        mAuto.setLoanTerm(loanTerm);
    }
    private void buildLoanReport() {
        // TASK 1: CONSTRUCT THE MONTHLY PAYMENT
        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1)
                + String.format("%.02f", mAuto.monthlyPayment());


        // TASK 2: CONSTRUCT THE LOAN REPORT
        loanReport = res.getString(R.string.report_line6)
                + String.format("%10.02f", mAuto.getPrice());
        loanReport += res.getString(R.string.report_line7)
                + String.format("%10.02f", mAuto.getDownPayment());

        loanReport += res.getString(R.string.report_line9)
                + String.format("%18.02f", mAuto.taxAmount());
        loanReport += res.getString(R.string.report_line10)
                + String.format("%18.02f", mAuto.totalCost());
        loanReport += res.getString(R.string.report_line11)
                + String.format("%12.02f", mAuto.borrowedAmount());
        loanReport += res.getString(R.string.report_line12)
                + String.format("%12.02f", mAuto.interestAmount());

        loanReport += "\n\n" + res.getString(R.string.report_line8) + " " + mAuto.getLoanTerm() + " years.";

        loanReport += "\n\n" + res.getString(R.string.report_line2);
        loanReport += res.getString(R.string.report_line3);
        loanReport += res.getString(R.string.report_line4);
        loanReport += res.getString(R.string.report_line5);

    }

    // Attach a Fragment to the Activity
    private void attachFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void activateLoanSummary() {
        //TASK 1: BUILD A LOAN REPORT FROM THE INPUT DATA
        collectAutoInputData();
        buildLoanReport();

//        //TASK 2: CREATE AN INTENT TO DISPLAY THE LOAN SUMMARY ACTIVITY
//        Intent launchReport = new Intent(this, LoanSummaryActivity.class);
//
//        //TASK 3: PASS THE LOAN SUMMARY ACTIVITY TWO PIECES OF DATA:
//        //     THE LOAN REPORT CONTAINING LOAN DETAILS
//        //     THE MONTHLY PAYMENT
//        launchReport.putExtra("LoanReport", loanReport);
//        launchReport.putExtra("MonthlyPayment", monthlyPayment);
        // CREATE DATA BUNDLE TO PASS TO FRAGMENT
        Bundle launchReport = new Bundle();
        launchReport.putString("LoanReport", loanReport);
        launchReport.putString("MonthlyPayment", monthlyPayment);
        // ATTACH DATA TO FRAGMENT
        loanSummaryFragment.setArguments(launchReport);

        //TASK 4: START THE LOAN ACTIVITY
//        startActivity(launchReport);


        attachFragment(loanSummaryFragment);
    }

    // this sets the default values for the input fields
    public void initFields() {
        carPrice = 0;
        downPayment = 0;
        loanTerm = getResources().getString(R.string.years2);
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public void setLoanTerm(String loanTerm) {
        this.loanTerm = loanTerm;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.my, menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_car_cost:
                attachFragment(carCostFragment);
                return true;
            case R.id.action_loan_report:
                activateLoanSummary();
                return true;
        }

        return true;
    }

}
