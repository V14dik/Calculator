package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_CALCULATOR = "MainActivity.KEY_CALCULATOR";
    private Calculator calculator;


    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();

        text = findViewById(R.id.result);
        text.setText(calculator.getText());

        int [] nubmers = new int[] {
                R.id.zero,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine
        };

        int[] actions = new int[]{
                R.id.minus,
                R.id.plus,
                R.id.division,
                R.id.modulo,
                R.id.multiply,
                R.id.equals
        };
        int[] specificActions = new int[]{
                R.id.ac,
                R.id.inversely,
                R.id.dot
        };

        View.OnClickListener specificActionClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onSpecificActionPressed(view.getId());
                text.setText(calculator.getText());
            }
        };

        View.OnClickListener numberClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onNumberPressed(view.getId());
                text.setText(calculator.getText());
            }
        };

        View.OnClickListener actionClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onActionPressed(view.getId());
                text.setText(calculator.getText());
            }
        };

        for (int i = 0; i < specificActions.length; i++){
            findViewById(specificActions[i]).setOnClickListener(specificActionClick);
        }

        for (int i = 0; i < nubmers.length; i++){
            findViewById(nubmers[i]).setOnClickListener(numberClick);
        }

        for (int i = 0; i < actions.length; i++){
            findViewById(actions[i]).setOnClickListener(actionClick);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putSerializable(KEY_CALCULATOR, calculator);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (Calculator) savedInstanceState.getSerializable(KEY_CALCULATOR);
        text.setText(calculator.getText());
    }
}