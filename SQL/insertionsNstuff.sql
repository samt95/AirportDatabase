-- insertions


INSERT INTO AIRLINES(acode, name, website)
VALUES (000, 'airline1', 'airline1.com');

INSERT INTO Routes(rnum, acode, planemodel)
VALUES (1, 000, 'model1');

INSERT INTO OutgoingRoutes(rnum, acode, outT, destination) 
VALUES(1, 000, TO_DATE('01/01/2016 01:00:00', 'dd/mm/yyyy hh24:mi:ss'), 'Toronto');

INSERT INTO Departures(depID, gate, depT, rnum, acode)
VALUES(1, 'abc', TO_DATE('01/01/2016 01:00:00', 'dd/mm/yyyy hh24:mi:ss'), 1, 000);

SELECT TO_CHAR(dept, 'dd/mm/yyyy hh24:mi:ss') FROM Departures;