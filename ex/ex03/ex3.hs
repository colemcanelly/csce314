--  CSCE 314 [Sections 595, 596, 597] Programming Languages -- Hyunyoung Lee
--  Exercise 3
--  Due date and time: 11:59 p.m. Thursday, 2/9/2023
--  =====
--  Name: Cole McAnelly
--  UIN: 630003358
--  Resource: N/A


--  Problem 1.
--  1.1. Using if conditional expressions.
min (x, y) = if x <= y
             then x
             else y
{-
ghci> Main.min (3,4)
3
ghci> Main.min (8,1)
1
-}

--  1.2. Using guards.
guardmin (x, y)
  | x <= y = x
  | otherwise = y
{-
ghci> guardmin (3,4)
3
ghci> guardmin (8,1)
1
-}

--  1.3. Give the type of min.
{-
Type of min and guardmin:

min :: Ord a => (a, a) -> a
-}
     

--  Problem 2.
take n xs
    | n <= 0 = []
    | null xs = []
    | otherwise = head xs : Main.take (n - 1) (tail xs)
{-
First line is the function declaration.
Second line is the first case, where n = 0, in that case we return an empty list.
Third line is the second case, where the list `xs` is empty; in that case, regardless of n, we return an empty list.
The final line is recursive case that takes the first element in the list with the `head` function and prepends it to 
    the list returned from the recursive call of our function, which runs until it hits one of the aformentioned base cases.(*)

ghci> Main.take 4 []
[]
ghci> Main.take 0 [1,5,4]
[]
ghci> Main.take 2 [1,5,4]
[1,5]
ghci> Main.take 5 [1,5,4]
[1,5,4]
-}


--  Problem 3.
--  3.1. [(x,y) | x <- [2,3,4], y <- [5,6]]
{-
The evaluation should take the first element in x, make all possible tuples with y,
    and then move to the next value from x. 
    Thus [(x_1,y_1), ... (x_1,y_n), (x_2,y_1), ... (x_2,y_n), ... (x_n,y_1), ... (x_n,y_n)].
For this problem, the output should be [(2,5), (2,6), (3,5), (3,6), (4,5), (4,6)].

sure enough:
ghci> [(x,y) | x <- [2,3,4], y <- [5,6]]
[(2,5),(2,6),(3,5),(3,6),(4,5),(4,6)]
-}

--  3.2. [(y,x) | x <- [5,6], y <- [2,3,4]]
{-
This problem, the input order is switched, so this will work identically to the previous 
    problem, except it will start with y, because that is the first value in the tuple.
    Thus [(y_1,x_1), ... (y_1,x_n), (y_2,x_1), ... (y_2,x_n), ... (y_n,x_1), ... (y_n,x_n)].
Therefore the output should be: [(2,5), (3,5), (4,5), (2,6), (3,6), (4,6)].

This is confirmed in ghci:
ghci> [(y,x) | x <- [5,6], y <- [2,3,4]]
[(2,5),(3,5),(4,5),(2,6),(3,6),(4,6)]
-}


--  Problem 4.
--  4.1. Implementation 1.
factorial' :: Int -> Int
factorial' 0 = 1
factorial' n 
    | n < 0 = error "Facorial not defined for negative values of n"
    | otherwise = n * factorial' (n - 1)
{-
This version of the facorial redifines the recursive case to include a guard for 
    negative values of n. The first line is the normal default case, and the second 
    line splits into a guard statement. It sends an error if n < 0, otherwise
    it returns the normal recursive case call.

Tests:
ghci> factorial' (-1)
*** Exception: Facorial not defined for negative values of n
CallStack (from HasCallStack):
  error, called at ex3.hs:93:15 in main:Main
ghci> factorial' (3)
6
-}

--  4.2. Implementation 2.
factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n - 1)

safefactorial :: Int -> Int
safefactorial n
    | n < 0 = error "Facorial not defined for negative values of n"
    | otherwise = factorial n

{-
This version of factorial, `safefactorial` first checks for invalid values of n 
    and sends an error if any are found. Otherwise, it returns the default `factorial`
    function call.
Tests:
ghci> safefactorial (-1)
*** Exception: Facorial not defined for negative values of n
CallStack (from HasCallStack):
  error, called at ex3.hs:105:15 in main:Main
ghci> safefactorial (3)
6
-}
