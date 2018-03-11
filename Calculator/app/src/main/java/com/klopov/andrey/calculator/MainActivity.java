package com.klopov.andrey.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView fieldResult; // поле для результата
    EditText fieldNumber; // поле для ввода
    TextView fieldOperation; // поле для знака
    Double operand = null; // данные, которые обрабатываются командой
    String lastOperation = "="; // последняя операция
    private android.os.Bundle savedInstanceState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fieldResult = (TextView) findViewById(R.id.fieldResult);
        fieldNumber = (EditText) findViewById(R.id.fieldNumber);
        fieldOperation = (TextView) findViewById(R.id.fieldOperation);
    }

    // сохранение состояния
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }
    // получаем ранее сохраненные данные
    @Override
    protected void onRestoreInstanceState (Bundle outState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        fieldResult.setText(operand.toString());
        fieldOperation.setText(lastOperation);
    }

    // нажатие на числовую кнопку
    public void clickOnNumber(View view) {
        Button button = (Button)view;
        fieldNumber.append(button.getText());

        if (lastOperation.equals("=") && operand!=null){
            operand = null;
        }
    }

    // нажатие на кнопку операции
    public void clickOnOperation(View view) {
       Button button = (Button)view;
       String op = button.getText().toString();
       String number = fieldNumber.getText().toString();
       // если введено что-нибудь
        if(number.length()>0){
            number = number.replace(',', '.');
            try{
                performOperation(Double.valueOf(number), op);
            }catch (NumberFormatException ex){
                fieldNumber.setText("");
            }
        }
        lastOperation = op;
        fieldOperation.setText(lastOperation);
    }

    private void performOperation(Double number, String operation) {
        // если данные еще не обрабатывались командой
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "*":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
            }
        }
        fieldResult.setText(operand.toString().replace(',','.'));
        fieldNumber.setText("");
    }
}
