-- https://school.programmers.co.kr/learn/courses/30/lessons/131535

SELECT COUNT(USER_ID) as USERS FROM USER_INFO
WHERE YEAR(JOINED) = 2021 
AND AGE >= 20 AND AGE <= 29;