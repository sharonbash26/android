package com.example.ex4;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Main extends AppCompatActivity implements JoystickView.JoystickListener {

    TcpClient tcpClient; //
    private String ip;
    private String port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ip = intent.getStringExtra("ip");// take ip
        port = intent.getStringExtra("port");//take port
        new ConnectTask().execute("");
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onJoystickMoved(float xPercent, float yPercent) {
        if (tcpClient != null) {
            tcpClient.sendMessage(xPercent + ", " + yPercent);
        }
        Log.d("Joystick", "X percent: " + xPercent + " Y percent: " + yPercent);
    }

    public void onDestroy() {
        if (tcpClient != null) {
            tcpClient.stopClient();
        }
        super.onDestroy();
    }

    public class ConnectTask extends AsyncTask<String, String, TcpClient> {
        @Override
        protected TcpClient doInBackground(String... message) {
            //we create a TCPClient object
            tcpClient = new TcpClient(ip, Integer.valueOf(port));
            tcpClient.run();

            return null;
        }

    }
}
