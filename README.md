# SALWaitlist
  
queue is a web application for students taking computer science classes at USC. Our app enables students to optimize their time by seeing the current wait time for a class and adding themselves to the waitlist.  
  
## Guest Functionality:
- Check the wait time of every CSCI course  

## Student Functionality:
- Update phone number and registered classes  
- Check the wait time for your registered classes  
- Add yourself to the waitlist for a certain class  
- Receive text notification when you are at the front of the waitlist  
- If a student adds themself to the waitlist when the waitlist is currently empty, they will not receive a text notification  
  
## CP Functionality:
- Check the students and their info (name, email address, phone number) currently in the waitlist  
- Check the Started checkbox when they start helping a student and check the Finished checkbox when finished  

## To run project:
1. Open schema/database.sql in MySQL Workbench and execute the query  
2. Make sure your DB connection is on localhost:3306 with username and password “root”  
3. Run SALNetwork.java as a Java Application  
4. Run login.jsp on Apache Tomcat server  

### Notes
Twilio phone number: +12016895329
