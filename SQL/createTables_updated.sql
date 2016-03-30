
-- DROP TABLES
drop table baggage;
drop table passengers;
drop table departures;
drop table arrivals;
drop table incomingroutes;
drop table outgoingroutes;
drop table routes;
drop table airlines;



-- TABLE CREATION

CREATE TABLE Airlines (
  acode int PRIMARY KEY,
  name VARCHAR(20),
  website VARCHAR(20)
);

CREATE TABLE Routes (
  rnum int PRIMARY KEY,
  planemodel VARCHAR(40),
  acode int REFERENCES Airlines(acode) ON DELETE CASCADE
);

CREATE TABLE OutgoingRoutes (
  destination VARCHAR(20),
  outT DATE,
  rnum int REFERENCES Routes(rnum) ON DELETE CASCADE,
  PRIMARY KEY (rnum)
);

CREATE TABLE IncomingRoutes (
  source VARCHAR(20),
  incT DATE,
  rnum int REFERENCES Routes(rnum) ON DELETE CASCADE,
  acode int,
  PRIMARY KEY (rnum)
);

CREATE TABLE Departures (
  depID int PRIMARY KEY,
  gate VARCHAR(10),
  depT DATE,
  rnum int REFERENCES Routes(rnum) ON DELETE CASCADE
);

CREATE TABLE Arrivals (
  arrID int PRIMARY KEY ,
  gate VARCHAR(10),
  arrT DATE,
  rnum int REFERENCES Routes(rnum) ON DELETE CASCADE
);

CREATE TABLE Passengers (
  pID int PRIMARY KEY,
  name VARCHAR(20),
  gov_issued_id int,
  dob DATE,
  pob VARCHAR(40),
  depID int REFERENCES Departures(depID) ON DELETE CASCADE,
  arrID int REFERENCES Arrivals(arrID) ON DELETE CASCADE
);
  
CREATE TABLE Baggage (
  bID int PRIMARY KEY,
  weight int,
  pID int REFERENCES Passengers(pID) ON DELETE CASCADE
);


