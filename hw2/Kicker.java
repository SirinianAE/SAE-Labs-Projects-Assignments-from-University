/*
 * name: Sirinian Aram Emmanouil
 * AM: 2537
 */
import java.io.*; 
import java.net.*; 
public class Kicker { 

	 public static void main(String argv[]) throws Exception 
	 { 

		while(true){
			  String modifiedSentence;

			  sleep(10000); 

			  BufferedReader inFromUser = 
				 new BufferedReader(new InputStreamReader(System.in)); 

			  Socket clientSocket = new Socket("localhost", 6000); 

			  DataOutputStream outToServer = 
				 new DataOutputStream(clientSocket.getOutputStream()); 
		
			  BufferedReader inFromServer = 
				 new BufferedReader(new
				 InputStreamReader(clientSocket.getInputStream())); 

			  outToServer.writeBytes("KICK" + "\r\n" + "\r\n"); 

			  fromServerSentence = inFromServer.readLine(); 

			  System.out.println("FROM GOSSIPER " + fromServerSentence); 

			  clientSocket.close(); 
		}
	 } 
} 

