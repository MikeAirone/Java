package javanet;

import java.util.*; 
import java.io.*;
import java.net.*;


class Server {
	public static void main (String args[]) throws IOException {
		ServerSocket server = new ServerSocket(4004);	
		ArrayList<Socket> clients = new ArrayList<>();
		
		String message = "";
		Socket client = null;
		server.setSoTimeout(1);

		do {

			try {
				client = server.accept();
			} catch (SocketTimeoutException e) {}
			
			if (client!= null && !clients.contains(client)) {
				client.setSoTimeout(1);
				clients.add(client);
			}

			for (int i = 0; i<clients.size();i++) {
				Scanner in = new Scanner(clients.get(i).getInputStream());
				if (in.hasNextLine()) {

					message = in.nextLine();
					String str = "[" + clients.get(i).getInetAddress() + ":" + clients.get(i).getPort() + "] >> " + message;
					System.out.println(str); 

					for (int j = 0; j<clients.size();j++) {
						if (i != j) {
							PrintWriter out = new PrintWriter(clients.get(j).getOutputStream());
							out.println(str);
							out.flush();
						}
					}
				}	
			}

		} while  (true);

		//client.close();
		//server.close(); 
	}
}