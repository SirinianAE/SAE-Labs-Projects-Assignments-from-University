/*
 * name: Sirinian Aram Emmanouil
 * AM: 2537
 */
import java.io.*; 
import java.net.*;
import java.util.ArrayList;

public class Gossiper{
	public static void main(String argv[]) throws Exception 
	 {
		ArrayList<String> ipArrayList = new ArrayList<String>();
		String commandQuery = "REPORT_AND_QUERY";	
		String groupMembership[] = new String[5];
		int port = 6000;

		while(true){
//////////////////////////////////////////////////////////////////////////////client
			  String modifiedSentence;
			  
			  if(argv.length < 5){
				System.out.print("argv.length < 5");
		                return;
			  }

			  for(int i=0; i<argv.length; i++){
				groupMembership[i] = argv[i];
				System.out.print(groupMembership[i] + "	");
			  }
			  System.out.print("\n");


			  for(int i=1; i<groupMembership.length; i++){
				int count = 0;
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

				Socket clientSocket = new Socket(groupMembership[i], 6000);
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
		
				  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

				  outToServer.writeBytes(commandQuery + "\r\n" + groupMembership[0] + "\r\n" + groupMembership[1] + "\r\n" + groupMembership[2] + "\r\n" + groupMembership[3] + "\r\n" + groupMembership[4] + "\r\n" + "\r\n"); 

				  do{
					modifiedSentence = inFromServer.readLine();
					if(modifiedSentence.equals("REPLY")){
						continue;
					}
					if(!modifiedSentence.equals("\r\n" + "\r\n")){
						break;
					}
					if(!ipArrayList.contains(modifiedSentence)){
						ipArrayList.add(modifiedSentence);
					}
			 		System.out.println("FROM SERVER: " + modifiedSentence);
					count ++;
				  }while(count < 70);

				  clientSocket.close();
	   		}
//////////////////////////////////////////////////////////////////////////////server

		      String clientSentence;
		      String sentence;
		      int flag = 0;

		      ServerSocket welcomeSocket = new ServerSocket(port);

		      while (true)
		      {
		      	 System.out.println("Server ready on "+port);

			 Socket connectionSocket = welcomeSocket.accept();

			 BufferedReader inFromClient = new BufferedReader( new InputStreamReader(connectionSocket.getInputStream()));

			 DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			 clientSentence = "Oups";
			 while(flag == 0){
				String myArray[];
			 	clientSentence = inFromClient.readLine();
				myArray = clientSentence.split("\r\n");
				if(clientSentence.equals("REPORT_AND_QUERY")){
					sentence = "REPLY";
					for(int i=0; i < ipArrayList.size(); i++){
						sentence = sentence + "\r\n" + ipArrayList.get(i);
					}
					sentence = sentence + "\r\n";
					outToClient.writeBytes(sentence);
					flag = 1;
				}
				if(clientSentence.equals("KICK")){
					return;
				}
			 }
		      }
		}

	 }
}
