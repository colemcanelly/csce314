
-- CSCE 314 [Sections 595, 596, 597] Programming Languages, Spring 2023
-- Homework Assignment 1 (Total 100 points)

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: Cole McAnelly
-- UIN: 630003358
-- Resources: N/A

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit  -- if this line causes compile error, try the following
                   -- command at the terminal prompt > cabal v1-install HUnit
import System.Exit


-- Problem 2 (5+10 = 15 points)
qsort1 :: Ord a => [a] -> [a]
---- Question 2.1 (5 points)
qsort1 [] = []
qsort1 (pivot:list) = qsort1 gt ++ [pivot] ++ qsort1 lt
  where
    lt = [a | a <- list, a <= pivot]
    gt = [b | b <- list, b > pivot]

---- Question 2.2 (10 points)
{- Write your answer for Question 2.2 within this block comment.
if you were to call qsort1 [4,1,6,5,3], the steps would look like this:
  
  qsort1 [6,5] ++ [4] ++ qsort1 [1,3]
  ├──qsort1 [] ++ [6] ++ qsort1 [5]
  │   └──qsort1 [] ++ [5] ++ qsort1 []
  └──qsort1 [3] ++ [1] ++ qsort1 []
      └──qsort1 [] ++ [3] ++ qsort1 []
  Thus, the total number of times the qsort1 function will be called is 10 times
-}


-- Problem 3 (10 points)
lucas :: Int -> Int
lucas 0 = 2
lucas 1 = 1
lucas n = lucas (n - 1) + lucas (n - 2)
{-
This is very similar to the fibonacci sequence,
the only difference are the starting values.
It follows that the function would look similar too,
the first line defines the base case when n = 0,
second line defines the base case when n = 1,
the final line defines the recursive case that repeatedly calls `lucas`
until a known base case is reached.
-}

-- Problem 4 (10 points)
factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n - 1)
{-
This function is even more straighforward than the last.
The first line defines the base case when n = 0,
the second line defines the recursive case that is called
until the base case n = 0 is reached. 
-}


-- Problem 5 (5+10+10=25 points)
---- Question 5.1 (5 points)
semifactorial :: Int -> Int
semifactorial 0 = 1
semifactorial 1 = 1
semifactorial n = n * semifactorial (n - 2)
{-
The first 2 lines define base cases for n = 0 and n = 1.
The 3rd line defines the given recursive case.
-}

---- Question 5.2 (10 points)
{- Write your answer for Question 5.2 within this block comment.
Given the function call `semifactorial 9`, the call stack would look like this:
  9 * semifactorial 7
  └──7 * semifactorial 5
      └──5 * semifactorial 3
          └──3 * semifactorial 1
  Therefore, the function would be recursively called 4 times.
-}

---- Question 5.3 (10 points)
myfactorial :: Int -> Int
myfactorial 0 = 1
myfactorial n = semifactorial n * semifactorial (n - 1)
{-
This took some testing to determine the relationship between the
semifactorial and the normal factorial, but I figured out that they are
very closely related, and that by multiplying the result of semifactorial n
times the result of semifactorial (n-1), it is equal to n! This actually
makes sense though, becuase n! = n * (n-1) * (n-2) * ... 1.
The semifactorial of n = n * (n-2) * ... * 1
The semifactorial of (n-1) = (n-1) * (n-3) * ... 1.
When you multiply them they are equal to n!
-}


-- Problem 6 (10+15+10=35 points)
---- Question 6.1 (10 points)
term :: Num a => Int -> a -> a
term 1 x = x
term n x = x * term (n - 1) x
{-
First line defines base case when n = 1.
Second line defines the recursive case as shown on the hw1 assignment
-}

---- Question 6.2 (15 points)
polynaive :: Num a => [a] -> Int -> a -> a
polynaive as 0 x = head as
polynaive as n x = (head as * term n x) + polynaive (tail as) (n - 1) x
{-
This function calls the function term to find the value of x^n.
The first line defines a base case of polynaive(0) = a_0.
The second line defines a recursive case that adds (a_n * x^n)
to the result of the same function called for n = n - 1.
-}

---- Question 6.3 (10 points)
{- Write your answer for Question 6.3 within this block comment.
When `polynaive [2,-1,3,5] 3 2` is called this is how my function works:

1. Get first element in list a_n, call `term n x` and multiply them:\
  - a_n = 2
  - term n x = term 3 2 ==> 8
  - a_n * term n x ==> 16
2. Add the result of that operation to the result of the recursive
   function call `polynaive` with the first element removed from the list
   using `tail`, n - 1, and the same value for x.
  - tail as = [-1,3,5]
  - n - 1 = 2
  - x = 2
  - Call: `polynaive [-1,3,5] 2 2`
3. Repeat this recursively until n = 0, as which point simply return a_0.
  - a_0 = 5
4. Return the final value of 23.
-}




myTestList = 
  TestList [

      "qsort1 1" ~: qsort1 [3, 2, 5, 1, 6] ~=? [6,5,3,2,1]
    , "qsort1 2" ~: qsort1 "howdy" ~=? "ywohd"
    , "qsort1 3" ~: qsort1 "Oh, happy day!" ~=? "yypphhdaaO,!  "

    , "lucas 1" ~: lucas 0 ~=? 2
    , "lucas 2" ~: lucas 1 ~=? 1    
    , "lucas 3" ~: lucas 4 ~=? 7
    
    , "factorial 1" ~: factorial 3 ~=? 6
    , "factorial 2" ~: factorial 5 ~=? 120
    , "factorial 3" ~: factorial 10 ~=? 3628800

    , "semifactorial 1" ~: semifactorial 3 ~=? 3
    , "semifactorial 2" ~: semifactorial 5 ~=? 15
    , "semifactorial 3" ~: semifactorial 10 ~=? 3840

    , "myfactorial 1" ~: myfactorial 3 ~=? 6
    , "myfactorial 2" ~: myfactorial 5 ~=? 120
    , "myfactorial 3" ~: myfactorial 10 ~=? 3628800

    , "term 1" ~: term 3 2 ~=? 8
    , "term 2" ~: term 4 5 ~=? 625
    , "term 3" ~: term 10 3 ~=? 59049

    , "polynaive 1" ~: polynaive [2,-1,3,5] 3 2 ~=? 23
    , "polynaive 2" ~: polynaive [1,3,0,7,2] 4 5 ~=? 1037
    , "polynaive 3" ~: polynaive [(1/3),1,-2,0,2,1,(1/2)] 6 3 ~=? 345.5
    
    ]

main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          exitWith (codeGet errs fails)
          
codeGet errs fails
 | fails > 0       = ExitFailure 2
 | errs > 0        = ExitFailure 1
 | otherwise       = ExitSuccess

