import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserThread extends Thread {
	public Socket s;
	public String email;
	public String classID;
	public SALNetwork sn;
	public ObjectOutputStream oos;
	boolean done = false;
	public UserThread(Socket s, SALNetwork sn, String email, String classID) {
		this.s = s;
		this.email = email;
		this.classID = classID;
		this.sn = sn;
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		
		while (!done) {
			try {
				oos.writeObject("no");
				oos.flush();
				Thread.sleep(1000);
			}
			catch (IOException e) {
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	synchronized public void sendNotification() {
		try {
			done = true;
			oos.writeObject("Ready");
			oos.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	synchronized public void closeOff() {
		try {
			done = true;
			oos.writeObject("Done");
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
