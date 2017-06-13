/* export CLASSPATH="/home/swapnil/Downloads/Arduino-SerialRead-Java-master/src/arduinoserialread/":"/home/swapnil/Downloads/Arduino-SerialRead-Java-master/src/arduinoserialread/ardulink.jar":"/home/swapnil/Downloads/Arduino-SerialRead-Java-master/src/arduinoserialread/jfreechart-1.0.17.jar":"/home/swapnil/Downloads/Arduino-SerialRead-Java-master/src/arduinoserialread/jcommon-1.0.21.jar":"./":"/home/swapnil/Downloads/rxtx-2.1-7-bins-r2/RXTXcomm.jar":"/home/swapnil/Robotics/Server/comm.jar":"/home/swapnil/Robotics/Server/commtest.jar":"/home/swapnil/Robotics/Server/BlackBox.jar":"/home/swapnil/Robotics/Server/ParallelBlackBox.jar":"/home/swapnil/Robotics/Server/javax.comm.properties"
*/

import java.io.*;
import java.net.*;
import java.util.*;


/**
* Initialize connection to the phone
*
**/
public class AdbClient implements LogFileTailerListener{

public static Socket socket;
public static Scanner sc;
public static PrintWriter out;
private LogFileTailer ob ;

public AdbClient(){



}

public AdbClient(String fileName ){

//ob = new LogFileTailer( new File( fileName ), 10, false );
//ob.addLogFileTailerListener( this );
//ob.start();

}

public static void main(String[] args){




initializeConnection();


AdbClient ob = new AdbClient("/home/swapnil/Robotics/Server/CoolTerm.txt");



}

 public void newLogFileLine(String line)
  {
    String[] lArr = line.split(" ");
    String temp = lArr[0];
    if(!temp.equalsIgnoreCase("r")){
       temp = lArr[1];
     }

    System.out.println(temp);
  }


public static void initializeConnection(){
//Create socket connection
try{
//localhost
//
socket = new Socket("localhost", 34355);
System.out.println("Got Socket ..... ");

out = new PrintWriter(socket.getOutputStream(), true);
//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
sc=new Scanner(socket.getInputStream());
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String inputLine = null;

while( !((inputLine = br.readLine()).equalsIgnoreCase("Bye"))){
  out.println(inputLine);

}


// add a shutdown hook to close the socket if system crashes or exists unexpectedly
Thread closeSocketOnShutdown = new Thread() {
public void run() {
try {
socket.close();
} catch (IOException e) {
e.printStackTrace();
}
}
};
Runtime.getRuntime().addShutdownHook(closeSocketOnShutdown);
} catch (UnknownHostException e) {
System.out.println("Socket connection problem (Unknown host)"+e.getStackTrace());
} catch (IOException e) {
 System.out.println("Could not initialize I/O on socket "+e.getStackTrace());
}
}

}
