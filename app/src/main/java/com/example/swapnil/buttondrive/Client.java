package com.example.swapnil.buttondrive;

/**
 * Created by swapnil on 6/3/17.
 */

/**
 * Created by swapnil on 6/3/17.
 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Client extends AsyncTask<Void, Void, Void> {


    private String connectionStatus=null;
    private Handler mHandler=null;
    ServerSocket server=null;
    Socket client = null;
    BufferedReader br = null;
    public static final String TAG="Client";
    public static final int TIMEOUT=10;

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;

    Client(TextView textResponse) {
        //dstAddress = addr;
        //dstPort = port;
        this.textResponse=textResponse;
    }

    private Runnable showConnectionStatus = new Runnable() {
        public void run() {
            //Toast.makeText(Client.this, connectionStatus, Toast.LENGTH_LONG).show();
            textResponse.setText(connectionStatus);
        }
    };

    private Runnable showText = new Runnable() {
        @Override
        public void run() {
            while(Globals.socketIn.hasNext()) {
                //received = Globals.socketIn.nextLine();
                // response.setText(received);

            }
        }
    };

    @Override
    protected Void doInBackground(Void... arg0) {


        try{
            if(server == null){
              server = new ServerSocket(38301);

            }
            server.setSoTimeout(TIMEOUT*1000);

//attempt to ccept a connection
            if(client == null){
                client = server.accept();
            }
            if(br == null)
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            if(Globals.socketOut == null)
            Globals.socketOut = new PrintWriter(client.getOutputStream(), true);

            while( !(response = br.readLine()).equalsIgnoreCase("Bye")){

            };


        } catch (SocketTimeoutException e) {
// print out TIMEOUT
            connectionStatus="Connection has timed out! Please try again";
           // mHandler.post(showConnectionStatus);
        } catch (IOException e) {
            Log.e(TAG, ""+e);
        } finally {
//close the server socket
            try {
                if (server!=null)
                    server.close();
            } catch (IOException ec) {
                Log.e(TAG, "Cannot close server socket"+ec);
            }
        }

        if (client!=null) {
            Globals.connected=true;

            connectionStatus="Connection was succesful!";

            //mHandler.post(showConnectionStatus);

        }

        return null;


        /*
        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();
*/
			/*
			 * notice: inputStream.read() will block if no data return
			 */
  /*          while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;*/


    }

    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }

}