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
  rnum int,
  planemodel VARCHAR(40),
  acode int REFERENCES Airlines(acode) ON DELETE CASCADE,
  PRIMARY KEY (acode, rnum) 
);

CREATE TABLE OutgoingRoutes (
  destination VARCHAR(20),
  outT DATE,
  rnum int,
  acode int,
  FOREIGN KEY (rnum, acode) REFERENCES Routes(rnum, acode) ON DELETE CASCADE,
  PRIMARY KEY (rnum, acode) 
);

CREATE TABLE IncomingRoutes (
  source VARCHAR(20),
  incT DATE,
  rnum int,
  acode int,
  FOREIGN KEY (rnum, acode) REFERENCES Routes(rnum, acode) ON DELETE CASCADE,
  PRIMARY KEY (rnum, acode)
);

CREATE TABLE Gates (
  gate VARCHAR(10) PRIMARY KEY,
  isFree int
);

CREATE TABLE Departures (
  depID int PRIMARY KEY,
  gate VARCHAR(10) REFERENCES Gates(gate),
  depT DATE,
  rnum int,
  acode int,
  FOREIGN KEY (rnum, acode) REFERENCES Routes(rnum, acode) ON DELETE CASCADE
);

CREATE TABLE Arrivals (
  arrID int PRIMARY KEY,
  gate VARCHAR(10) REFERENCES Gates(gate),
  arrT DATE,
  rnum int,
  acode int,
  FOREIGN KEY (rnum, acode) REFERENCES Routes(rnum, acode)ON DELETE CASCADE
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


