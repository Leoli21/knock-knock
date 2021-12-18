import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/***
 * 
 * @author Leo Li
 * Knock Knock Joke Server
 *
 */
public class KnockKnockServer 
{

	public static void main(String[] args) throws IOException
	{
		int port = 12345;
		ServerSocket server = new ServerSocket(port);
		
		while(true)
		{
			try
			{
				System.out.println("Server Listening...");
				Socket client = server.accept();
				System.out.println("Client Accepted.");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				
				String message;
				int jokePart = 0;
				
				while((message = in.readLine()) != null)
				{
					System.out.println("Client Message: " + message);
					//Knock Knock Response
					if((message.contains("Knock Knock")) && jokePart == 0)
					{
						jokePart++;
						out.println("Who's there?");
					}
					
					//Setup Response
					else if(!(message.contains("Knock Knock")) && jokePart == 1)
					{
						message = message + " Who?";
						jokePart++;
						out.println(message);
					}
					
					//Punchline Response
					else if(!(message.contains("Knock Knock")) && jokePart == 2) 
					{
						out.println("Haha! Tell me another one.");
						jokePart = 0;
					}
			
					else
					{
						out.println("I do not understand. Please tell me a 'Knock Knock' Joke.");
						jokePart = 0;
					}

					
				}
				client.close();
			}
			
			catch(Exception e)
			{
				System.out.println("Issue Occurred.");
			}
		}
	}

}
