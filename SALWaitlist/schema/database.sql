DROP DATABASE IF EXISTS finalDB;
CREATE DATABASE finalDB;
USE finalDB;
CREATE TABLE Students(Email VARCHAR(50) PRIMARY KEY, 
			  Full_Name VARCHAR(50), 
			  Profile_Picture_URL VARCHAR(300),
		      	  Phone_Number VARCHAR(25)
					);
					
CREATE TABLE Classes(Class_ID VARCHAR(25) PRIMARY KEY, Email VARCHAR(25), 
					 FOREIGN KEY(Email) REFERENCES Students(Email)
					);
					
CREATE TABLE Past_Wait_Time(Email VARCHAR(25) PRIMARY KEY, 
							Time_Spent VARCHAR(25), 
							Class_ID VARCHAR(25), 
							FOREIGN KEY(Email) REFERENCES Students(Email),
							FOREIGN KEY(Class_ID) REFERENCES Classes(Class_ID)
					);
					
CREATE TABLE Current_Users(Email VARCHAR(25), 
			   Class_ID VARCHAR(25), 
			   Time_Started VARCHAR(25),
			   FOREIGN KEY(Email) REFERENCES Students(Email),
			   FOREIGN KEY(Class_ID) REFERENCES Classes(Class_ID)
						);
CREATE TABLE CP(Email VARCHAR(25) PRIMARY KEY, 
				Class_ID VARCHAR(25), 
				FOREIGN KEY(Class_ID) REFERENCES Classes(Class_ID)
				);
