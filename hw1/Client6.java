/*
 * name: Sirinian Aram Emmanouil
 * AM: 2537
 */
import java.io.*; 
import java.net.*; 
public class Client6 { 

	 public static void main(String argv[]) throws Exception 
	 { 
		  String sentence; 
		  String modifiedSentence;
		  String myString;
   		  myString = "GET / HTTP/1.0\r\n\r\n";

		  BufferedReader inFromUser = 
			 new BufferedReader(new InputStreamReader(System.in)); 

		  Socket clientSocket = new Socket("195.130.121.21", 80); 

		  DataOutputStream outToServer = 
			 new DataOutputStream(clientSocket.getOutputStream()); 
        
		  BufferedReader inFromServer = 
			 new BufferedReader(new
			 InputStreamReader(clientSocket.getInputStream())); 

		  sentence = myString + "";

		  outToServer.writeBytes(sentence); 

		 //do{
		//	modifiedSentence = inFromServer.readLine();
		 	//System.out.println("FROM SERVER: " + modifiedSentence);
		 //}while(modifiedSentence != null);

		 do{
		 	modifiedSentence = inFromServer.readLine();
		 	System.out.println("FROM SERVER: " + modifiedSentence);
			if(modifiedSentence.equals("")){
				break;
			}
		 }while(modifiedSentence != null);


		  clientSocket.close(); 
                   
	 } 
} 

