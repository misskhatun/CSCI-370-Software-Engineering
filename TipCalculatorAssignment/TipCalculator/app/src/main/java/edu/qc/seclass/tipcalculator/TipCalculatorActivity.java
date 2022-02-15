package edu.qc.seclass.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class TipCalculatorActivity extends AppCompatActivity {
    double TotalBill;
    int numOfPeople;

    EditText checkAmount;
    EditText partySize;
    Button computeButton;

    EditText Tip15p;
    EditText Tip20p;
    EditText Tip25p;

    EditText Total15p;
    EditText Total20p;
    EditText Total25p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAmount = findViewById(R.id.checkAmountValue);
        partySize = findViewById(R.id.partySizeValue);
        computeButton = findViewById(R.id.buttonCompute);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast msg1= Toast.makeText(getApplicationContext(), "Enter the party size", Toast.LENGTH_LONG);
                Toast msg2=Toast.makeText(getApplicationContext(), "Enter the check amount", Toast.LENGTH_LONG);
                Toast msg3=Toast.makeText(getApplicationContext(), "Check amount cannot be divided by 0", Toast.LENGTH_LONG);


                 if (partySize.getText().toString().length() == 0){
                     msg1.show();
                }

                else if(checkAmount.getText().toString().length() == 0){
                    msg2.show();
                }

                else if(Integer.valueOf(partySize.getText().toString()) == 0) {
                    msg3.show();
                }

                else{
                    TotalBill = Double.valueOf(checkAmount.getText().toString());
                    numOfPeople = Integer.valueOf((partySize.getText().toString()));
                    Tip15p = findViewById(R.id.fifteenPercentTipValue);
                    Tip20p = findViewById(R.id.twentyPercentTipValue);
                    Tip25p = findViewById(R.id.twentyfivePercentTipValue);
                    Total15p = findViewById(R.id.fifteenPercentTotalValue);
                    Total20p = findViewById(R.id.twentyPercentTotalValue);
                    Total25p = findViewById(R.id.twentyfivePercentTotalValue);



                    Tip15p.setText(String.valueOf(calculateTip(TotalBill, numOfPeople, 0.15)));
                    Tip20p.setText(String.valueOf(calculateTip(TotalBill, numOfPeople, 0.20)));
                    Tip25p.setText(String.valueOf(calculateTip(TotalBill, numOfPeople, 0.25)));
                    Total15p.setText(String.valueOf(calculateTotal(TotalBill, numOfPeople, 0.15)));
                    Total20p.setText(String.valueOf(calculateTotal(TotalBill, numOfPeople, 0.20)));
                    Total25p.setText(String.valueOf(calculateTotal(TotalBill, numOfPeople , 0.25)));


                }

            }
        });
    }

    public int calculateTip(double TotalBill, int numOfPeople, double percent){
        int TotalEach=(int) Math.ceil(TotalBill / numOfPeople);
        int total = (int) Math.ceil(TotalEach * percent);
        return  total;
    }

    public int calculateTotal(double TotalBill, int numOfPeople, double percent){
        double TotalBillEach= TotalBill / numOfPeople;
        double tip = (TotalBillEach * percent);
        int total = (int) Math.ceil(TotalBillEach + tip);
        return  total;
    }


}