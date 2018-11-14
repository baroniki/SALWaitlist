
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Notification {
	
	public static final String ACCOUNT_SID = "ACb54c82cc4c6b728c689407ed158c64f8";
	public static final String AUTH_TOKEN = "af52a9b03a3a8bf3d7bea02ef2104b56";
	
	public Notification(String phoneNumber) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message5 = Message.creator(new PhoneNumber(phoneNumber),
	            new PhoneNumber("+12016895329"), 
	            "It is your turn!").create();
		System.out.println(message5.getSid());
	}
	/*
	public static void main(String[] args) {
		//new Notification("+18056464945");
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message5 = Message.creator(new PhoneNumber("+18056464945"),
	            new PhoneNumber("+12016895329"), 
	            "It is your turn!").create();
		System.out.println(message5.getSid());
		
	}
	*/
}
