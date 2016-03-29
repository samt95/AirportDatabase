5.

b)

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



c)

-- for departures
SELECT *
FROM Departures
WHERE 24*ABS(depT-to_date('01/01/2016 12:00', 'dd/mm/yyyy hh24:mi') ) <= 1;

-- for arrivals
SELECT *
FROM Arrivals
WHERE 24*ABS(arrT-to_date('01/01/2016 12:00', 'dd/mm/yyyy hh24:mi') ) <= 1;



6.

a)
SELECT I.rnum AS inRoute, O.rnum AS outRoute
FROM IncomingRoutes I, OutgoingRoutes O
WHERE ((I.incT+(1/24)) <= O.outT AND
      O.outT <= (I.incT+(12/24)));


b)

-- need flight time??????????????
-- or need status
SELECT pID, name
FROM Passengers LEFT OUTER JOIN Departures LEFT OUTER JOIN Arrivals
WHERE (arrT > currentTime AND currentTime > arrt-flightTime) OR
      (depT < currentTime AND currentTime < depT+flightTime);

c)

SELECT pID, name, COUNT(pI)
FROM Passengers
WHERE
GROUP BY ()


d)


--not working.

SELECT destination, rnum, acode, COUNT(depID) AS depDelayCount
FROM Departures NATURAL JOIN OutgoingRoutes
WHERE to_date(to_char(depT,'hh24:mi'),'hh24:mi') - to_date(to_char(outT,'hh24:mi'),'hh24:mi') > 0
GROUP BY destination, acode
ORDER BY depDelayCount DESC;




