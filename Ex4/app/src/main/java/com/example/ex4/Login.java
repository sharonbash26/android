package com.example.ex4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText ip;
    private EditText port;
    private Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ip = (EditText) findViewById(R.id.ipText);// he give to me all the things of object
        port = (EditText) findViewById(R.id.portText);
        connectButton = (Button) findViewById(R.id.connectButton);

        //what we do when we enter on connect
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Main.class);
                intent.putExtra("ip", ip.getText().toString());// give to parmter to secind activity from the first
                intent.putExtra("port", port.getText().toString());

                startActivity(intent);//start second iintent
            }
        });
    }
}
