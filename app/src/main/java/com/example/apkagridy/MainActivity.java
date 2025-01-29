package com.example.apkagridy;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;


public class MainActivity extends AppCompatActivity {
    private int numberString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputAge = findViewById(R.id.inputAge);
        EditText inputNumber = findViewById(R.id.inputNumber);
        EditText inputPassword = findViewById(R.id.inputPassword);
        EditText inputName = findViewById(R.id.inputName);

        Button btn = findViewById(R.id.button);

        TextView errorHandler = findViewById(R.id.errorText);
        TextView errorHandlerw= findViewById(R.id.errorTextw);


        inputNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                errorHandler.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String string = charSequence.toString();
                if (!string.isEmpty()) {
                    try {
                        numberString = Integer.parseInt(string);
                    } catch (NumberFormatException e) {
                        numberString = -1;
                    }
                } else {
                    numberString = -1;
                }
            }


            @Override
            public void afterTextChanged(Editable s) {
                if (numberString < 1 || numberString > 35) {
                    errorHandler.setText(getString(R.string.error_invalid_number));
                } else {
                    errorHandler.setText("");
                }
            }
        });

        inputAge.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                errorHandlerw.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String string = charSequence.toString();
                if (!string.isEmpty()) {
                    try {
                        numberString = Integer.parseInt(string);
                    } catch (NumberFormatException e) {
                        numberString = -1;
                    }
                } else {
                    numberString = -1;
                }
            }


            @Override
            public void afterTextChanged(Editable s) {
                if (numberString < 1940 || numberString > 2025) {
                    errorHandlerw.setText(getString(R.string.error_invalid_age));
                } else {
                    errorHandlerw.setText("");

                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = inputPassword.getText().toString();
                if(pass.length()==0){
                    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
                    Random random = new Random();
                    StringBuilder randomString = new StringBuilder();
                    for (int i = 0; i < 10; i++) {
                        int index = random.nextInt(characters.length());

                        randomString.append(characters.charAt(index));
                    }
                    pass = randomString.toString();
                }
                Integer age = 2025 - Integer.parseInt(inputAge.getText().toString());


                String name = inputName.getText().toString();
                String namePop ="";
                if (name.length() > 0) {
                    namePop = new StringBuilder(name).reverse().toString();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Wyniki")
                        .setMessage("Twój wiek: "+ age+
                        "\nTwoje hasło: "+ pass+
                                "\nOdwrócone imię: "+ namePop)
                        .setNegativeButton("Zamknij", (dialog, which) -> dialog.cancel());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

}