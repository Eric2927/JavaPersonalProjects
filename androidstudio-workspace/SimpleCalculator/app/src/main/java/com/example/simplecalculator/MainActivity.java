package com.example.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view) {
        EditText numOneText = (EditText) findViewById(R.id.firstNum);
        EditText numTwoText = (EditText) findViewById(R.id.secondNum);
        double numOne = Double.parseDouble(numOneText.getText().toString());
        double numTwo = Double.parseDouble(numTwoText.getText().toString());
        double sum = numOne + numTwo;
        double difference = numOne - numTwo;
        double product = numOne * numTwo;
        double quotient = numOne / numTwo;

        TextView answerText = (TextView) findViewById(R.id.answerLabel);
        answerText.setText("Sum: " + sum + "\nDifference: " + difference + "\nProduct: " + product + "\nQuotient: " + quotient);
    }
}
