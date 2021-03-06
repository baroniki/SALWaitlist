DROP DATABASE IF EXISTS finalDB;
CREATE DATABASE finalDB;
USE finalDB;
CREATE TABLE Students (
	Email							VARCHAR(50)		PRIMARY KEY, 
	Full_Name						VARCHAR(50), 
	Profile_Picture_URL				VARCHAR(300),
	Phone_Number					VARCHAR(25)
);

CREATE TABLE Classes (
	Class_ID						VARCHAR(25) PRIMARY KEY
);
        
CREATE TABLE Enrollment (
	Class_ID						VARCHAR(25),
    	Email							VARCHAR(50),
    	FOREIGN KEY (Class_ID) REFERENCES Classes (Class_ID),
	FOREIGN KEY (Email) REFERENCES Students(Email),
   	 PRIMARY KEY (Class_ID, Email)
);
        
CREATE TABLE Past_Wait_Time (
	Email							VARCHAR(50)		PRIMARY KEY, 
	Time_Spent						VARCHAR(25), 
	Class_ID						VARCHAR(25), 
	FOREIGN KEY (Email) REFERENCES Students(Email),
	FOREIGN KEY (Class_ID) REFERENCES Classes(Class_ID)
					);
					
CREATE TABLE Current_Users (
	Email							VARCHAR(50), 
	Class_ID						VARCHAR(25), 
	FOREIGN KEY(Email) REFERENCES Students(Email),
	FOREIGN KEY(Class_ID) REFERENCES Classes(Class_ID)
);
CREATE TABLE CP (
	Email							VARCHAR(50)		PRIMARY KEY, 
	Class_ID						VARCHAR(25), 
    	Name							VARCHAR(50),
	FOREIGN KEY (Class_ID) REFERENCES Classes(Class_ID)
);

INSERT INTO Classes VALUES
	('CSCI 103'), 
   	('CSCI 104'),
    	('CSCI 109'),
    	('CSCI 170'),
    	('CSCI 201'),
    	('CSCI 270'),
    	('CSCI 310'),
    	('CSCI 350'),
    	('CSCI 353'),
    	('CSCI 356'),
    	('CSCI 360');
