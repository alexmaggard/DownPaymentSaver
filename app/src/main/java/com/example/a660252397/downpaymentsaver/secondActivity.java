package com.example.a660252397.downpaymentsaver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.text.NumberFormat;


public class secondActivity extends AppCompatActivity implements OnEditorActionListener {

    //set widget variables
    private TextView savingsTextViewLabel;
    private EditText itemValueEditTextView;
    private EditText downPayPerEditTextView;
    private EditText interestRateEditTextView;
    private EditText savingsEditTextView;
    private TextView downPayPreviewTextView;

    //set instance variables
    float item;
    float dpp;
    float interest;
    float savings;
    float dpPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //retrieve bundle to bring passed information from first intent
        Bundle data = getIntent().getExtras();
        if(data == null){
            return;
        }

        //apply info to the current text view on page 2 of the app
        String msg = data.getString("msg");
        final TextView savingsTextViewLabel = (TextView) findViewById(R.id.savingsTextViewLabel);
        savingsTextViewLabel.setText(msg);

        //get reference to widgets
        itemValueEditTextView = (EditText) findViewById(R.id.itemValueEditTextView);
        downPayPerEditTextView = (EditText) findViewById(R.id.downpayperEditTextView);
        interestRateEditTextView = (EditText) findViewById(R.id.interestRateEditTextView);
        savingsEditTextView = (EditText) findViewById(R.id.savingsEditTextView);
        downPayPreviewTextView = (TextView) findViewById(R.id.downPayPreviewTextView);


        //set listeners
        itemValueEditTextView.setOnEditorActionListener(this);
        downPayPreviewTextView.setOnEditorActionListener(this);
        interestRateEditTextView.setOnEditorActionListener(this);
        savingsEditTextView.setOnEditorActionListener(this);
    }

    /**********************************************
        onclick methods for buttons
     **********************************************/
    //move to final page
    public void onClickNext(View view) {
        //save information and pass to the next page when the next button is clicked
        final TextView downPayPreviewTextView = (TextView) findViewById(R.id.downPayPreviewTextView);
        Intent i = new Intent (this, thirdActivity.class);

        String msg = downPayPreviewTextView.getText().toString();
        i.putExtra("msg",msg);

        startActivity(i);
    }
    //clear submitted information
    public void onClickClear(View view) {
        itemValueEditTextView.setText("");
        downPayPerEditTextView.setText("");
        interestRateEditTextView.setText("");
        savingsEditTextView.setText("");
        //TODO CHANGE SET TEXT ON DOWNPAYPREVIEW TO BE A NUMBER $0.00
        downPayPreviewTextView.setText("");
    }
    //return to page 1 of app
    public void onClickBack(View view) {
        Intent i = new Intent (this, secondActivity.class);

        String msg = savingsTextViewLabel.getText().toString();
        i.putExtra("msg",msg);

        startActivity(i);
    }
    /**********************************************
      listener for onEditorActions
     **********************************************/
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();
        return false;
    }
    /*******************************************************
        calculation method
     *******************************************************/
    private void calculateAndDisplay() {
        String itemString = itemValueEditTextView.getText().toString();
        String dppString = downPayPerEditTextView.getText().toString();
        String interestString = interestRateEditTextView.getText().toString();
        String savingsString = savingsEditTextView.getText().toString();

        item = Float.parseFloat(itemString);
        dpp = Float.parseFloat(dppString);
        interest = Float.parseFloat(interestString);
        savings = Float.parseFloat(savingsString);
        //TODO:FINISH CALCULATE METHOD
        dpPreview = (item*interest*dpp);

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        downPayPreviewTextView.setText(currency.format(dpPreview));

    }
}
