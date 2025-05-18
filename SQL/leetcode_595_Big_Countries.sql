USE [Leetcode]
SELECT name, population, area 
FROM dbo.World
WHERE area > 3000000 OR population > 25000000