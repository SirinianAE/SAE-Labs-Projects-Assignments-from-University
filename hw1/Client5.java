/*
 * name: Sirinian Aram Emmanouil
 * AM: 2537
 */
import java.io.*; 
import java.net.*; 
public class Client5 { 

	 public static void main(String argv[]) throws Exception 
	 { 
		  String sentence; 
		  String modifiedSentence;
		  String myString;
   		  myString = "1111\n2222\n3333$"; 

		  BufferedReader inFromUser = 
			 new BufferedReader(new InputStreamReader(System.in)); 

		  Socket clientSocket = new Socket("10.7.4.86", 6789); 

		  DataOutputStream outToServer = 
			 new DataOutputStream(clientSocket.getOutputStream()); 
        
		  BufferedReader inFromServer = 
			 new BufferedReader(new
			 InputStreamReader(clientSocket.getInputStream())); 

		  sentence = myString + "";

		  outToServer.writeBytes(sentence + '\n'); 

		 do{
		 	modifiedSentence = inFromServer.readLine();
		 	System.out.println("FROM SERVER: " + modifiedSentence);
		 }while(modifiedSentence != null);


		  clientSocket.close(); 
                   
	 } 
} 

