
-- CSCE 314 [Sections  595, 596, 597] Programming Languages Spring 2023
-- Hyunyoung Lee
-- Homework Assignment 2 (Total 100 points)

-- Problem 1 (5 points)
-- Student Name: Cole McAnelly
-- UIN: 630003358
-- Resources: N/A

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit
import System.Exit
import Data.List
import Data.Char

-- *** Read textbook Chapters 5, 6, and 7 ***

-- Problem 2 (Chapter 5, Exercise 9) (10+5=15 points)
scalarproduct :: [Int] -> [Int] -> Int
---- Question 2.1 (10 points)
scalarproduct xs ys = sum [ x * y | (x, y) <- zip xs ys ]

---- Question 2.2 (5 points)
{-
  This is the process for my scalarproduct function, given this call:
    *Main> scalarproduct [7,8,5] [4..]
  1. My function calls `zip xs ys`, which will result in a list of 3 tuples:
    ⇒ [(7,4),(8,5),(5,6)]
  2. Using list comprehension, each tuple is multiplied to create entries in a new list:
    ⇒ [(7 * 4),(8 * 5),(5 * 6)]
  3. Sum is called on the list:
    ⇒ 98
-}



-- Problem 3 (Chapter 6, Exercise 7) (10 points)
merge :: Ord a => [a] -> [a] -> [a]
merge xs []         = xs
merge [] ys         = ys
merge (x:xs) (y:ys)
  | y < x     = y : merge (x : xs) ys
  | otherwise = x : merge xs (y : ys)



-- Problem 4 (Chapter 6, Exercise 8) (10+10=20 points)
---- Question 4.1 (10 points)
halve :: [a] -> ([a], [a])
halve ls = splitAt (div (length ls) 2) ls

---- Question 4.2 (10 points)
msort :: Ord a => [a] -> [a]
msort [] = []
msort [x] = [x]
msort xs = merge (msort $ fst halves) (msort $ snd halves)
  where
    halves = halve xs




-- Problem 5 (10+10=20 points)
---- Question 5.1 (10 points) 
mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a]
mergeBy _ xs []         = xs
mergeBy _ [] ys         = ys
mergeBy f (x:xs) (y:ys)
  | f x y     = x:mergeBy f xs (y:ys)
  | otherwise = y:mergeBy f (x:xs) ys

---- Question 5.2 (10 points) 
msortBy :: (a -> a -> Bool) -> [a] -> [a]
msortBy _ [] = []
msortBy _ [x] = [x]
msortBy f xs = mergeBy f (msortBy f $ fst halves) (msortBy f $ snd halves)
  where
    halves = halve xs




-- Problem 6 (10+10=20 points)
---- Question 6.1 (10 points)
altMap :: (a -> b) -> (a -> b) -> [a] -> [b]
altMap _ _ []       = []
altMap f _ [x]      = [f x]
altMap f g (x:y:ls) = f x : g y : altMap f g ls

---- Question 6.2 (10 points)
{-
  The first 2 lines define base cases for when the list is only 0 or 1 elements long.
  The third line is the recursive case for this function, which works by getting the first 
  2 elements in a list, applying each given function an appending them to the result of the 
  recursive call.

  Given:
    *Main> altMap (‘div‘ 3) (*5) [1..10]
  1. Get first 2 elements in list
    ⇒ 1:2:[3..10]
  2. Apply each function to the elements, append to front of recursive call
    ⇒ (1 `div` 3):(2 * 5):altMap (‘div‘ 3) (*5) [3..10]
  3. Repeat until base case
    ⇒ (1 `div` 3):(2 * 5):(. . .):altMap (‘div‘ 3) (*5) []
  4. Upon base case, return specified condition
    ⇒ (. . .):[]
  5. Return complete list
    ⇒ [0,10,1,20,1,30,2,40,3,50]
-}



-- Problem 7 (10 points)
concatenateAndUpcaseEvenLengthStrings :: [String] -> String
concatenateAndUpcaseEvenLengthStrings = concat . map (map toUpper) . filter (even . length)


    
myTestList =
  TestList [
      "scalarproduct 1" ~: scalarproduct [4,5,6] [1,2,3] ~=? 32
    , "scalarproduct 2" ~: scalarproduct [2,3] [1,-1] ~=? -1
    , "scalarproduct 3" ~: scalarproduct [1..10] [1..5] ~=? 55

    , "merge 1" ~: merge "EGG" "ABCDEFGH" ~=? "ABCDEEFGGGH" 
    , "merge 2" ~: merge "Hello" "e" ~=? "Heello"

    , "halve 1" ~: halve "" ~=? ("","")
    , "halve 2" ~: halve "halves" ~=? ("hal","ves")
    , "halve 3" ~: halve "halve" ~=? ("ha","lve")

    , "msort 1" ~: msort "Howdy all!" ~=? " !Hadllowy"
    , "msort 2" ~: msort "" ~=? ""
    , "msort 3" ~: msort "Mississippi" ~=? "Miiiippssss"

    , "mergeBy 1" ~: mergeBy (>) "FED" "GBA" ~=? "GFEDBA"
    , "mergeBy 2" ~: mergeBy (<) "Howdy" "Maui" ~=? "HMaouiwdy"
      
    , "msortBy 1" ~: msortBy (<) "gig 'em" ~=? " 'eggim" 
    , "msortBy 2" ~: msortBy (>) "Jack be nimble" ~=? "nmlkieecbbaJ  "
    , "msortBy 3" ~: msortBy (<) "" ~=? ""

    , "altMap 1" ~: altMap (* 10) (* 100) [1,2,3,4,5] ~=? [10,200,30,400,50]
    , "altMap 2" ~: and (altMap even odd [1..10]) ~=? False
    , "altMap 3" ~: altMap toLower toUpper "Haskell IS fun!" ~=? "hAsKeLl iS FuN!"

    , "concatenateAndUpcaseEvenLengthStrings" ~:
         concatenateAndUpcaseEvenLengthStrings ["here's ", "an", "a", " example"] ~=? "AN EXAMPLE"

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
