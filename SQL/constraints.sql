-- constraint i

CREATE OR REPLACE VIEW PassengersV AS 
  SELECT * --i.e. all attributes are made available
  FROM Passengers X
  WHERE NOT EXISTS (
    --arrival time is greater than departure time for the tuple from X
    
      Select * 
      FROM Arrivals, Departures
      Where X.ARRID = arrID
      and X.DEPID = depID
      and arrT > depT
 
 
  );
WITH CHECK OPTION;




-- constraint ii

CREATE OR REPLACE VIEW DepartureV AS 
  SELECT *   --i.e. all attributes are made available
  FROM Departures X
  WHERE NOT EXISTS (
    --Departure with the same gate and depT within +/- 1 hour of X.depT
    (SELECT *
    FROM Departures d 
    where  X.gate = d.gate
    and (24*abs(d.depT - X.depT) < 1)
    and d.depID<>X.depID
    )
    UNION
    (SELECT *
      FROM Arrivals a
      where a.gate = X.gate
      and (24*abs(a.arrt - X.depT) < 1)
    --Arrival with the same gate and arrT within +/- 1 hour of X.depT
    )
  )
WITH CHECK OPTION;



CREATE OR REPLACE VIEW ArrivalV AS 
  SELECT *   --i.e. all attributes are made available
  FROM Arrivals X
  WHERE NOT EXISTS (
    (SELECT *
    FROM Departures d 
    where  X.gate = d.gate
    and (24*abs(d.depT - X.arrT) < 1)
  )
    UNION
    (SELECT *
      FROM Arrivals a
      where a.gate = X.gate
      and (24*abs(a.arrt - X.arrT) < 1)
      and a.arrID<>X.arrID
      )
    --Arrival with the same gate and arrT within +/- 1 hour of X.depT
  )
WITH CHECK OPTION;



