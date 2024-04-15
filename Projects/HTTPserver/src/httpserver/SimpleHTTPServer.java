package httpserver;
//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
/**
 * Java program to create a simple HTTP Server to demonstrate how to use
 * ServerSocket and Socket class.
 * 
 * @author Javin Paul
 */

public class SimpleHTTPServer {

	public static void main(String[] args) throws Exception{
		final ServerSocket server = new ServerSocket(8888);
		System.out.println("Listening for connection on [ort 8888...");
		while(true) {
//			final Socket clientSocket = server.accept();
//			InputStreamReader isr = new InputStreamReader (clientSocket.getInputStream());
//			BufferedReader reader = new BufferedReader(isr);
//			String line = reader.readLine();
//			while(!line.isEmpty()) {
//				System.out.println(line);
//				line = reader.readLine();
			try (Socket socket = server.accept()) {
				Date today = new Date();
				String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
				socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
				
			}
			
			
		}
		

	}

}
