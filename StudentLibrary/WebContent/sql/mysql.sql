--Create database test
use test;

-- Create table Students
CREATE TABLE `Students` (
  `studentid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL
);
--Create table Books
CREATE TABLE `Books` (
  `bookid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `isbn number` varchar(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(50), 
  `publisher` varchar(50), 
  `price` double default 0.0
);
--Create table Library
CREATE TABLE `Library` (
  `studentid` int not null,
  `bookid` int not null,
  `issued by` varchar(25),
  `issued date` datetime,
  
  CONSTRAINT PK_Library PRIMARY KEY (studentid, bookid), 
  CONSTRAINT FK_Library_Students FOREIGN KEY (studentid) REFERENCES Students(studentid), 
  CONSTRAINT FK_Library_Books FOREIGN KEY (bookid) REFERENCES Books(bookid)
);

--Insert sample data into tables

insert into students values (1, 'Sasa', 'Milenkovic');
insert into books values (1, '234178622008', 'The Sheltering Sky', 'Paul Bowles', 'Unknown', 90.5);
insert into books values (2, '444928330071', 'Look at Me', 'Jennifer Egan', 'Unknown', 83.5);
insert into books values (3, '311195001705', 'Pirates of the Caribbean', 'Jean-Paul Orpinas ', 'Disney', 77.0);
insert into library values (1, 1, 'Liam', '2018-02-11 00:00:00');
insert into library values (1, 3, 'Susan', '2018-02-23 00:00:00');


