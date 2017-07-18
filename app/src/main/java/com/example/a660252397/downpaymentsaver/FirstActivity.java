package com.example.a660252397.downpaymentsaver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import java.text.NumberFormat;

public class FirstActivity extends AppCompatActivity implements OnEditorActionListener {

    //set widget variables
    private EditText expenseEditTextView;
    private EditText incomeEditTextView;
    private TextView savingsLabelTextView;

    //set instance variables
    public float expense;
    public float income;
    public float savings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //get reference to widgets
        expenseEditTextView = (EditText) findViewById(R.id.expenseEditTextView);
        incomeEditTextView = (EditText) findViewById(R.id.incomeEditTextView);
        savingsLabelTextView = (TextView) findViewById(R.id.savingsLabelTextView);

        //set listeners
        expenseEditTextView.setOnEditorActionListener(this);
        incomeEditTextView.setOnEditorActionListener(this);


    }


    /*******************************************************************
            Event Listener
     ********************************************************************/
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }

    /*******************************************************************
      Calculate savings and display
     ********************************************************************/
    private void calculateAndDisplay() {

        String expenseString = expenseEditTextView.getText().toString();
        String incomeString = incomeEditTextView.getText().toString();

        expense = Float.parseFloat(expenseString);
        income = Float.parseFloat(incomeString);

        savings = income - expense;

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        savingsLabelTextView.setText(currency.format(savings));
    }
    /**************************************************
           onclick method to move to the second page
     ****************************************************/
    public void onClickNext(View view) {
        //save information and pass to the next page when the next button is clicked
        final TextView savingsLabelTextView = (TextView) findViewById(R.id.savingsLabelTextView);
        Intent i = new Intent (this, secondActivity.class);

        String msg = savingsLabelTextView.getText().toString();
        i.putExtra("msg",msg);

        startActivity(i);
    }

    /**************************************************
     onclick method to clear entered info
     ****************************************************/

    public void onClickClear(View view) {
        expenseEditTextView.setText("");
        incomeEditTextView.setText("");

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        savings = 0 ;
        savingsLabelTextView.setText(currency.format(savings));
    }


}
