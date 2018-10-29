package com.example.user.week1weekend;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //declare boolean, textviews, buttons
    boolean pressedEquals;
    boolean pressedSciKey;
    TextView tvInput, tvResult;
    Button btnAC, btnDiv, btnMult, btnPlus, btnMinus, btnEquals, btnDot, btnZero, btnOne;
    Button btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    Button btnSin, btnCos, btnTan, btnX2, btnSqrt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind java variables to xml values
        pressedEquals = false;
        pressedSciKey = false;
        tvInput = findViewById(R.id.tvInput);
        tvResult = findViewById(R.id.tvResult);
        btnAC = findViewById(R.id.btnAC);
        btnDiv = findViewById(R.id.btnDiv);
        btnMult = findViewById(R.id.btnMult);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnEquals = findViewById(R.id.btnEquals);
        btnDot = findViewById(R.id.btnDot);
        btnZero = findViewById(R.id.btnZero);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnX2 = findViewById(R.id.btnX2);
        btnSqrt = findViewById(R.id.btnSqrt);

        //set onclicklistener for each button
        btnAC.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        //additional buttons set during landscape only
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            btnSin.setOnClickListener(this);
            btnCos.setOnClickListener(this);
            btnTan.setOnClickListener(this);
            btnX2.setOnClickListener(this);
            btnSqrt.setOnClickListener(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("InputText", tvInput.getText().toString());
        outState.putString("ResultText", tvResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String input = savedInstanceState.getString("InputText");
        String result = savedInstanceState.getString("ResultText");
        tvInput.setText(input);
        tvResult.setText(result);
    }

    //logic handling when a button is pressed
    @Override
    public void onClick(View v) {
        String input = tvInput.getText().toString();
        if (input.equals("0") && !pressedOperator(v) && !pressedScientificKey(v))
            input = "";
        if (pressedEquals && v.getId() != R.id.btnEquals) {
            if (pressedOperator(v) || pressedScientificKey(v))
                input = tvResult.getText().toString();
            else
                input = "";
            pressedEquals = false;
        }
        switch (v.getId()) {
            case R.id.btnAC:
                pressedSciKey = false;
                tvInput.setText("0");
                tvResult.setText("0");
                break;
            case R.id.btnDiv:
                pressedSciKey = false;
                input = removeLastOperator(input);
                tvInput.setText(input + btnDiv.getText());
                break;
            case R.id.btnMult:
                pressedSciKey = false;
                input = removeLastOperator(input);
                tvInput.setText(input + btnMult.getText());
                break;
            case R.id.btnPlus:
                pressedSciKey = false;
                input = removeLastOperator(input);
                tvInput.setText(input + btnPlus.getText());
                break;
            case R.id.btnMinus:
                pressedSciKey = false;
                input = removeLastOperator(input);
                tvInput.setText(input + btnMinus.getText());
                break;
            case R.id.btnEquals:
                pressedEquals = true;
                pressedSciKey = false;
                if (input.length() > 0)
                {
                    input = removeLastOperator(input);
                    tvInput.setText(input);
                    calculate(input);
                }
                break;
            case R.id.btnDot:
                tvInput.setText(input + btnDot.getText());
                break;
            case R.id.btnZero:
                tvInput.setText(input + btnZero.getText());
                break;
            case R.id.btnOne:
                tvInput.setText(input + btnOne.getText());
                break;
            case R.id.btnTwo:
                tvInput.setText(input + btnTwo.getText());
                break;
            case R.id.btnThree:
                tvInput.setText(input + btnThree.getText());
                break;
            case R.id.btnFour:
                tvInput.setText(input + btnFour.getText());
                break;
            case R.id.btnFive:
                tvInput.setText(input + btnFive.getText());
                break;
            case R.id.btnSix:
                tvInput.setText(input + btnSix.getText());
                break;
            case R.id.btnSeven:
                tvInput.setText(input + btnSeven.getText());
                break;
            case R.id.btnEight:
                tvInput.setText(input + btnEight.getText());
                break;
            case R.id.btnNine:
                tvInput.setText(input + btnNine.getText());
                break;
            case R.id.btnSin:
                if(pressedSciKey)
                    break;
                else
                {
                    pressedSciKey = true;
                    tvInput.setText(formatInput(input, "sin"));
                    break;
                }
            case R.id.btnCos:
                if(pressedSciKey)
                    break;
                else
                {
                    pressedSciKey = true;
                    tvInput.setText(formatInput(input, "cos"));
                    break;
                }
            case R.id.btnTan:
                if(pressedSciKey)
                    break;
                else
                {
                    pressedSciKey = true;
                    tvInput.setText(formatInput(input, "tan"));
                    break;
                }
            case R.id.btnX2:
                if(pressedSciKey)
                    break;
                else
                {
                    pressedSciKey = true;
                    //if last char ends with a operator, add a 0... (sin2+ becomes sin2+0)
                    char endOfInput = input.charAt(input.length() - 1);
                    if (endOfInput == 'x' || endOfInput == '÷' || endOfInput == '+' || endOfInput == '-')
                    {
                        input = input + "0";
                    }
                    tvInput.setText(input + "²");
                    break;
                }
            case R.id.btnSqrt:
                if(pressedSciKey)
                    break;
                else
                {
                    pressedSciKey = true;
                    tvInput.setText(formatInput(input, "√"));
                    break;
                }
        }
    }

    private String removeLastOperator(String input)
    {
        //remove last char of input if its an operator
        char endOfInput = input.charAt(input.length() - 1);
        if (endOfInput == 'x' || endOfInput == '÷' || endOfInput == '+' || endOfInput == '-')
        {
            input = input.substring(0, input.length() - 1);
        }
        return input;
    }

    //special formatting to deal with sin, cos, tan, sqrt, since this symbol needs to come before the number
    private String formatInput(String input, String func)
    {
        //if last char ends with a operator, add a 0... (sin2+ becomes sin2+0)
        char endOfInput = input.charAt(input.length() - 1);
        if (endOfInput == 'x' || endOfInput == '÷' || endOfInput == '+' || endOfInput == '-')
        {
            input = input + "0";
        }

        int indexLastOp = 0;

        for(int i=0; i<input.length(); i++)
        {
            if (input.charAt(i) == 'x' || input.charAt(i) == '÷' || input.charAt(i) == '+' || input.charAt(i) == '-')
            {
                indexLastOp = i;
            }
        }

        if (indexLastOp == 0)
        {
            return func + input;
        }
        else
        {
            return input.substring(0, indexLastOp + 1) + func + input.substring(indexLastOp + 1);
        }
    }

    //function checks whether button pressed was an operator
    private boolean pressedOperator(View v)
    {
        if(v.getId() == R.id.btnDiv || v.getId() == R.id.btnMult || v.getId() == R.id.btnPlus || v.getId() == R.id.btnMinus)
            return true;
        else
            return false;
    }

    //function checks whether button pressed was a sci. key (sin, cos, tan, x², or sqrt)
    private boolean pressedScientificKey(View v)
    {
        if(v.getId() == R.id.btnSin || v.getId() == R.id.btnCos || v.getId() == R.id.btnTan
                || v.getId() == R.id.btnX2 || v.getId() == R.id.btnSqrt)
            return true;
        else
            return false;
    }

    //function that parses through a math expression stored in a string, calculates, and displays result
    private void calculate(String input)
    {
        //calculations got complex, so in case of errors, the try-catch keeps app from completely crashing
        try {
            List<Character> operators = getOperators(input);
            List<String> operands = getOperands(input);
            calcAdvOperands(operands);
            int numOfOperations = operators.size();
            float result;

            for (int i = 0; i < numOfOperations; i++) {
                if (operators.contains('x') || operators.contains('÷')) //process mult and div first
                {
                    int currMultPos = operators.indexOf('x');
                    int currDivPos = operators.indexOf('÷');
                    if ((currMultPos < currDivPos && currMultPos != -1) || currDivPos == -1) {
                        result = Float.parseFloat(operands.get(currMultPos)) * Float.parseFloat(operands.get(currMultPos + 1));
                        operators.remove(currMultPos); //remove operator
                        operands.remove(currMultPos);  //remove the 2 operands
                        operands.remove(currMultPos);
                        operands.add(currMultPos, String.valueOf(result)); //replace with result
                    } else {
                        result = Float.parseFloat(operands.get(currDivPos)) / Float.parseFloat(operands.get(currDivPos + 1));
                        operators.remove(currDivPos);
                        operands.remove(currDivPos);
                        operands.remove(currDivPos);
                        operands.add(currDivPos, String.valueOf(result));
                    }
                } else if (operators.contains('+') || operators.contains('-')) //add and subtract afterward
                {
                    int currPlusPos = operators.indexOf('+');
                    int currMinusPos = operators.indexOf('-');
                    if ((currPlusPos < currMinusPos && currPlusPos != -1) || currMinusPos == -1) {
                        result = Float.parseFloat(operands.get(currPlusPos)) + Float.parseFloat(operands.get(currPlusPos + 1));
                        operators.remove(currPlusPos); //remove operator
                        operands.remove(currPlusPos);  //remove the 2 operands
                        operands.remove(currPlusPos);
                        operands.add(currPlusPos, String.valueOf(result)); //replace with result
                    } else {
                        result = Float.parseFloat(operands.get(currMinusPos)) - Float.parseFloat(operands.get(currMinusPos + 1));
                        operators.remove(currMinusPos);
                        operands.remove(currMinusPos);
                        operands.remove(currMinusPos);
                        operands.add(currMinusPos, String.valueOf(result));
                    }
                }
            }

            //go through operands (should be only 1 left in the list at this point) and add to total string
            String total = "";
            for (int i = 0; i < operands.size(); i++) {
                total += operands.get(i);
            }

            //formatting, so result displays as 5 instead of 5.0, and at most 5 digits past decimal
            result = Float.parseFloat(total);
            DecimalFormat df = new DecimalFormat("#.#####");
            tvResult.setText(df.format(result));
        }
        catch (Exception e)
        {
            //error occurred
        }
    }

    //resolves advanced functions in operand list: sin, cos, tan, x², sqrt
    private List<String> calcAdvOperands(List<String> operands)
    {
        String curr = "";
        double result;
        DecimalFormat df = new DecimalFormat("#.#####");
        for(int i=0; i<operands.size(); i++)
        {
            curr = operands.get(i);
            if(curr.contains("sin"))
            {
                curr = getNumericPortion(curr);
                result = Math.sin(Double.parseDouble(curr));
                operands.set(i, df.format(result));
            }
            else if(curr.contains("cos"))
            {
                curr = getNumericPortion(curr);
                result = Math.cos(Double.parseDouble(curr));
                operands.set(i, df.format(result));
            }
            else if(curr.contains("tan"))
            {
                curr = getNumericPortion(curr);
                result = Math.tan(Double.parseDouble(curr));
                operands.set(i, df.format(result));
            }
            else if(curr.contains("²"))
            {
                curr = getNumericPortion(curr);
                result = Double.parseDouble(curr) * Double.parseDouble(curr);
                operands.set(i, df.format(result));
            }
            else if(curr.contains("√"))
            {
                curr = getNumericPortion(curr);
                result = Math.sqrt(Double.parseDouble(curr));
                operands.set(i, df.format(result));
            }
        }
        return operands;
    }

    //used to return only the numeric values within a string
    private String getNumericPortion(String str)
    {
        String output = "";
        for(int i=0; i<str.length(); i++)
        {
            if(Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')
            {
                output += str.charAt(i);
            }
        }
        return output;
    }

    //gets list of all operators (+,-,x,÷) in expression
    private List<Character> getOperators(String input)
    {
        List<Character> result = new ArrayList<>();
        for(int i=0; i<input.length(); i++)
        {
            if(input.charAt(i) == 'x' || input.charAt(i) == '÷' || input.charAt(i) == '+' || input.charAt(i) == '-')
            {
                if(i>0)  //if input's first char is an operator, ignore it
                    result.add(input.charAt(i));
            }
        }
        return result;
    }

    //gets list of the operands in expression
    private List<String> getOperands(String input)
    {
        String operand = "";
        List<String> result = new ArrayList<>();
        for(int i=0; i<input.length(); i++)
        {
            if (input.charAt(i) == 'x' || input.charAt(i) == '÷' || input.charAt(i) == '+' || input.charAt(i) == '-')
            {
                if (!operand.equals(""))
                {
                    result.add(operand);
                }
                operand = "";
            }
            else
            {
                operand += input.charAt(i);
            }
        }
        if (!operand.equals(""))
        {
            result.add(operand);
        }

        return result;
    }
}


