--  CSCE 314 Programming Languages
--  Exercise 6
--  Due: Friday, 2/24/2023
--  =====
--  Name: Cole McAnelly


-- Problem 1

--  Problem 4
f = [1,2] >>= \x -> [x..3] >>= \y -> return x


g = do x <- [1,2]
       y <- [x..3]
       return x