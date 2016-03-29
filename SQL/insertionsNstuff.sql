-- insertions

INSERT INTO AIRLINES(acode, name, website)
VALUES (000, 'airline1', 'airline1.com');

INSERT INTO Routes(rnum, acode, planemodel)
VALUES (1, 000, 'model1');

INSERT INTO OutgoingRoutes(rnum, acode, outT, destination) 
VALUES(1, 000, TO_DATE('01/01/2016 01:00:00', 'dd/mm/yyyy hh24:mi:ss'), 'Toronto');

INSERT INTO Departures(rnum, acode, depID, gate, depT)
VALUES(1, 000, 1, 'abc', TO_DATE('01/01/2016 01:00:00', 'dd/mm/yyyy hh24:mi:ss'));

INSERT INTO Passengers VALUES(1, 'fucktits', 22, TO_DATE('01/01/2016 01:00:00', 'dd/mm/yyyy hh24:mi:ss'), 'FUK', 1, null);

INSERT INTO Baggage VALUES(1, 55, 1);


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

SELECT TO_CHAR(dept, 'dd/mm/yyyy hh24:mi:ss') FROM Departures;