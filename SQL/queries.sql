5.

a)

-- ACTUAL QUERY
-- 'airlineName' is replaced by the name entered in the form
SELECT *
FROM Routes
WHERE acode = (
  SELECT acode
  FROM Airlines
  WHERE name='airlineName'
  );


-- query in java string used in form
ResultSet rset = stmt.executeQuery(
                "SELECT * " +
                "FROM Routes " +
                "WHERE acode = ( " +
                    "SELECT acode " +
                    "FROM Airlines " +
                    "WHERE name='" + airline + "'" +
                    " )"
                );


---------------------------------------------------------------------------------------

b)


-- ACTUAL QUERY

-- initially used this to perfrom a single query
SELECT * 
FROM Routes
WHERE rnum IN (
  SELECT rnum
  FROM OutgoingRoutes
  WHERE destination='Toronto'
  )
  OR rnum IN (
  SELECT rnum
  FROM IncomingRoutes
  WHERE source='Toronto'
  );


-- used this following queries in the form instead to split up departures and arrivals in different tables
-- 'location' is replaced by string entered in form
-- for departures
SELECT acode, rnum, TO_CHAR(outT, 'hh24:mi') AS outTime
FROM OutgoingRoutes 
WHERE destination='location';

-- for arrivals
SELECT acode, rnum, TO_CHAR(incT, 'hh24:mi') AS incomingTime
FROM IncomingRoutes
WHERE source='location';



-- query in java string used in form

ResultSet rset = stmt.executeQuery(
                      "SELECT acode, rnum, TO_CHAR(outT, 'hh24:mi') AS outTime "
                    + "FROM OutgoingRoutes "
                    + "  WHERE destination='" + location + "'"
                );

rset = stmt.executeQuery(
                    "SELECT acode, rnum, TO_CHAR(incT, 'hh24:mi') AS incomingTime "
                  + "FROM IncomingRoutes "
                  + "  WHERE source='" + location + "'"
              );

---------------------------------------------------------------------------------------

c)

-- ACTUAL QUERIES

-- for departures
SELECT *
FROM Departures
WHERE 24*ABS(depT-to_date('01/01/2016 12:00', 'dd/mm/yyyy hh24:mi') ) <= 1;

-- for arrivals
SELECT *
FROM Arrivals
WHERE 24*ABS(arrT-to_date('01/01/2016 12:00', 'dd/mm/yyyy hh24:mi') ) <= 1;



-- JAVA STRING QUERIES

ResultSet rset = stmt.executeQuery(
                          "SELECT * "
                        + "FROM Departures "
                        + "WHERE 24*ABS(depT-to_date('" + dateTime + "', 'dd/mm/yyyy hh24:mi') ) <= 1"
                );


rset = stmt.executeQuery(
                              "SELECT acode, rnum, TO_CHAR(incT, 'hh24:mi') AS incomingTime "
                            + "FROM IncomingRoutes "
                            + "  WHERE source='" + location + "'"
                        );



---------------------------------------------------------------------------------------
d)


-- for departures where depID is an interger entered in the form
ResultSet rset = stmt.executeQuery(
                  "SELECT * "
                + "FROM Passengers "
                + "WHERE depID=" + Integer.valueOf(depID)
                );

-- for arrivals where arrID is an interger intered in the form
ResultSet rset = stmt.executeQuery(
                  "SELECT * "
                + "FROM Passengers "
                + "WHERE arrID=" + Integer.valueOf(arrID)
                );

---------------------------------------------------------------------------------------
e)

-- passengerID is entered in the form
ResultSet rset = stmt.executeQuery(
                        "SELECT * "
                        + "FROM Baggage "
                        + "WHERE pID = ( "
                        + "  SELECT pID "
                        + "  FROM Passengers "
                        + "  WHERE pid=" + passengerID
                        + "  )"
                        );


---------------------------------------------------------------------------------------
f)

SELECT gate FROM Gates WHERE isFree=1;

---------------------------------------------------------------------------------------
g)

-- where '1' is replaced by the gate entered in the form
UPDATE Gates
SET isFree=1
WHERE gate='1';


---------------------------------------------------------------------------------------
6.

a)
SELECT I.rnum AS inRoute, O.rnum AS outRoute
FROM IncomingRoutes I, OutgoingRoutes O
WHERE ((I.incT+(1/24)) <= O.outT AND
      O.outT <= (I.incT+(12/24)));
---------------------------------------------------------------------------------------

b)

SELECT *
FROM Passengers
WHERE depID IS NOT NULL AND arrID IS NOT NULL;


---------------------------------------------------------------------------------------
c)

CREATE VIEW PassengerFlightsCount AS
  SELECT *
  FROM (
  -- count arrival
  SELECT name, gov_issued_id, NVL(COUNT(pID), 0) AS flightCount
  FROM Passengers P1
  WHERE arrID IS NOT NULL
  GROUP BY gov_issued_id, name
  )
  UNION ALL (
  -- count departures
  SELECT name, gov_issued_id, NVL(COUNT(pID), 0) AS flightCount
  FROM Passengers P2
  WHERE depID IS NOT NULL
  GROUP BY gov_issued_id, name
  );



SELECT * FROM (
  SELECT name, gov_issued_id, SUM(flightCount) AS flightCount
  FROM PassengerFlightsCount
  GROUP BY name, gov_issued_id
  ORDER BY flightCount DESC
)
WHERE rownum <=3;


---------------------------------------------------------------------------------------
d)

SELECT destination, acode, COUNT(depID) AS depDelayCount
FROM Departures NATURAL JOIN OutgoingRoutes
WHERE to_date(to_char(depT,'hh24:mi'),'hh24:mi') - to_date(to_char(outT,'hh24:mi'),'hh24:mi') > 0
GROUP BY destination, acode
ORDER BY depDelayCount DESC;
