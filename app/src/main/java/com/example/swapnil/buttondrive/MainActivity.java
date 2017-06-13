package com.example.swapnil.buttondrive;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.orbotix.ConvenienceRobot;
import com.orbotix.DualStackDiscoveryAgent;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Button Drive sample
 *
 * Connect either a Bluetooth Classic or Bluetooth LE robot to an Android Device, then
 * drive the robot by pressing buttons on the screen.
 * Headings are all based off of the back LED being considered the back of the robot
 *
 * 0 moves forward
 * 90 moves right
 * 180 moves backward
 * 270 moves left
 */


public class MainActivity extends Activity implements View.OnClickListener, RobotChangedStateListener {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 42;
    private static final float ROBOT_VELOCITY = 0.2f;

    private DualStackDiscoveryAgent mDiscoveryAgent;
    private ConvenienceRobot mRobot;

    TextView response;
    EditText editTextAddress, editTextPort;
    Button buttonRead, buttonClear;
    Button buttonConnect;
    Button blecon;
    Button blestop;

    public static final String TAG="MainActivity";
    public static final int TIMEOUT=100;
    Intent i=null;
    TextView tv=null;
    private String connectionStatus=null;
    private Handler mHandler=null;
    ServerSocket server=null;
    private Button mBtn0;
    private Button mBtn90;
    private Button mBtn180;
    private Button mBtn270;
    private Button mBtnStop;
    private String received = null;

    private Runnable showConnectionStatus = new Runnable() {
        public void run() {
            Toast.makeText(MainActivity.this, connectionStatus, Toast.LENGTH_LONG).show();
            response.setText(connectionStatus);
        }
    };

    public static final int MIN_PORT_NUMBER = 1100;

	    /**
 	     * The maximum server currentMinPort number.
      */
            public static final int MAX_PORT_NUMBER = 49151;

    public static boolean available(int port) {
        if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                /* should not be thrown */
                }
            }
        }

        return false;
    }


    private Runnable initializeConnection = new Thread() {
        public void run() {
            if(server!=null){
                Log.d(TAG, "Server object is not null");
                return;
            }

            Socket client=null;
// initialize server socket
            try{

                int port = 38300;
               if(available(port)) {
                   Log.d(TAG, "Connecting to Port " + port);

                   server = new ServerSocket();
                   server.setReuseAddress(true);
                   server.bind(new InetSocketAddress(port));
                   server.setSoTimeout(TIMEOUT * 1000);
               }
               else{
                   Log.d(TAG, "Port not available ");
                   return;
               }
               // }else{


                //}

//attempt to ccept a connection
                client = server.accept();
                Globals.socketIn=new Scanner(client.getInputStream());
                Globals.socketOut = new PrintWriter(client.getOutputStream(), true);


         //       Toast.makeText(MainActivity.this, "Check 1 "+client.toString() , Toast.LENGTH_LONG).show();


            } catch (SocketTimeoutException e) {
// print out TIMEOUT
                connectionStatus="Connection has timed out! Please try again";
                mHandler.post(showConnectionStatus);
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
// print out success
                connectionStatus="Connection was succesful!";
//                Toast.makeText(MainActivity.this, "Check 2" , Toast.LENGTH_LONG).show();

                mHandler.post(showConnectionStatus);

          //      startActivity(i);
            }
        }




    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Associate a listener for robot state changes with the DualStackDiscoveryAgent.
            DualStackDiscoveryAgent checks for both Bluetooth Classic and Bluetooth LE.
            DiscoveryAgentClassic checks only for Bluetooth Classic robots.
            DiscoveryAgentLE checks only for Bluetooth LE robots.
       */
        mDiscoveryAgent = new DualStackDiscoveryAgent();
        mDiscoveryAgent.addRobotStateListener(this);

        initViews();

        //editTextAddress = (EditText) findViewById(R.id.addressEditText);
       // editTextPort = (EditText) findViewById(R.id.portEditText);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
            if (hasLocationPermission != PackageManager.PERMISSION_GRANTED) {
                Log.e("Sphero", "Location permission has not already been granted");
                List<String> permissions = new ArrayList<String>();
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE_LOCATION_PERMISSION);
            } else {
                Log.d("Sphero", "Location permission already granted");
            }
        }



       blecon = (Button) findViewById(R.id.BlueCon);
        blecon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                }
                startDiscovery();
            }
        });

        blestop = (Button) findViewById(R.id.BlueStop);
        blestop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the DiscoveryAgent is in discovery mode, stop it.
                if (mDiscoveryAgent.isDiscovering()) {
                    mDiscoveryAgent.stopDiscovery();
                }

                //If a robot is connected to the device, disconnect it
                if (mRobot != null) {
                    mRobot.disconnect();
                    mRobot = null;
                }

            }
        });


        buttonConnect = (Button) findViewById(R.id.cb);
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(initializeConnection).start();
                String msg="Attempting to connect…";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });


        buttonRead = (Button) findViewById(R.id.connectButton);
        buttonClear = (Button) findViewById(R.id.clearButton);
        response = (TextView) findViewById(R.id.responseTextView);
        buttonRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                if (Globals.connected){
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {

                                while(Globals.socketIn.hasNext())
                                {

                                    received = Globals.socketIn.nextLine();
                                    setBText(response,received);
                                    if(received.equalsIgnoreCase("Bye")){
                                        break;
                                    }

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();
                }
                //Client myClient = new Client(response);
                //myClient.execute();
            }
        });
/*
        Client myClient = new Client(response);
        myClient.execute();
        //buttonRead.setOnClickListener(this);

        //i = new Intent(this, MainActivity.class);
        mHandler=new Handler();



        buttonClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                response.setText("");
            }
        });
*/


        i = new Intent(this, MainActivity.class);
        mHandler=new Handler();






    }
    private void setBText(final TextView text,final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
                if(value.equalsIgnoreCase("l")){
                    mBtn270.performClick();

                }
                else if(value.equalsIgnoreCase("r")){
                    mBtn90.performClick();
                }
                else if(value.equalsIgnoreCase("f")){
                    mBtn0.performClick();
                }
                else if(value.equalsIgnoreCase("b")){
                    mBtn180.performClick();
                }
                else {

                    mBtnStop.performClick();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_LOCATION_PERMISSION: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        startDiscovery();
                        Log.d("Permissions", "Permission Granted: " + permissions[i]);
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        Log.d("Permissions", "Permission Denied: " + permissions[i]);
                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    private void initViews() {
        mBtn0 = (Button) findViewById(R.id.btn_0);
        mBtn90 = (Button) findViewById(R.id.btn_90);
        mBtn180 = (Button) findViewById(R.id.btn_180);
        mBtn270 = (Button) findViewById(R.id.btn_270);
        mBtnStop = (Button) findViewById(R.id.btn_stop);

        mBtn0.setOnClickListener(this);
        mBtn90.setOnClickListener(this);
        mBtn180.setOnClickListener(this);
        mBtn270.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        startDiscovery();
    }

    private void startDiscovery() {
        //If the DiscoveryAgent is not already looking for robots, start discovery.
        if (!mDiscoveryAgent.isDiscovering()) {
            try {
                mDiscoveryAgent.startDiscovery(this);
            } catch (DiscoveryException e) {
                Log.e("Sphero", "DiscoveryException: " + e.getMessage());
            }
        }
    }

    @Override
    protected void onStop() {
        //If the DiscoveryAgent is in discovery mode, stop it.
        if (mDiscoveryAgent.isDiscovering()) {
            mDiscoveryAgent.stopDiscovery();
        }

        //If a robot is connected to the device, disconnect it
        if (mRobot != null) {
            mRobot.disconnect();
            mRobot = null;
        }

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDiscoveryAgent.addRobotStateListener(null);
    }




        @Override
    public void onClick(View v) {


      if (mRobot == null) {
            return;
      }

        /*
            When a heading button is pressed, set the robot to drive in that heading.
            All directions are based on the back LED being considered the back of the robot.
            0 moves in the opposite direction of the back LED.
         */
        switch(v.getId()) {
            case R.id.btn_0: {
                //Forward
                mRobot.drive(0.0f, ROBOT_VELOCITY);
                break;
            }
            case R.id.btn_90: {
                //To the right
                mRobot.drive(90.0f, ROBOT_VELOCITY);
                break;
            }
            case R.id.btn_180: {
                //Backward
                mRobot.drive(180.0f, ROBOT_VELOCITY);
                break;
            }
            case R.id.btn_270: {
                //To the left
                mRobot.drive(270.0f, ROBOT_VELOCITY);
                break;
            }
            case R.id.btn_stop: {
                //Stop the robot
                mRobot.stop();
                break;
            }


        }
    }


    @Override
    public void handleRobotChangedState(Robot robot, RobotChangedStateNotificationType type) {
        switch (type) {
            case Online: {
                //Save the robot as a ConvenienceRobot for additional utility methods
                mRobot = new ConvenienceRobot(robot);
                break;
            }
        }
    }
}