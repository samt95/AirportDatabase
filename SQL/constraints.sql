--constraints

CREATE OR REPLACE VIEW DeparturesView AS
  SELECT *
  FROM Departures D 
  WHERE NOT EXISTS (
    SELECT *
    FROM Departures d1 NATURAL JOIN OutgoingRoutes o1
    
    WHERE d1.gate IN (
      SELECT d2.gate
      FROM Departures d2 NATURAL JOIN OutgoingRoutes o2
      WHERE abs(o1.outT-o2.outT) < 1
    )
    
    OR d1.gate IN (
      SELECT a1.gate
      FROM Arrivals a1 NATURAL JOIN IncomingRoutes i1
      WHERE abs(o1.outT-i1.incT) < 1
    )
  )
WITH CHECK OPTION;

--AUSTIN
CREATE OR REPLACE VIEW DepartureV AS 
  SELECT depID,depT,gate,acode,rnum   --i.e. all attributes are made available
  FROM Departures X
  WHERE NOT EXISTS (
    --Departure with the same gate and depT within +/- 1 hour of X.depT

    (SELECT *
    FROM Departures d 
    where  X.gate = d.gate
    and (24*abs(X.depT - d.depT)) < 1)

    UNION
    (SELECT *
      FROM Arrivals a
      where a.gate = X.gate
      and (24*abs(a.arrT - X.depT)) < 1)

    --Arrival with the same gate and arrT within +/- 1 hour of X.depT
  )
WITH CHECK OPTION;


CREATE OR REPLACE VIEW DeparturesView AS
  SELECT *
  FROM Departures 
  WHERE NOT EXISTS (
    SELECT *
    FROM Departures d1 NATURAL JOIN OutgoingRoutes o1
    
    WHERE d1.gate IN (
      SELECT d2.gate
      FROM Departures d2 NATURAL JOIN OutgoingRoutes o2
      WHERE abs(o1.outT-o2.outT) < 1 AND d1.gate<>d2.gate
    )
    
    OR d1.gate IN (
      SELECT a1.gate
      FROM Arrivals a1 NATURAL JOIN IncomingRoutes i1
      WHERE abs(o1.outT-i1.incT) < 1 AND a1.gate<>i1.gate
    )
  )
WITH CHECK OPTION;




check on gates boolean