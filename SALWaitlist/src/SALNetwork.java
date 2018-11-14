//FUCK

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class SALNetwork {
	/*
	 public static Map<String, UserThread> userThreads = new HashMap<String, UserThread>;
	 
	  in try:
	  Queue classUsers = userThreads.get(userClass);
	  if (classUsers != null) {
	  		userThreads.get(userClass).add(ut);
	  }
	  else {
	  		userThreads.put(userClass, new LinkedList());
	  		userThreads.get(userClass).add(ut);
	  }
	 */
	public static Vector<UserThread> userThreads;
	public SALNetwork(int port) {
		ServerSocket ss = null;
		userThreads = new Vector<UserThread>();
		try {
			System.out.println("Trying to bind to port " + port);
			ss = new ServerSocket(port);
			System.out.println("Bound to port " + port);
			while (true) {
				Socket s = ss.accept();
				System.out.println("Connection from " + s.getInetAddress());
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				//ois = new ObjectInputStream(s.getInputStream());
				String type = (String)ois.readObject();
				if (type.equals("CP")) {
					System.out.println("Trying to perform CP operation");
					String remEmail = (String)ois.readObject();
					removeUser(remEmail);
					System.out.println("Performed CP operation");
					
				}
				else {
					System.out.println("Trying to add user");
					String userEmail = (String)ois.readObject();
					String userClass = (String)ois.readObject();
					UserThread ut = new UserThread(s, this, userEmail, userClass);
					userThreads.add(ut);
					System.out.println("Added user " + userEmail);
				}
			}
			//System.out.println("Userthreads size: " + userThreads.size());
		}
		catch(IOException ioe) {
			
		}
		catch(ClassNotFoundException e) {
			
		}
		finally {
			try {
				if (ss!= null) {
					ss.close();
				}
			}
			catch (IOException ioe) {
				
			}
		}
	}
	public static void main(String[] args) {
		new SALNetwork(6789);
	}
	public void pri(String s) {
		System.out.println(s);
	}
	
	public void removeUser(String email) {
		String classID = null;
		int newIndex = -1;
		for (int i = 0; i < userThreads.size(); i++) {
			if (userThreads.get(i).email.equals(email)) {
				classID = userThreads.get(i).classID;
				if (userThreads.get(i).done != true) {
					userThreads.get(i).closeOff();
				}
				userThreads.remove(i);
				break;
			}
		}
		for (int i = 0; i < userThreads.size(); i++) {
			if (userThreads.get(i).classID.equals(classID)) {
				userThreads.get(i).sendNotification();
			}
		}

		System.out.println("Removed "+email+". Now size is " + userThreads.size());
	}
}
