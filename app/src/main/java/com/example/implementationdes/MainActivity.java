package com.example.implementationdes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {
    private EditText editTextData;
    private TextView textViewResult;
    private SecretKey secretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextData = findViewById(R.id.editTextData);
        textViewResult = findViewById(R.id.textViewResult);
        Button buttonEncrypt = findViewById(R.id.buttonEncrypt);
        Button buttonDecrypt = findViewById(R.id.buttonDecrypt);

        try {
            // Generate or retrieve the secret key
            secretKey = DESUtil.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }

        buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String data = editTextData.getText().toString();
                    String encryptedData = DESUtil.encrypt(data, secretKey);
                    textViewResult.setText(encryptedData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        buttonDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String encryptedData = textViewResult.getText().toString();
                    String decryptedData = DESUtil.decrypt(encryptedData, secretKey);
                    textViewResult.setText(decryptedData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
