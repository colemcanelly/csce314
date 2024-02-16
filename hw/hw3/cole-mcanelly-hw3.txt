
-- CSCE 314 [Sections 595, 596, 597] Programming Languages Spring 2023
-- Homework Assignment 3 (Total 135 points)
-- Due on Monday, March 6, 2023

-- Problem 1 (5 points)
-- Student Name: Cole McAnelly
-- UIN: 630003358
-- N/A
-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit
import System.Exit
import Distribution.Compat.CharParsing (CharParsing(string))

-- *** Read Chapters 8 and 16 ***

data Tree a b = Leaf a | Branch b (Tree a b) (Tree a b)

---- Tree objects to be used to test your functions in Problems 2, 3 and 4
-- Use tree1 to show the step-by-step of your functions in Problem 3
tree1 :: Tree Int String
tree1 = Branch "+" 
            (Branch "+" 
               (Leaf 2)
               (Branch "*" (Leaf 3) (Leaf 4)))
            (Branch "*"
               (Branch "+" (Leaf 5) (Leaf 6))
               (Leaf 7))

-- Another example Tree object
tree2 :: Tree Int String 
tree2 = Branch "A" 
            (Branch "B" 
               (Leaf 1) 
               (Leaf 2)) 
            (Leaf 3)

-- Yet another Tree object
tree3 :: Tree Int String
tree3 = Branch "+" 
            (Branch "*" 
               (Leaf 4)
               (Leaf 5))
            (Branch "+"
               (Branch "*" (Leaf 2) (Leaf 3))
               (Leaf 6))
               
---------------

-- Problem 2 (20 points)
treeShow :: (Show a1, Show a2) => Tree a1 a2 -> Int -> [Char]
treeShow (Leaf a) tabs        = replicate (tabs * 4) ' ' ++ show a ++ "\n"
treeShow (Branch x l r) tabs  = replicate (tabs * 4) ' ' ++ show x ++ "\n" ++ treeShow r (tabs + 1) ++ treeShow l (tabs + 1)

instance (Show a, Show b) => Show (Tree a b) where
   show :: (Show a, Show b) => Tree a b -> String
   show t = treeShow t 0
   

-- Problem 3 (15 + 15 = 30 points)
---- Problem 3.1 (5 + 5 + 5 = 15 points)
preorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
preorder mapLeaf mapBranch (Leaf a)       = [mapLeaf a]
preorder mapLeaf mapBranch (Branch b l r) = [value] ++ left ++ right
   where
      value = mapBranch b
      left = preorder mapLeaf mapBranch l
      right = preorder mapLeaf mapBranch r 

inorder   :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
inorder mapLeaf mapBranch (Leaf a)       = [mapLeaf a]
inorder mapLeaf mapBranch (Branch b l r) = left ++ [value] ++ right
   where
      value = mapBranch b
      left = inorder mapLeaf mapBranch l
      right = inorder mapLeaf mapBranch r 

postorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
postorder mapLeaf mapBranch (Leaf a)       = [mapLeaf a]
postorder mapLeaf mapBranch (Branch b l r) = left ++ right ++ [value]
   where
      value = mapBranch b
      left = postorder mapLeaf mapBranch l
      right = postorder mapLeaf mapBranch r 

---- Problem 3.2 (5 + 5 + 5 = 15 points)
{-- Explain the step-by-step of the following expression (each 5 points)

My functions are almost identical in that:
The base case is when the tree object passed is a Leaf node, 
   in which case I simply return a singlet list of the mapped leaf conversion
My Recursive case consists of various ordering of `value`, `left`, and `right`: 
   `value`: maps the conversion function on the current node's value
   `left` : recursively calls the function on the left child
   `right`: recursively calls the function on the right child

> preorder show id tree1 
   ⇒  ["+","+","2","*","3","4","*","+","5","6","7"]
   Preorder concatenates [value] to left, which is concatenated to right.
   1. `value` = id "+", `left` and `right` are called on the left and right children
   2. repeat recursively until base case
   ...
   n. `preorder show id (Leaf 7)`       = [show 7]    ⇒ ["7"]

> inorder show id tree1
   ⇒  ["2","+","3","*","4","+","5","+","6","*","7"]
   Inorder concatenates left to [value], which is concatenated to right.
   1. `left` is called and concatenated to [id "+"] which is concatenated to the result of `right`.
   2. repeat recursively until base case
   ...
   n. `inorder show id (Leaf 7)`       = [show 7]    ⇒ ["7"]

> postorder show id tree1
   ⇒  ["2","3","4","*","+","5","6","+","7","*","+"]
   Postorder concatenates left to right, which is concatenated to [value].
   1. `left` is called recursively and concatenated to the result of `right`, which is concatenated to [id "+"].
   2. repeat recursively until base case
   ...
   n. `postorder show id (Leaf 7)`       = [show 7]    ⇒ ["7"]
--}



-- Problem 4 (20 + 20 = 40 points)
-- Chapter 16, Exercise 6 (MODIFIED: Use the above Tree a b type instead of
-- the Tree type given in the exercise problem.)

---- Problem 4.1 (10 + 10 = 20 points)
leaves :: Tree a b -> Int
leaves (Leaf _) = 1
leaves (Branch _ l r) = 0 + leaves l + leaves r

branches :: Tree a b -> Int
branches (Leaf _) = 0
branches (Branch _ l r) = 1 + branches l + branches r

---- Problem 4.2 (Base case 5 points + inductive case 15 points = 20 points)
{-- (Write your induction proof within this block comment.)
Prove: 
   leaves t = branches t + 1
Base case:
   Given a branch `t`, let |t| = children(t):
   |t| ≥ 1, otherwise `t` is a Leaf
Assume:
   leaves t = branches t + 1
Inductive case: (Make sure that you state the induction hypothesis first!)
   ⇒ leaves t =  

--}


-- Problem 5 (40 points) Chapter 8, Exercise 9
data Expr = Val Int | Add Expr Expr | Mul Expr Expr

type Cont = [Op]

data Op = EVALA Expr | ADD Int | EVALM Expr | MUL Int

eval :: Expr -> Cont -> Int
-- Give three definitions for eval.
-- First two definitions,
-- 1) for (Val n) and c as arguments and
-- 2) for (Add x y) and c as arguments
-- are already given in the text Section 8.7, but
-- you need to modify the second definition slightly
-- and give the third definition for (Mul x y)
eval = undefined

exec :: Cont -> Int -> Int
-- Give five definitions for exec, one for an empty list and
-- one for each of the four constructors of the data type Op
-- Some of these are already given in the text Section 8.7.
exec = undefined

value :: Expr -> Int
value e = eval e []

-- Following expressions are to test your eval and exec definitions
-- (2 + 3) + 4 = 9
e1 = (Val 3)    -- 3
e2 = (Add (Val 4) (Val 2))  -- 4 + 2 = 6
e3 = (Mul (Val 4) (Val 3))  -- 4 * 3 = 12
e4 = (Add (Add (Val 2) (Val 3)) (Val 4))  -- (2 + 3) + 4 = 9
e5 = (Mul (Mul (Val 2) (Val 3)) (Val 4))  -- (2 * 3) * 4 = 24
e6 = (Mul (Add (Val 2) (Val 3)) (Val 4))  -- (2 + 3) * 4 = 20
e7 = (Add (Mul (Val 2) (Val 3)) (Val 4))  -- (2 * 3) + 4 = 10
e8 = (Add (Mul (Val 2) (Val 3)) (Add (Val 4) (Val 5))) -- (2 * 3) + (4 + 5) = 15
e9 = (Mul (Add (Val 2) (Val 3)) (Add (Val 4) (Val 5))) -- (2 + 3) * (4 + 5) = 45
e10 = (Add (Mul (Add (Val 2) (Val 3)) (Mul (Val 4) (Val 5))) (Mul (Val 3) (Add (Val 4) (Val 7)))) -- ((2 + 3) * (4 * 5)) + (3 * (4 + 7)) = 133


myTestList = 
  TestList [

    "preorder 1"  ~: (concat (preorder show id tree1)) ~=? "++2*34*+567"
  , "inorder 1"   ~: (concat (inorder show id tree1))  ~=? "2+3*4+5+6*7"
  , "postorder 1" ~: (concat (postorder show id tree1))  ~=? "234*+56+7*+"
  , "preorder 2"  ~: (concat (preorder show id tree2)) ~=? "AB123"
  , "inorder 2"   ~: (concat (inorder show id tree2))  ~=? "1B2A3"
  , "postorder 2" ~: (concat (postorder show id tree2))  ~=? "12B3A"
  , "preorder 3"  ~: (concat (preorder show id tree3)) ~=? "+*45+*236"
  , "inorder 3"   ~: (concat (inorder show id tree3))  ~=? "4*5+2*3+6"
  , "postorder 3" ~: (concat (postorder show id tree3))  ~=? "45*23*6++"
 
  , "leaves 1"    ~: leaves tree1 ~=? 6
  , "branches 1"  ~: branches tree1 ~=? 5
  , "leaves 2"    ~: leaves tree2 ~=? 3
  , "branches 2"  ~: branches tree2 ~=? 2
  , "leaves 3"    ~: leaves tree3 ~=? 5
  , "branches 3"  ~: branches tree3 ~=? 4

--   , "value 1"  ~: value e1 ~=? 3
--   , "value 2"  ~: value e2 ~=? 6
--   , "value 3"  ~: value e3 ~=? 12
--   , "value 4"  ~: value e4 ~=? 9
--   , "value 5"  ~: value e5 ~=? 24
--   , "value 6"  ~: value e6 ~=? 20
--   , "value 7"  ~: value e7 ~=? 10
--   , "value 8"  ~: value e8 ~=? 15
--   , "value 9"  ~: value e9 ~=? 45
--   , "value 10" ~: value e10 ~=? 133
  
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

