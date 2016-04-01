-- constraint i

CREATE OR REPLACE VIEW PassengersV AS 
  SELECT pID,name,gov_issued_id,dob,pob,arrID,depID --i.e. all attributes are made available
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


-- constraint gov_issued_id

