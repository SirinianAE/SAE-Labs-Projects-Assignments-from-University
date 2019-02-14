/*
 * name: Sirinian Aram Emmanouil
 * AM: 2537
 */

import java.io.*; 
import java.net.*; 
import java.util.*;
public class P2PGossiper { 

	public static void main(String argv[]) throws Exception { 
		ArrayList<String> groupMembership = new ArrayList<String>(); 
		groupMembership.add("10.7.4.47");
		groupMembership.add("10.7.4.48"); 
		groupMembership.add("10.7.4.19");
		groupMembership.add("10.7.4.54");  
		groupMembership.add("10.7.4.46");
		String clientSentence;
		String serverSentence;
		int port = 6000;
		ServerSocket welcomeSocket = new ServerSocket(port);
		while(true){
				for(int i=1;i<5; i++){
					try{
						Socket clientSocket = new Socket(groupMembership.get(i), port);	
				
						DataOutputStream outToServer = 
						 new DataOutputStream(clientSocket.getOutputStream()); 
					
						BufferedReader inFromServer = 
						 new BufferedReader(new
						 InputStreamReader(clientSocket.getInputStream())); 

						outToServer.writeBytes("QUERY\r\n" ); 

						serverSentence = inFromServer.readLine();
						System.out.println(serverSentence);
						if(serverSentence.contains("REPLY")){
							while(true){
								serverSentence = inFromServer.readLine();
								System.out.println("FROM Server: " +  groupMembership.get(i));
								if(serverSentence.equals("")){
									clientSocket.close();
									break;
								}
								if(!groupMembership.contains(serverSentence)){
									groupMembership.add(serverSentence);
								}
							}
							System.out.println("");
							for(int j=0; i<groupMembership.size(); j++){
								System.out.println(groupMembership.get(j));
							}
							System.out.println("");
						}else if(serverSentence.contains("KICK")){
							System.out.println("ERROR expected REPLY get KICK");
							clientSocket.close();
						}else{
							System.out.println("ERROR expected REPLY");
							clientSocket.close();
						}
					}catch(Exception e){
						System.out.println("can't find the server");
					}
				}
			

			while (true){
				System.out.println("Server ready on "+port);
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient =
					new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient =
					new DataOutputStream(connectionSocket.getOutputStream());
					
				clientSentence = inFromClient.readLine();
				if(clientSentence.contains("QUERY")){
					System.out.println("FROM Client: " +  clientSentence);
					String stringToClient = "REPLY\r\n";
					for(int i=0;i<groupMembership.size(); i++){
						stringToClient += groupMembership.get(i) + "\r\n";
					}
					stringToClient += "\r\n";
					outToClient.writeBytes(stringToClient);
					connectionSocket.close();
				}else if(clientSentence.contains("KICK")){
					System.out.println("FROM Client: " +  clientSentence);
					connectionSocket.close();
					break;
				}else{
					System.out.println("ERROR expected QUERY or KICK");
					connectionSocket.close();
				}
			}
		}
	}
}
