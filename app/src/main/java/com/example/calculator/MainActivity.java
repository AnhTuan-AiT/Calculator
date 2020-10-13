package com.example.calculator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private static final char ADDITION = '+';

    private static final char SUBTRACTION = '-';

    private static final char MULTIPLICATION = '*';

    private static final char DIVISION = '/';

    private List<Double> values;

    private List<Character> operators;

    private String newOperand;

    private DecimalFormat decimalFormat;

    private Button buttonZero;

    private Button buttonOne;

    private Button buttonTwo;

    private Button buttonThree;

    private Button buttonFour;

    private Button buttonFive;

    private Button buttonSix;

    private Button buttonSeven;

    private Button buttonEight;

    private Button buttonNine;

    private Button buttonAdd;

    private Button buttonSubtract;

    private Button buttonMultiply;

    private Button buttonDivide;

    private Button buttonDot;

    private Button buttonEqual;

    private Button buttonClear;

    private boolean isNewOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");
        values = new ArrayList<>();
        operators = new ArrayList<>();
        newOperand = "";
        isNewOperation = true;

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf");
        textView = findViewById(R.id.textView);
        textView.setTypeface(tf);

        textView.setText("");

        buttonZero = findViewById(R.id.buttonZero);
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonDot = findViewById(R.id.buttonDot);
        buttonClear = findViewById(R.id.buttonClear);
        buttonEqual = findViewById(R.id.buttonEqual);

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(textView.getText() + ".");
            }
        });

        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "0";
                textView.setText(textView.getText() + "0");
            }
        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "1";
                textView.setText(textView.getText() + "1");
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "2";
                textView.setText(textView.getText() + "2");
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "3";
                textView.setText(textView.getText() + "3");
            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "4";
                textView.setText(textView.getText() + "4");
            }
        });

        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "5";
                textView.setText(textView.getText() + "5");
            }
        });

        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "6";
                textView.setText(textView.getText() + "6");
            }
        });

        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "7";
                textView.setText(textView.getText() + "7");
            }
        });

        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "8";
                textView.setText(textView.getText() + "8");
            }
        });

        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNewOperation) {
                    isNewOperation = true;
                    textView.setText("");
                }

                newOperand += "9";
                textView.setText(textView.getText() + "9");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newOperand.equals("")) {
                    values.add(Double.parseDouble(newOperand));
                    operators.add(ADDITION);
                    textView.setText(textView.getText().toString() + ADDITION);
                    newOperand = "";
                }
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newOperand.equals("")) {
                    values.add(Double.parseDouble(newOperand));
                    operators.add(SUBTRACTION);
                    textView.setText(textView.getText().toString() + SUBTRACTION);
                    newOperand = "";
                }
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newOperand.equals("")) {
                    values.add(Double.parseDouble(newOperand));
                    operators.add(MULTIPLICATION);
                    textView.setText(textView.getText().toString() + MULTIPLICATION);
                    newOperand = "";
                }
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newOperand.equals("")) {
                    values.add(Double.parseDouble(newOperand));
                    operators.add(DIVISION);
                    textView.setText(textView.getText().toString() + DIVISION);
                    newOperand = "";
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.add(Double.parseDouble(newOperand));
                textView.setText(computeCalculation());
                values = new ArrayList<>();
                operators = new ArrayList<>();
                newOperand = "";
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (textView.getText().length() > 0) {
                    CharSequence currentText = textView.getText();
                    textView.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    textView.setText("");
                }
            }
        });
    }

    private String computeCalculation() {
        isNewOperation = false;

        if (values.size() == 0) {
            return "";
        }

        double result = values.get(0);

        if (values.size() == 1) {
            if (operators.size() > 0) {
                if (operators.get(0) == ADDITION)
                    result *= 2;
                else if (operators.get(0) == SUBTRACTION)
                    result = 0;
                else if (operators.get(0) == MULTIPLICATION)
                    result = result * result;
                else if (operators.get(0) == DIVISION)
                    result = 1;
            }

            return String.valueOf(result);
        } else {
            for (int i = 1; i < values.size(); i++) {
                if (operators.get(i - 1) == ADDITION)
                    result += values.get(i);
                else if (operators.get(i - 1) == SUBTRACTION)
                    result -= values.get(i);
                else if (operators.get(i - 1) == MULTIPLICATION)
                    result *= values.get(i);
                else if (operators.get(i - 1) == DIVISION) {
                    if (values.get(i) == 0) {
                        return "Cannot divide by zero";
                    } else {
                        result /= values.get(i);
                    }
                }

            }

            return String.valueOf(result);
        }
    }
}