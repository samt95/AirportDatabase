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