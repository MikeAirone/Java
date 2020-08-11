package javanet;

import java.util.*; 
import java.io.*;
import java.net.*;


class ServerListener implements Runnable {
	Socket server;


	public ServerListener (Socket server) {
		this.server = server;
	}


	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			while (true) {
				String str = in.readLine();
				System.out.println(str);
			}
		} catch (IOException e) {
			System.out.println("I die.");
		}
	}
}