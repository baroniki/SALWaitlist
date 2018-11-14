import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserClient extends Thread {
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String email;
	private String classID;
	public boolean CP;
	String pos;
	Socket s;
	public UserClient(String hostname, int port, String email, String classID, CP cp)  {
		try {
			pos = "not";
			System.out.println("Connecting to " + hostname + ":" + port);
			s = new Socket(hostname, port);
			System.out.println("Connected");
			
			oos = new ObjectOutputStream(s.getOutputStream());
			
			this.email = email;
			this.classID = classID;
			if (!(cp.isCP)) {
				System.out.println("New student client " + email);
				oos.writeObject("Student");
				oos.flush();
				oos.writeObject(email);
				oos.flush();
				oos.writeObject(classID);
				oos.flush();
				this.start();
			}
			else {
				System.out.println("New cp access");
				oos.writeObject("CP");
				oos.flush();
				oos.writeObject(cp.emailToRemove);
				oos.flush();
				System.out.println("Done with cp access");
			}
			
		}
		catch (IOException ioe) {
			
		}
	}
	public void run() {
		try {
			ois = new ObjectInputStream(s.getInputStream());
			while (!pos.equals("Ready") && !pos.equals("Done")) {
				pos = (String) ois.readObject();
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public synchronized String getPos() {
		return pos;
	}
}
