-- TABLE CREATION

CREATE TABLE Airlines (
  acode int PRIMARY KEY,
  name VARCHAR(20),
  website VARCHAR(20)
);

CREATE TABLE Routes (
  rnum int,
  planemodel VARCHAR(40),
  acode int REFERENCES Airlines(acode),
  PRIMARY KEY (acode, rnum)
);

CREATE TABLE OutgoingRoutes (
  destination VARCHAR(20),
  outT DATE,
  rnum int,
  acode int,
  FOREIGN KEY (rnum, acode) REFERENCES Routes(rnum, acode),
  PRIMARY KEY (rnum, acode)
);

CREATE TABLE IncomingRoutes (
  source VARCHAR(20),
  incT DATE,
  rnum int,
  acode int,
  FOREIGN KEY (rnum, acode) REFERENCES Routes(rnum, acode),
  PRIMARY KEY (rnum, acode)
);

CREATE TABLE Departures (
  depID int PRIMARY KEY,
  gate VARCHAR(10),
  depT DATE,
  rnum int,
  acode int,
  FOREIGN KEY (rnum, acode) REFERENCES Routes(rnum, acode)
);

CREATE TABLE Arrivals (
  arrID int PRIMARY KEY,
  gate VARCHAR(10),
  arrT DATE,
  rnum int,
  acode int,
  FOREIGN KEY (rnum, acode) REFERENCES Routes(rnum, acode)
);

CREATE TABLE Passengers (
  pID int PRIMARY KEY,
  name VARCHAR(20),
  gov_issued_id int,
  dob DATE,
  pob VARCHAR(40),
  depID int REFERENCES Departures(depID),
  arrID int REFERENCES Arrivals(arrID)
);
  
CREATE TABLE Baggage (
  bID int PRIMARY KEY,
  weight int,
  pID int REFERENCES Passengers(pID)
);


