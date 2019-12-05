package com.example.ex4;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {

    public static final String TAG = TcpClient.class.getSimpleName();
    public static String server_ip; //server IP address
    public static int server_port;
    private String mServerMessage;
    private boolean mRun = false;
    private PrintWriter mBufferOut;

    public Socket socket;

    /**
     * Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
    public TcpClient(String ip, int port) {
        server_ip = ip;
        server_port = port;
    }

    /**
     * Sends the message entered by client to the server
     *
     * @param message text entered by client
     */
    public void sendMessage(final String message) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mBufferOut != null) {
                    Log.d(TAG, "Sending: " + message);
                    mBufferOut.println(message);
                    mBufferOut.flush();
                }
            }
        };
        Thread thread = new Thread(runnable);

        thread.start();
    }

    /**
     * Close the connection and release the members
     */
    public void stopClient() {
        mRun = false;
        if (mBufferOut != null) {
            mBufferOut.flush();
            mBufferOut.close();//stop connection of socket
        }
        mBufferOut = null;
    }

    public void run() {

        mRun = true;

        try {
            //here you must put your computer's IP address. getBYname recive string of ip and return object of intentadress
            InetAddress serverAddr = InetAddress.getByName(server_ip);
            Log.d("TCP Client", "C: Connecting...");
            //create a socket to make the connection with the server
            socket = new Socket(serverAddr, server_port);
            mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            Log.d("TCP Client", "Connected");

        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
        }


    }



}