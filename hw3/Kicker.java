/*
 * name: Sirinian Aram Emmanouil
 * AM: 2537
 */

import java.io.*; 
import java.net.*; 
import java.util.*;

public class Kicker {
	public static void main(String argv[]) throws Exception{
		String clientSentence = "KICK\r\n";
		int port = 6000;

		while(true){
			Socket clientSocket = new Socket("localhost", port);
			Thread.sleep(3000);
			DataOutputStream outToServer = 
			 new DataOutputStream(clientSocket.getOutputStream());

			System.out.println("Sending KICK message");
			outToServer.writeBytes(clientSentence);
		}
	}
}
