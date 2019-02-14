/*
 * name: Sirinian Aram Emmanouil
 * AM: 2537
 */
import java.io.*;
import java.net.*;

public class Server5
{

   public static void main(String argv[]) throws Exception
   {
      String clientSentence;
      String capitalizedSentence;
      int port = 6789;
      int flag = 0;

      ServerSocket welcomeSocket = new ServerSocket(port);

      while (true)
      {
      	System.out.println("Server ready on "+port);

         Socket connectionSocket = welcomeSocket.accept();

         BufferedReader inFromClient =
            new BufferedReader(
               new InputStreamReader(connectionSocket.getInputStream()));

         DataOutputStream outToClient =
            new DataOutputStream(connectionSocket.getOutputStream());

	 clientSentence = "Oups";
	 while(flag == 0){
		String myArray[];
	 	clientSentence = inFromClient.readLine();
		myArray = clientSentence.split("");
		for(int i = 0; i < myArray.length; i++){
			if(myArray[i].equals("$")){
				flag = 1;
			}
		}
	 }

         capitalizedSentence = clientSentence.toUpperCase() + '\n';

         outToClient.writeBytes(capitalizedSentence);
      }
   }
}
