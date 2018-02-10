package com.example.rishi.hw1;
//Assignment #HW1
//File Name  #HW1
//Full Name #Bhavya Gedi,#Rosy Azad(Homework Group 25)
// MaxSDK is 26 , minSDK is 24

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    String display = "";
    String display2 = "";
    boolean nextInput = false;
    TextView resultText;
    public String currentOperator;
    String operatorClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_relative);

        Button one = (Button) findViewById(R.id.buttonone);
        Button two = (Button) findViewById(R.id.buttontwo);
        Button three = (Button) findViewById(R.id.buttonthree);
        Button four = (Button) findViewById(R.id.buttonfour);
        Button five = (Button) findViewById(R.id.buttonfive);
        Button six = (Button) findViewById(R.id.buttonsix);
        Button seven = (Button) findViewById(R.id.buttonseven);
        Button eight = (Button) findViewById(R.id.buttone8);
        Button nine = (Button) findViewById(R.id.button9);
        Button zero = (Button) findViewById(R.id.buttonzero);
        Button clearButton = (Button) findViewById(R.id.buttone8);
        resultText = (TextView) findViewById(R.id.resultText);

    }

    public void resultScreen() {
        resultText.setText(display);
        Log.d("rdemo", display);
    }

    public void clear() {

        resultText.setText("0");
    }

    public void onClickClear(View v) {
        clear();
        display = "";
        display2 = "";
    }

    public void onClickNumber(View v) {
        Button b = (Button) v;
        if (nextInput) {
            clear();
            nextInput = false;
            if (display2 == "") {
                display2 = "0";
            }

        }
        if (operatorClicked == "") {
            if (!display.equals(".")) {
                display = "";
            }
            operatorClicked = currentOperator;
        }

        display += b.getText();
        // Log.d("demo", display);
        resultScreen();

    }

    public void onClickOperator(View v) {
        Button b = (Button) v;
        if (!nextInput) {
            display2 = display;
        }
        display = "";
        currentOperator = b.getText().toString();
        Log.d("emo2", display2);
        Log.d("operator", currentOperator);
        nextInput = true;
    }

    public void onClickDot(View v) {
        Button b = (Button) v;
        if (display.contains(".")) {
            return;
        } else {
            display += b.getText();
        }
        resultScreen();
    }

    public double operate() {
        switch (currentOperator) {
            case "+":
                if (display2 == "") {
                    display2 = "0";
                }
                return Double.valueOf(display) + Double.valueOf(display2);
            case "-":
                if (display2 == "") {
                    display2 = "0";
                }
                if (Double.valueOf(display2) < Double.valueOf(display)) {
                    return Double.valueOf(display) - Double.valueOf(display2);

                } else
                    return Double.valueOf(display2) - Double.valueOf(display);

            case "/":
                try {
                    if (display2 == "") {
                        return Double.valueOf(display);
                    }


                    return Double.valueOf(display2) / Double.valueOf(display);

                } catch (Exception e) {
                    Log.d("exception", e.getMessage());
                    return Double.valueOf(display);
                }

            case "*":
                if (display2 == "") {
                    display2 = "1";
                }
                return Double.valueOf(display) * Double.valueOf(display2);
            default:
                return Double.valueOf(display);
        }

    }

    DecimalFormat myformatter = new DecimalFormat("0.########");

    public void onClickEqual(View v) {
        operatorClicked = currentOperator;
        // Log.d("demoOperator",currentOperator);
        Double result = 0.0;
        //int resultLength;
        int flag = 0;
        clear();
        if (display2 == "" && display == "" && !nextInput) {
            clear();
            flag = 1;


        }
        if (display == "") {
            flag = 1;
        }

        try {
            result = operate();
        } catch (Exception e) {
            Log.d("NullException", e.getMessage());
        }
        String temp = myformatter.format(result);
        Log.d("Sign", temp);

        if (temp.length() > 14) {
            if (temp.contains(".")) {
                //resultText.setText(temp.substring(0, 15));
                resultText.setText("Out of memory");
            } else {
                //resultText.setText(temp.substring(0, 14));
                resultText.setText("Out of memory");
                Log.d("fourteen", temp.substring(0, 14));
            }
        } else
            resultText.setText(String.valueOf(temp));
        Log.d("result", String.valueOf(result));
        //if ((operatorClicked == "/" || operatorClicked == "*" || operatorClicked == "+" || operatorClicked == "-") && display=="")

        if (flag == 0) {
            Log.d("FLAG", String.valueOf(flag));
            display = String.valueOf(result);
        }
        if (operatorClicked != null && !operatorClicked.isEmpty() && display == "") {
            resultText.setText(display2);
        } else {
            display2 = "";
        }
        if (nextInput) {
            resultText.setText((display2));
        }
        Log.d("Memory", display);
        operatorClicked = "";
    }
}





