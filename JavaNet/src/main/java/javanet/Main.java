package javanet;

import java.util.*; 
import java.io.*;
import java.net.*;


class Main {
	public static void main (String args[]) throws IOException {
		String IP = args[0];
		Socket server = new Socket(IP, 4004);

		Thread listener = new Thread(new ServerListener (server));

		PrintWriter out = new PrintWriter(server.getOutputStream());
		Scanner scan = new Scanner(System.in);
		String message = "";

		listener.start();
		do {
			if (scan.hasNextLine()) {

				message = scan.nextLine();
				out.println(message);
				out.flush();
			}
			
		} while (!message.equals("end"));

		listener.interrupt();
		server.close();
	}

}
