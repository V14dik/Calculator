package com.example.calculator;

import java.io.Serializable;
import java.math.BigDecimal;

public class Calculator implements Serializable {
    private Double firstArg;
    private Double secondArg;

    private StringBuilder input = new StringBuilder();
    private int action;

    private State state;

    private enum State {
        firstArgInput,
        secondArgInput,
        chooseAction,
        showResult
    }
    public Calculator(){
        state = State.firstArgInput;
    }

    public void onNumberPressed(int buttonId){
        if(input.toString().equals("0")){
            input.setLength(0);
        }
        if (state == State.showResult){
            state = State.firstArgInput;
            input.setLength(0);
        }
        if (state == State.chooseAction){
            if (input.toString().equals("-")){
                state = State.secondArgInput;

            }
            else {
                state = State.secondArgInput;
                input.setLength(0);
            }
        }
        if (input.length() < 9){
            switch (buttonId) {
                case R.id.zero:
                    if (input.length() != 0) {
                        try {
                            if (Integer.parseInt(input.toString()) > 0) {
                                input.append("0");
                            }
                        }
                        catch (NumberFormatException e){
                            if (input.toString().contains(".")){
                                input.append("0");
                            }
                        }
                    }
                    else {
                        input.append("0");
                    }
                    break;
                case R.id.one:
                    input.append("1");
                    break;
                case R.id.two:
                    input.append("2");
                    break;
                case R.id.three:
                    input.append("3");
                    break;
                case R.id.four:
                    input.append("4");
                    break;
                case R.id.five:
                    input.append("5");
                    break;
                case R.id.six:
                    input.append("6");
                    break;
                case R.id.seven:
                    input.append("7");
                    break;
                case R.id.eight:
                    input.append("8");
                    break;
                case R.id.nine:
                    input.append("9");
                    break;
            }
        }
    }


    private void calculate(){
        switch (action) {
            case R.id.minus:
                if ((firstArg - secondArg)%1 == 0){
                    input.append((int)(firstArg - secondArg));
                }
                else {
                    input.append(firstArg - secondArg);
                }
                firstArg = firstArg - secondArg;
                break;
            case R.id.plus:
                if ((firstArg + secondArg)%1 == 0){
                    input.append((int)(firstArg + secondArg));
                }
                else {
                    input.append(firstArg + secondArg);
                }
                firstArg = firstArg + secondArg;
                break;
            case R.id.division:
                if ((firstArg / secondArg)%1 == 0){
                    input.append((int)(firstArg / secondArg));
                }
                else {
                    input.append(firstArg / secondArg);
                }
                firstArg = firstArg / secondArg;
                break;
            case R.id.modulo:
                if ((firstArg % secondArg)%1 == 0){
                    input.append((int)(firstArg % secondArg));
                }
                else {
                    input.append(firstArg % secondArg);
                }
                firstArg = firstArg % secondArg;
                break;
            case R.id.multiply:
                if ((firstArg * secondArg)%1 == 0){
                    input.append((int)(firstArg * secondArg));
                }
                else {
                    input.append(firstArg * secondArg);
                }
                firstArg = firstArg * secondArg;
                break;
        }
    }


    private void checkAction(int actionId){
        switch (actionId) {
            case R.id.minus:
                if(state == State.chooseAction){
                    input.setLength(0);
                    //input.append("-");
                }
                else{
                    action = R.id.minus;
                    state = State.chooseAction;
                }
                break;
            case R.id.plus:
                action = R.id.plus;
                state = State.chooseAction;
                break;
            case R.id.division:
                action = R.id.division;
                state = State.chooseAction;
                break;
            case R.id.modulo:
                action = R.id.modulo;
                state = State.chooseAction;
                break;
            case R.id.multiply:
                action = R.id.multiply;
                state = State.chooseAction;
                break;
            case R.id.inversely:
                action = R.id.inversely;
                state = State.chooseAction;
                break;
        }
    }


    public void onActionPressed(int actionId){
        if(actionId != R.id.equals && state == State.showResult){
            firstArg = Double.parseDouble(input.toString());
            checkAction(actionId);
            return;
        }
        if(state == State.secondArgInput && actionId != R.id.equals){
            secondArg = Double.parseDouble(input.toString());
            input.setLength(0);
            calculate();
            state = State.chooseAction;
            return;
        }
        if(actionId == R.id.equals && state == State.showResult){
            input.setLength(0);
            calculate();
            return;
        }
        if(actionId != R.id.equals && state == State.chooseAction){
            checkAction(actionId);
            return;
        }
        if(actionId == R.id.equals && state == State.secondArgInput){
            secondArg = Double.parseDouble(input.toString());
            state = State.showResult;
            input.setLength(0);
            calculate();
        } else if(input.length() > 0 && state == State.firstArgInput){
            try {
                firstArg = Double.parseDouble(input.toString());
                checkAction(actionId);
            } catch (NumberFormatException e) {
            }
        }
        if(actionId == R.id.minus && input.length() == 0){
            if(state == State.showResult){
                input.append("-");
            }

        }
    }

    public void onSpecificActionPressed (int actionId){
        switch (actionId) {
            case R.id.ac:
                input.setLength(0);
                firstArg = 0.0;
                secondArg = 0.0;
                //action = R.id.plus;
                state = State.firstArgInput;
                break;
            case R.id.inversely:
                if(input.toString().contains("-")){
                    input.deleteCharAt(0);
                }
                else {
                    input.insert(0,"-");
                }
                break;
            case R.id.dot:
                if(input.toString().contains(".")){
                    break;
                }
                if(input.length() != 0){
                    input.append(".");
                }
                break;
        }
    }
    public String getText(){
        return input.toString();
    }
}
